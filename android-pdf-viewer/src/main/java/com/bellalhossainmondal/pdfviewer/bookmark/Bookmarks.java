package com.bellalhossainmondal.pdfviewer.bookmark;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bellalhossainmondal.pdfviewer.PDFView;
import com.bellalhossainmondal.pdfium.PdfDocument;
import com.bellalhossainmondal.pdfviewer.R;
import com.bellalhossainmondal.pdfviewer.bookmark.adapter.BookmarkAdapter1;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

/**
 * ==========================================================
 * Project: android-pdf-viewer
 * File: Bookmarks.java
 * Created by: Bellal Hossain Mondal
 * Date: 05/01/2025
 * Email: bellalmondalofficial@gmail.com
 * Description:
 * <p>
 * Bookmarks is a BottomSheetDialog that displays a list of bookmarks
 * from a PDF document, allowing users to navigate easily through
 * different sections of the PDF.
 * <p>
 * How It Works:
 * 1. The constructor initializes the BottomSheetDialog with the given
 * context and a reference to PDFView.
 * 2. onCreate() sets up the dialog layout (bookmark_layout.xml).
 * 3. Retrieves the list of bookmarks from PDFView using getTableOfContents().
 * 4. Initializes a RecyclerView to display the bookmarks.
 * 5. Sets up a LinearLayoutManager for vertical scrolling.
 * 6. Assigns BookmarkAdapter1 to the RecyclerView, which populates the
 * list with parent and child bookmarks.
 * <p>
 * Features:
 * ✅ Displays PDF bookmarks in a BottomSheetDialog.
 * ✅ Supports hierarchical bookmark navigation.
 * ✅ Allows users to jump to bookmarked pages in PDFView.
 * ✅ Uses RecyclerView with BookmarkAdapter1 for efficient display.
 * <p>
 * This component enhances PDF navigation by providing a structured view
 * of bookmarks and quick access to specific pages.
 * ==========================================================
 */

public class Bookmarks extends BottomSheetDialog {
    private final PDFView pdfView;

    public Bookmarks(Context context, PDFView pdfView) {
        super(context);
        this.pdfView = pdfView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark_layout);

        // Get the list of bookmarks from the PDFView
        List<PdfDocument.Bookmark> bookmarks = pdfView.getTableOfContents();

        // Set up the RecyclerView and adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
            BookmarkAdapter1 bookmarkAdapter = new BookmarkAdapter1(bookmarks, pdfView);
            recyclerView.setAdapter(bookmarkAdapter);
        }
    }
}