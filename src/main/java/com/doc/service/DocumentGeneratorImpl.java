package com.doc.service;

import com.doc.model.DocumentRequest;
import com.doc.model.DocumentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;


@Service
@Slf4j
public class DocumentGeneratorImpl implements DocumentService {

    private final TextFormatter textFormatter;

    private final DocumentGeneratorImpl self;

    private final WebCrawlerService webCrawlerService;

    private final FileDownloadService fileDownloadService;

    public DocumentGeneratorImpl(TextFormatter textFormatter,
                                 WebCrawlerService webCrawlerService,
                                 FileDownloadService fileDownloadService) {
        this.textFormatter = textFormatter;
        this.self = this;
        this.webCrawlerService = webCrawlerService;
        this.fileDownloadService = fileDownloadService;
    }

    @Override
    public CompletableFuture<DocumentResponse> documentGeneration(DocumentRequest request) {

        CompletableFuture<String> contentFuture = webCrawlerService.scrapeWebsite(request.getUrl());

        return contentFuture.thenCompose(content -> { // Use thenCompose to chain asynchronous operations
            String formattedText = textFormatter.format(content); // Now 'content' holds the actual scraped data
            try {
                return fileDownloadService.asyncDownloadFile(formattedText, request.getFileName(), request.getType())
                        .thenApply(v -> new DocumentResponse(request.getFileName(), "File Request Completed Successfully"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
