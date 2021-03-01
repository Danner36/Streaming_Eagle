package com.example.streamingeagle;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;


public class Video_Player extends AppCompatActivity {


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
        WebView web = findViewById(R.id.webView);

        WebSettings webSettings = web.getSettings();
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        // Attached webview to Java Class MyWebViewClient that vets the incoming urls before loading.
        // Blocks Ads / viruses / popups.
        web.setWebViewClient(new MyWebViewClient());
        web.loadUrl(video_url);
    }
}
