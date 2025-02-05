package com.bellalhossainmondal.pdfviewer.bookmark.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bellalhossainmondal.pdfium.PdfDocument;
import com.bellalhossainmondal.pdfviewer.PDFView;
import com.bellalhossainmondal.pdfviewer.R;

import java.util.List;

/**
 * ==========================================================
 * Project: android-pdf-viewer
 * File: BookmarkAdapter3.java
 * Created by: Bellal Hossain Mondal
 * Date: 05/02/2025
 * Email: bellalmondalofficial@gmail.com
 * Description:
 * <p>
 * BookmarkAdapter1 is a RecyclerView adapter responsible for displaying
 * a list of bookmarks in a hierarchical structure. It allows users to
 * navigate through bookmarked pages in a PDF document using the
 * android-pdf-viewer library. This adapter handles both parent bookmarks
 * and their child bookmarks using a nested RecyclerView.
 * <p>
 * How It Works:
 * 1. The constructor initializes the adapter with a list of PdfDocument.Bookmark
 * objects and a reference to PDFView for navigation.
 * 2. onCreateViewHolder() inflates the bookmark_row.xml layout and creates
 * a ViewHolder for each bookmark.
 * 3. onBindViewHolder() populates the ViewHolder with bookmark data:
 * - Displays the bookmark title and corresponding page number.
 * - Click action jumps to the respective page in PDFView and dismisses
 * the bookmark dialog.
 * - If the bookmark has child bookmarks, a nested RecyclerView
 * (handled by BookmarkAdapter4) is initialized.
 * - If no children exist, the adapter is cleared to avoid incorrect data.
 * 4. BookmarkViewHolder holds references to the bookmark title, page number,
 * and the nested RecyclerView.
 * 5. The RecyclerView uses a LinearLayoutManager for vertical scrolling.
 * <p>
 * Features:
 * ✅ Displays a hierarchical bookmark list for PDFs.
 * ✅ Supports nested bookmarks with child items.
 * ✅ Allows users to jump to bookmarked pages in PDFView.
 * ✅ Optimized to prevent redundant adapter re-assignments.
 * <p>
 * This adapter enhances the usability of PDF navigation by organizing
 * bookmarks efficiently and allowing easy access to specific sections.
 * ==========================================================
 */

public class BookmarkAdapter3 extends RecyclerView.Adapter<BookmarkAdapter3.BookmarkViewHolder> {
    private final List<PdfDocument.Bookmark> bookmarks;
    private final PDFView pdfView;

    public BookmarkAdapter3(List<PdfDocument.Bookmark> bookmarks, PDFView pdfView) {
        this.bookmarks = bookmarks;
        this.pdfView = pdfView;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_row, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        PdfDocument.Bookmark bookmark = bookmarks.get(position);
        holder.title.setText(bookmark.getTitle());
        holder.page.setText(String.valueOf(bookmark.getPageIdx() + 1));

        // Handle parent item click
        holder.itemView.setOnClickListener(v -> {
            pdfView.jumpTo((int) bookmark.getPageIdx());
            pdfView.dismissBookmarkDialog();
        });

        // Set the child RecyclerView for each parent item (avoid re-setting adapter if it's already set)
        if (bookmark.hasChildren()) {
            // Only set adapter if it's not already set
            if (holder.recyclerView.getAdapter() == null) {
                BookmarkAdapter4 bookmarkAdapter = new BookmarkAdapter4(bookmark.getChildren(), pdfView);
                holder.recyclerView.setAdapter(bookmarkAdapter);
            }
        } else {
            // Clear the adapter in case there are no children, to avoid leftover data
            holder.recyclerView.setAdapter(null);
        }
    }

    @Override
    public int getItemCount() {
        return bookmarks.size();
    }

    public static class BookmarkViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView page;
        RecyclerView recyclerView;

        public BookmarkViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            page = itemView.findViewById(R.id.page_no);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
}
