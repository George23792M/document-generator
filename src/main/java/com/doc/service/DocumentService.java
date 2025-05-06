package com.doc.service;

import com.doc.model.DocumentRequest;
import com.doc.model.DocumentResponse;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface DocumentService {

    CompletableFuture<DocumentResponse> documentGeneration(DocumentRequest request) throws IOException;

}
