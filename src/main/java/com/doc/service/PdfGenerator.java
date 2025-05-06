package com.doc.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class PdfGenerator implements  DocumentGenerator{
    @Override
    public void generateDocument(String formattedText, OutputStream outputStream) {

        PdfDocument pdf = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdf); // Create Document object
        document.add(new Paragraph(formattedText)); // Add to Document
        document.close(); // Close Document
        pdf.close(); // close the pdf document.
    }

    public void generatePdfFile(String formattedText, String filePath) throws IOException {
        try(FileOutputStream outputStream = new FileOutputStream(filePath)){
            generateDocument(formattedText, outputStream);
        }
    }
}
