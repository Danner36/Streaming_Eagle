package com.example.streamingeagle;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MyWebViewClient extends WebViewClient {
    public boolean shouldOverrideKeyEvent (WebView view, KeyEvent event) {

        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Video_Player.handler.removeCallbacks(Video_Player.my_runnable);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        final Uri uri = Uri.parse(url);
        return handleUri(uri, view);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        final Uri uri = request.getUrl();
        return handleUri(uri, view);
    }

    private boolean handleUri(final Uri uri, WebView view) {
        final String host = uri.getHost();
        // Check requested URL to known good
        if (host.contains("s1-tv.blogspot.com") ||
            host.contains("reddit-tv-streams.blogspot.com") ||
            host.contains("newdmn.icu") ||
            host.contains("lowend.xyz") ||
            host.contains("mygoodstream") ||
            host.contains("tinyurl"))
        {
            if(host.contains("lowend.xyz"))
            {
                Video_Player.my_runnable = new Runnable() {
                    @Override
                    public void run() {
                        view.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis() + 1,MotionEvent.ACTION_DOWN,300,300,0));
                        view.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis() + 1,MotionEvent.ACTION_UP,300,300,0));
                    }
                };
                Video_Player.handler.postDelayed(Video_Player.my_runnable, 50);
            }
            // Returning false means that you are going to load this url in the webView itself
            return false;
        } else {
            // Do not load the requested URL
            return true;
        }
    }
}
