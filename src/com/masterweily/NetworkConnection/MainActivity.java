package com.masterweily.NetworkConnection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ( findViewById(R.id.getButton) ).setOnClickListener(this);

    }

    public void onClick(View v) {
        Log.d("masterweily", "click");
        switch (v.getId()) {

            case R.id.getButton:
                try {
                    getConnection();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                break;

        }
    }

    protected void getConnection() throws IOException
    {
        int response = -1;
        String uri = "http://www.google.co.il";
        URL url = new URL(uri);

        URLConnection conn = url.openConnection();

        if (! (conn instanceof HttpURLConnection) )
            throw new IOException("Not an HTTP connection");

        HttpURLConnection httpConn = (HttpURLConnection) conn;


        httpConn.setAllowUserInteraction(false);
        httpConn.setInstanceFollowRedirects(true);
        httpConn.setRequestMethod("Get");

        httpConn.connect();

        response = httpConn.getResponseCode();
        if (response == HttpURLConnection.HTTP_OK);
        {
            InputStream ips = httpConn.getInputStream();
            Log.d("masterweily", ips.toString());
        }

    }
}
