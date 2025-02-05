package com.bellalhossainmondal.pdfviewer.meta;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.bellalhossainmondal.pdfium.PdfDocument;
import com.bellalhossainmondal.pdfviewer.PDFView;
import com.bellalhossainmondal.pdfviewer.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * ==========================================================
 * Project: android-pdf-viewer
 * File: DocumentMetaData.java
 * Created by: Bellal Hossain Mondal
 * Date: 05/01/2025
 * Email: bellalmondalofficial@gmail.com
 * Description:
 * <p>
 * DocumentMetaData is a BottomSheetDialog that displays metadata
 * information of a PDF document, such as title, author, subject,
 * keywords, creator, producer, and creation/modification dates.
 * <p>
 * How It Works:
 * 1. The constructor initializes the BottomSheetDialog with the given
 * context and a reference to PDFView.
 * 2. onCreate() sets up the dialog layout (meta_layout.xml).
 * 3. Retrieves metadata information from the PDFView using getDocumentMeta().
 * 4. Finds corresponding TextViews from the layout and sets their values
 * with metadata such as title, author, subject, and more.
 * 5. Displays the metadata in a user-friendly format within the BottomSheetDialog.
 * <p>
 * Features:
 * ✅ Displays essential PDF metadata in a BottomSheetDialog.
 * ✅ Uses PDFView's getDocumentMeta() to retrieve information.
 * ✅ Allows users to view details like title, author, subject, and more.
 * ✅ Provides a structured and informative way to display document details.
 * <p>
 * This component enhances the user experience by allowing quick access
 * to PDF metadata in an intuitive bottom sheet layout.
 * ==========================================================
 */

public class DocumentMetaData extends BottomSheetDialog {
    private final PDFView pdfView;

    public DocumentMetaData(Context context, PDFView pdfView) {
        super(context);
        this.pdfView = pdfView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meta_layout);

        // Get the list of bookmarks from the PDFView
        PdfDocument.Meta documentMeta = pdfView.getDocumentMeta();

        TextView titleTextView = findViewById(R.id.title);
        if (titleTextView != null) {
            titleTextView.setText(documentMeta.getTitle());
        }

        TextView authorTextView = findViewById(R.id.author);
        if (authorTextView != null) {
            authorTextView.setText(documentMeta.getAuthor());
        }

        TextView subjectTextView = findViewById(R.id.subject);
        if (subjectTextView != null) {
            subjectTextView.setText(documentMeta.getSubject());
        }

        TextView keywordsTextView = findViewById(R.id.keywords);
        if (keywordsTextView != null) {
            keywordsTextView.setText(documentMeta.getKeywords());
        }

        TextView creatorTextView = findViewById(R.id.creator);
        if (creatorTextView != null) {
            creatorTextView.setText(documentMeta.getCreator());
        }

        TextView producerTextView = findViewById(R.id.producer);
        if (producerTextView != null) {
            producerTextView.setText(documentMeta.getProducer());
        }

        TextView creationDateTextView = findViewById(R.id.creation_date);
        if (creationDateTextView != null) {
            creationDateTextView.setText(documentMeta.getCreationDate());
        }

        TextView modificationDateTextView = findViewById(R.id.modification_date);
        if (modificationDateTextView != null) {
            modificationDateTextView.setText(documentMeta.getCreationDate());
        }
    }
}