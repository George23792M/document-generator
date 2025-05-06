package com.doc.service;

import java.util.List;
import java.util.stream.Collectors;

public class WhiteSpaceFormatter implements TextFormatter{
    @Override
    public String format(String content) {

        // replaces one or more whitespaces characters (spaces, tabs, newlines) with a single space
        //trim() used to remove leading and trailing whitespaces
        //ensures consistent spacing and removing unnecessary white-spaces.
        String cleanedText = content.replaceAll("\\s+", " ").trim();
        List<String> paragraphs = List.of(cleanedText.split("\n\n"));

        return paragraphs.stream()
                .filter(paragraph -> !paragraph.isEmpty())
                .map(String::trim)
                .collect(Collectors.joining("\n\n"));

    }
}
