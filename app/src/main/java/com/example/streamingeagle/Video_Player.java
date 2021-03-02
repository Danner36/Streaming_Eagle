package com.example.streamingeagle;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static android.webkit.WebSettings.PluginState.ON;


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
        // Attached webview to java class MyWebViewClient that vets the incoming urls before loading.
        // Blocks Ads / viruses / popups.
        // Also keeps url from launch in a browser.
        web.setWebViewClient(new MyWebViewClient());

        // Checks if channel is sourced from sportsbay.org.
        if(video_url.contains("sportsbay.org"))
        {
            // Changes the browser user agent since chrome user agent returns 403 Forbidden message.
            webSettings.setUserAgentString("Mozilla/5.0 (platform; rv:geckoversion) Gecko/geckotrail Firefox/firefoxversion");
        }
        web.loadUrl(video_url);
    }
}
