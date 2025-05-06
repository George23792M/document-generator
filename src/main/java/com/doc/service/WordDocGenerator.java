package com.doc.service;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WordDocGenerator implements DocumentGenerator {
    @Override
    public void generateDocument(String text, OutputStream outputStream) throws IOException {

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        document.write(outputStream);
        document.close();
    }

    public void generateWordFile(String formattedText, String filePath) throws IOException {
        try(FileOutputStream outputStream = new FileOutputStream(filePath)){
            generateDocument(formattedText, outputStream);
        }
    }
}
