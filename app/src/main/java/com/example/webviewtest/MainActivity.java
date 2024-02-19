package com.example.webviewtest;

import android.os.Bundle;
import android.os.Environment;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String HTML_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/html";
    private static final String INDEX_FILE = "index.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a folder for test HTML files
        File htmlDir = new File(HTML_PATH);
        if (!htmlDir.exists()) {
            htmlDir.mkdirs();
        }

        WebView webView = (WebView)findViewById(R.id.webView); //get webView
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient()); //set webView client

        WebSettings webSettings = webView.getSettings();// initiate webView settings
        webSettings.setJavaScriptEnabled(true); // allow webView perform javascript
        webSettings.setDomStorageEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false); // allow videos to play unmuted
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(false); // Enforce CORS on file://

        String url = "file://" + new File(htmlDir, INDEX_FILE).getAbsolutePath();
        webView.loadUrl(url); //load URL
    }
}