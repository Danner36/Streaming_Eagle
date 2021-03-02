package com.example.streamingeagle;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import static android.content.ContentValues.TAG;

public class MyWebViewClient extends WebViewClient {
    public boolean shouldOverrideKeyEvent (WebView view, KeyEvent event) {

        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        final Uri uri = Uri.parse(url);
        return handleUri(uri);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        final Uri uri = request.getUrl();
        return handleUri(uri);
    }

    private boolean handleUri(final Uri uri) {
        Log.i(TAG, "Uri =" + uri);
        final String host = uri.getHost();
        final String scheme = uri.getScheme();
        // Check requested URL to known good
        if (host.equals("s1-tv.blogspot.com") ||
            host.equals("reddit-tv-streams.blogspot.com") ||
            host.equals("newdmn.icu") ||
            host.equals("lowend.xyz") ||
            host.equals("mygoodstream"))
        {
            // Returning false means that you are going to load this url in the webView itself
            return false;
        } else {
            // Do not load the requested URL
            return true;
        }
    }
}
