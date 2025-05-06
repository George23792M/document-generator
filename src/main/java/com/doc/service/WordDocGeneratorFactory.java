package com.doc.service;


public class WordDocGeneratorFactory implements DocumentGeneratorFactory{
    @Override
    public DocumentGenerator createGenerator(String documentType) {
        return new WordDocGenerator();
    }
}
