package com.doc.service;

import java.io.IOException;
import java.io.OutputStream;

public interface DocumentGenerator {

    void generateDocument(String text, OutputStream outputStream) throws IOException;

}
