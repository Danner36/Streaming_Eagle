package com.example.streamingeagle;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.os.SystemClock.sleep;


public class MyWebViewClient extends WebViewClient
{

    @Override
    public void onPageFinished(WebView view, String url)
    {
        Video_Player.desired_url = false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        final Uri uri = Uri.parse(url);
        return handleUri(uri, view);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
    {
        final Uri uri = request.getUrl();
        return handleUri(uri, view);
    }

    private boolean handleUri(final Uri uri, WebView view)
    {
        final String host = uri.getHost();
        // Check requested URL to known good
        if (host.contains("s1-tv.blogspot.com") ||
            host.contains("reddit-tv-streams.blogspot.com") ||
            host.contains("newdmn.icu") ||
            host.contains("lowend.xyz") ||
            host.contains("mygoodstream") ||
            host.contains("tinyurl"))
        {
            if(host.contains("lowend.xyz") && Video_Player.desired_url == false)
            {
                Video_Player.desired_url = true;
                new WebViewLoad().execute("worthless parameter");
            }
            // Returning false means that you are going to load this url in the webView itself
            return false;
        } else {
            // Do not load the requested URL
            return true;
        }
    }

    private class WebViewLoad extends AsyncTask<String, Integer, String>
    {
        // This is run in a background thread
        @Override
        protected String doInBackground(String... params)
        {
            while(Video_Player.desired_url = true)
            {
                Video_Player.web.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis() + 100, MotionEvent.ACTION_DOWN,500,1100,0));
            }
            return null;
        }
    }
}
