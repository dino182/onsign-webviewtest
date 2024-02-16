/*
 * Combined code from:
 *   https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:webkit/webkit/src/main/java/androidx/webkit/internal/MimeUtil.java
 *   https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:webkit/webkit/src/main/java/androidx/webkit/internal/AssetHelper.java
 */

package com.example.webviewtest;

import androidx.annotation.NonNull;

import java.net.URLConnection;

public class MimeUtil {
    /**
     * Default value to be used as MIME type if guessing MIME type failed.
     */
    public static final String DEFAULT_MIME_TYPE = "text/plain";

    /**
     * Use {@link MimeUtil#getMimeFromFileName} to guess MIME type or return the
     * {@link DEFAULT_MIME_TYPE} if it can't guess.
     *
     * @param filePath path of the file to guess its MIME type.
     * @return MIME type guessed from file extension or {@link DEFAULT_MIME_TYPE}.
     */
    @NonNull
    public static String guessMimeType(@NonNull String filePath) {
        String mimeType = MimeUtil.getMimeFromFileName(filePath);
        return mimeType == null ? DEFAULT_MIME_TYPE : mimeType;
    }

    private static String getMimeFromFileName(String fileName) {
        if (fileName == null) {
            return null;
        }

        // Copying the logic and mapping that Chromium follows.
        // First we check against the OS (this is a limited list by default)
        // but app developers can extend this.
        // We then check against a list of hardcoded mime types above if the
        // OS didn't provide a result.
        String mimeType = URLConnection.guessContentTypeFromName(fileName);

        if (mimeType != null) {
            return mimeType;
        }

        return guessHardcodedMime(fileName);
    }

    // We should keep this map in sync with the lists under
    // //net/base/mime_util.cc in Chromium.
    // A bunch of the mime types don't really apply to Android land
    // like word docs so feel free to filter out where necessary.
    private static String guessHardcodedMime(String fileName) {
        int finalFullStop = fileName.lastIndexOf('.');
        if (finalFullStop == -1) {
            return null;
        }

        final String extension = fileName.substring(finalFullStop + 1).toLowerCase();

        switch (extension) {
            case "webm":
                return "video/webm";
            case "mpeg":
            case "mpg":
                return "video/mpeg";
            case "mp3":
                return "audio/mpeg";
            case "wasm":
                return "application/wasm";
            case "xhtml":
            case "xht":
            case "xhtm":
                return "application/xhtml+xml";
            case "flac":
                return "audio/flac";
            case "ogg":
            case "oga":
            case "opus":
                return "audio/ogg";
            case "wav":
                return "audio/wav";
            case "m4a":
                return "audio/x-m4a";
            case "gif":
                return "image/gif";
            case "jpeg":
            case "jpg":
            case "jfif":
            case "pjpeg":
            case "pjp":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "apng":
                return "image/apng";
            case "svg":
            case "svgz":
                return "image/svg+xml";
            case "webp":
                return "image/webp";
            case "mht":
            case "mhtml":
                return "multipart/related";
            case "css":
                return "text/css";
            case "html":
            case "htm":
            case "shtml":
            case "shtm":
            case "ehtml":
                return "text/html";
            case "js":
            case "mjs":
                return "application/javascript";
            case "xml":
                return "text/xml";
            case "mp4":
            case "m4v":
                return "video/mp4";
            case "ogv":
            case "ogm":
                return "video/ogg";
            case "ico":
                return "image/x-icon";
            case "woff":
                return "application/font-woff";
            case "gz":
            case "tgz":
                return "application/gzip";
            case "json":
                return "application/json";
            case "pdf":
                return "application/pdf";
            case "zip":
                return "application/zip";
            case "bmp":
                return "image/bmp";
            case "tiff":
            case "tif":
                return "image/tiff";
            default:
                return null;
        }
    }
}
