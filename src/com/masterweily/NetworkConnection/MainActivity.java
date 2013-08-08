package com.masterweily.NetworkConnection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.masterweily.http_dsl.HttpDsl;


public class MainActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ( findViewById(R.id.getButton) ).setOnClickListener(this);
        ( findViewById(R.id.assyncGetButton) ).setOnClickListener(this);

    }

    public void onClick(final View v) {
        final String uri = "http://192.168.1.13/echo_params.php";
        Log.d("masterweily", "click");
        new Thread(){
            public void run() {
                switch (v.getId())
                {
                    case R.id.getButton:
                        HttpDsl.Response response = new HttpDsl.Request(uri).get();
                        if (response != null)
                            Log.d("masterweily", response.toString());
                        break;

                    case R.id.assyncGetButton:
                        HttpDsl.Request request = new HttpDsl.Request(uri).get(new HttpDsl.Request.Listener() {
                            @Override
                            public void onSuccess(HttpDsl.Response response) {
                                Log.d("masterweily", response.toString());
                            }
                        });
                        break;
                }
            }
        }.start();

    }

//    protected void getConnection() throws IOException
//    {
//        int response = -1;
//        String uri = "http://www.google.co.il";
//        URL url = new URL(uri);
//
//        URLConnection conn = url.openConnection();
//
//        if (! (conn instanceof HttpURLConnection) )
//            throw new IOException("Not an HTTP connection");
//
//        HttpURLConnection httpConn = (HttpURLConnection) conn;
//
//
//        httpConn.setAllowUserInteraction(false);
//        httpConn.setInstanceFollowRedirects(true);
//        httpConn.setRequestMethod("Get");
//
//        httpConn.connect();
//
//        response = httpConn.getResponseCode();
//        if (response == HttpURLConnection.HTTP_OK);
//        {
//            InputStream ips = httpConn.getInputStream();
//            Log.d("masterweily", ips.toString());
//        }
//
//    }
}
