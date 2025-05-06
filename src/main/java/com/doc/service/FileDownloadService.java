package com.doc.service;

import com.doc.exceptions.UnsupportedDocumentTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class FileDownloadService {

    private final Map<String, DocumentGeneratorFactory> documentGeneratorFactoryMap;

    public FileDownloadService(Map<String, DocumentGeneratorFactory> documentGeneratorFactoryMap) {
        this.documentGeneratorFactoryMap = documentGeneratorFactoryMap;
    }

    @Async
    public CompletableFuture<Void> asyncDownloadFile(String formattedText, String fileName, String type) throws IOException {

        String currentThreadName = Thread.currentThread().getName();

        log.info("asyncDownloadFile started for file '{}' in thread: {}", fileName, currentThreadName);


        final String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\files";

        Path path = Paths.get(filePath, fileName);

        Files.createDirectories(path.getParent());

        DocumentGeneratorFactory factory = documentGeneratorFactoryMap.get(type.toLowerCase());

        if(null == factory){
            String errorMessage = "Invalid File Type";
            throw new UnsupportedDocumentTypeException(errorMessage);
        }

        DocumentGenerator generator = factory.createGenerator(type.toLowerCase());
        generateTypeDocument(generator, formattedText, path);
        log.info("asyncDownloadFile completed for file '{}' in thread: {}", fileName, currentThreadName);
        return CompletableFuture.completedFuture(null);


    }


    private void generateTypeDocument(DocumentGenerator generator, String formattedText, Path path) throws IOException {

        if (generator instanceof PdfGenerator) {
            ((PdfGenerator) generator).generatePdfFile(formattedText, path.toString());
        } else if (generator instanceof WordDocGenerator) {
            ((WordDocGenerator) generator).generateWordFile(formattedText, path.toString());
        } else {
            String errorMessage = "Unsupported document generator type";
            log.error(errorMessage);
            throw new UnsupportedDocumentTypeException(errorMessage);

        }

    }
}
