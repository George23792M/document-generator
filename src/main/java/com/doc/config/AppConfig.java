package com.doc.config;

import com.doc.service.DocumentGeneratorFactory;
import com.doc.service.PdfGeneratorFactory;
import com.doc.service.TextFormatter;
import com.doc.service.WhiteSpaceFormatter;
import com.doc.service.WordDocGeneratorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean("pdf")
    public DocumentGeneratorFactory documentGeneratorPdfFactory() {
        return new PdfGeneratorFactory();
    }

    @Bean("docx")
    public DocumentGeneratorFactory documentGeneratorWordFactory() {
        return new WordDocGeneratorFactory();
    }

    @Bean
    public TextFormatter textFormatter() {
        return new WhiteSpaceFormatter();
    }

}
