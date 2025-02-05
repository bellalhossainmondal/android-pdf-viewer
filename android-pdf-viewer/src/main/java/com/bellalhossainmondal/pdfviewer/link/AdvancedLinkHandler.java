package com.bellalhossainmondal.pdfviewer.link;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.bellalhossainmondal.pdfviewer.PDFView;
import com.bellalhossainmondal.pdfviewer.model.LinkTapEvent;

/**
 * ==========================================================
 * Project: android-pdf-viewer
 * File: AdvancedLinkHandler.java
 * Created by: Bellal Hossain Mondal
 * Date: 04/02/2025
 * Email: bellalmondalofficial@gmail.com
 * <p>
 * Description:
 * This class handles link interactions within a PDF document
 * using PDFView. It supports both external URL links and
 * internal document navigation. If the "askOpenLinks" option
 * is enabled, it prompts the user before opening external links.
 * ==========================================================
 */

public class AdvancedLinkHandler implements LinkHandler {
    private final PDFView pdfView;
    private final boolean askOpenLinks;

    // Constructor with askOpenLinks parameter
    public AdvancedLinkHandler(boolean askOpenLinks, PDFView pdfView) {
        this.pdfView = pdfView;
        this.askOpenLinks = askOpenLinks;
    }

    @Override
    public void handleLinkEvent(LinkTapEvent event) {
        Uri uri = Uri.parse(event.getLink().getUri());
        if (askOpenLinks) {
            if (uri != null && uri.isHierarchical()) {
                showLinkConfirmationDialog(uri.toString());
            } else {
                handleInternalLink(event);
            }
        } else {
            openUrl(uri.toString());
        }
    }

    private void showLinkConfirmationDialog(final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(pdfView.getContext());
        builder.setTitle("Do you want to open this link in an external browser?");
        builder.setPositiveButton("Open", (dialog, which) -> openUrl(url));
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void openUrl(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            pdfView.getContext().startActivity(intent);
        } catch (Exception e) {
            Log.e("MyLinkHandler", "Failed to open URL: " + url, e);
        }
    }

    private void handleInternalLink(LinkTapEvent event) {
        int pageIndex = event.getLink().getDestPageIdx();
        if (pageIndex >= 0) {
            pdfView.jumpTo(pageIndex);
        }
    }
}
