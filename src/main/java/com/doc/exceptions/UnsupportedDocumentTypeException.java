package com.doc.exceptions;

import java.io.IOException;

public class UnsupportedDocumentTypeException extends IOException {

    public UnsupportedDocumentTypeException(String message){
        super(message);
    }
}
