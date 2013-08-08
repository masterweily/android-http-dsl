/**
 * Created with IntelliJ IDEA.
 * User: yaron
 * Date: 2/16/13
 * Time: 6:25 PM
 * To change this template use File | Settings | File Templates.
 */
package com.masterweily.http_dsl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpDsl
{
    public static class Request
    {
        public String uri;
        public Response response;


        public Request(String uri)
        {
            this.uri = uri;
        }

        public Response get()  {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(this.uri);

            try {
                HttpResponse response = client.execute(request);
                this.response = new Response(this,response );
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            return this.response;
        }


        public Request get(final Listener listener)
        {
            final Request self = this;
            new Thread(){
               public void run() {
                  listener.onSuccess( self.get() );
               }
            }.start();
            return this;
        }


        public static abstract class Listener{
           public abstract void onSuccess(Response response);
        }

    }

    public static class Response
    {
        public final Request request;
        private final HttpResponse response;

        public Response(Request request, HttpResponse response)
        {
            this.request = request;
            this.response = response;
        }

        public String toString()
        {
            String str = "";
            try {
                str = convertStreamToString( this.response.getEntity().getContent() );
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            finally {
                return str;
            }
        }

        private static String convertStreamToString(InputStream is) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append((line + "\n"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
    }
}
