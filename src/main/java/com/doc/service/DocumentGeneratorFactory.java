package com.doc.service;

public interface DocumentGeneratorFactory {
    DocumentGenerator createGenerator(String documentType);
}
