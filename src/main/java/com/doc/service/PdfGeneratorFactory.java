package com.doc.service;


public class PdfGeneratorFactory implements DocumentGeneratorFactory{

    @Override
    public DocumentGenerator createGenerator(String documentType) {
        return new PdfGenerator();
    }
}
