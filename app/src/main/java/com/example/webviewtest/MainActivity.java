package com.example.webviewtest;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewClientCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String HTML_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/html";
    private static final String URL = "https://my.funky.domain/index.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a folder for test HTML files
        File htmlDir = new File(HTML_PATH);
        if (!htmlDir.exists()) {
            htmlDir.mkdirs();
        }

        WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/", new ExternalStoragePathHandler(htmlDir))
                .setDomain("my.funky.domain")
                .build();

        WebView webView = (WebView)findViewById(R.id.webView); //get webView
        webView.setWebChromeClient(new WebChromeClient());
        // Set webView client using example from https://developer.android.com/reference/androidx/webkit/WebViewAssetLoader
        webView.setWebViewClient(new WebViewClientCompat() {
            @Override
            @RequiresApi(21)
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return assetLoader.shouldInterceptRequest(request.getUrl());
            }

            @Override
            @SuppressWarnings("deprecation") // for API < 21
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return assetLoader.shouldInterceptRequest(Uri.parse(url));
            }
        });

        WebSettings webSettings = webView.getSettings();// initiate webView settings
        webSettings.setJavaScriptEnabled(true); // allow webView perform javascript
        webSettings.setDomStorageEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false); // allow videos to play unmuted
        // Setting this off for security. Off by default for SDK versions >= 16.
        webSettings.setAllowFileAccessFromFileURLs(false);
        // Off by default, deprecated for SDK versions >= 30.
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        // Keeping these off is less critical but still a good idea, especially if your app is not
        // using file:// or content:// URLs.
        webSettings.setAllowFileAccess(false);
        webSettings.setAllowContentAccess(false);

        webView.loadUrl(URL); //load URL
    }
}

