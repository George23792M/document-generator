package com.doc.service;

import com.doc.exceptions.WebScrapingException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class WebCrawlerService {

    @Async
    public CompletableFuture<String> scrapeWebsite(String url) {
        String webSiteBdyContent = null;
        CompletableFuture<String> future = new CompletableFuture<>();
        try {
            org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
            Elements body = doc.select("body");
            webSiteBdyContent = body.text();
            future.complete(webSiteBdyContent);
            log.info("Website content fetched successfully!");
        } catch (Exception ex) {
            if (ex instanceof WebScrapingException) {
                log.error("Failed to scrape website: {}", ex.getStackTrace());
                future.completeExceptionally(new WebScrapingException(ex.getMessage()));
            } else {
                log.error("Failed to scrape website: {}", url);

            }
        }
        return future;
    }
}
