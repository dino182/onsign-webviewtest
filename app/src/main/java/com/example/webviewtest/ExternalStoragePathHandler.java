/*
 * Based on idea from https://issuetracker.google.com/issues/139313585
 */

package com.example.webviewtest;

import android.util.Log;
import android.webkit.WebResourceResponse;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.webkit.WebViewAssetLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExternalStoragePathHandler implements WebViewAssetLoader.PathHandler {
    private static final String TAG = "ExternalStoragePathHandler";

    @NonNull
    private final File mDirectory;

    public ExternalStoragePathHandler(@NonNull File directory) {
        mDirectory = directory;
    }

    @Override
    @WorkerThread
    @NonNull
    public WebResourceResponse handle(@NonNull String path) {
        Log.d(TAG, "Attempting to load file: " + path);
        try {
            File file = new File(mDirectory, path);
            FileInputStream fileInputStream = new FileInputStream(file);
            String mimeType = MimeUtil.guessMimeType(path);
            return new WebResourceResponse(mimeType, null, fileInputStream);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Error opening file: " + path, e);
        }

        return new WebResourceResponse(null, null, null);
    }
}
