package com.doc.controller;

import com.doc.model.DocumentRequest;
import com.doc.model.DocumentResponse;
import com.doc.service.DocumentGeneratorImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController
@RequiredArgsConstructor
@RequestMapping("/document-generation")
@Slf4j
public class DocumentController {

    private final DocumentGeneratorImpl service;
    @GetMapping
    public String health(){
        return "Application is Up!";
    }

    @PostMapping("/process")
    public ResponseEntity<?> generateDocument(@RequestBody @Valid DocumentRequest request) throws Exception {

        CompletableFuture<DocumentResponse> documentResponse = service.documentGeneration(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(documentResponse.get());
    }

}
