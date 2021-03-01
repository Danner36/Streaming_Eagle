package com.example.streamingeagle;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


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
        webSettings.setDomStorageEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        // Attached webview to Java Class MyWebViewClient that vets the incoming urls before loading.
        // Blocks Ads / viruses / popups.
        web.setWebViewClient(new MyWebViewClient());
        web.getSettings().setUserAgentString("Mozilla/5.0 (Linux; <Android Version>; <Build Tag etc.>) AppleWebKit/<WebKit Rev> (KHTML, like Gecko) Chrome/<Chrome Rev> Mobile Safari/<WebKit Rev>");

        web.loadUrl(video_url);
    }
}
