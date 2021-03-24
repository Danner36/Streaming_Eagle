package com.example.streamingeagle;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class Video_Player extends AppCompatActivity {

    public static Handler handler = new Handler(Looper.getMainLooper());
    public static Runnable my_runnable;
    public static boolean desired_url = false;
    public static WebView web = null;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Bring Linear layout into view.
        setContentView(R.layout.webview);
        // Grab current intent & pull out video url.
        Intent i = getIntent();
        String video_url = i.getStringExtra("video_url");
        // Removes app name banner at top. Allows for orientation changes without reload.
        getSupportActionBar().hide();
        // Creates webview object.
        web = findViewById(R.id.webView);
        // Configure settings for webview.
        WebSettings webSettings = web.getSettings();
        // Allows use of the phones file storage.
        webSettings.setDomStorageEnabled(true);
        // Sets encoding standard for urls.
        webSettings.setDefaultTextEncodingName("utf-8");
        // Able to zoom.
        webSettings.setSupportZoom(true);
        // Needed for websites to load javascript enabled content (most videos/streams).
        webSettings.setJavaScriptEnabled(true);
        // Starts webview stream on load.
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setLoadsImagesAutomatically(true);
        //webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // Attached webview to java class MyWebViewClient that vets the incoming urls before loading.
        // Blocks Ads / viruses / popups.
        // Also keeps url from launch in a browser.
        web.setWebViewClient(new MyWebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(web, url);
                TextView myAwesomeTextView = (TextView)findViewById(R.id.textView);
                myAwesomeTextView.setText(view.getUrl());
            }
        });

        // Checks if channel is sourced from sportsbay.org.
        if(video_url.contains("sportsbay.org"))
        {
            // Changes the browser user agent since chrome user agent returns 403 Forbidden message.
            //Mozilla/5.0 (platform; rv:geckoversion) Gecko/geckotrail Firefox/firefoxversion
            webSettings.setUserAgentString("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        }

        web.loadUrl(video_url);
    }
}

