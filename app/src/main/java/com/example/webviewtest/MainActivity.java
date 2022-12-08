package com.example.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://media.staging.displayplan.com/demo/garmin/lift/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView)findViewById(R.id.webView); //get webView
        webView.setWebViewClient(new WebViewClient()); //set webView client
        WebSettings webSettings = webView.getSettings();// initiate webView settings
        webSettings.setJavaScriptEnabled(true); // allow webView perform javascript
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);

        webView.loadUrl(URL); //load URL

    }
}