package com.example.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "file:///storage/emulated/0/Android/data/tv.onsign/files/html/Lift and Learn Full Screen Test/Video Troubleshooting/index.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView)findViewById(R.id.webView); //get webView
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient()); //set webView client

        WebSettings webSettings = webView.getSettings();// initiate webView settings
        webSettings.setJavaScriptEnabled(true); // allow webView perform javascript
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);

        webView.loadUrl(URL); //load URL
    }
}