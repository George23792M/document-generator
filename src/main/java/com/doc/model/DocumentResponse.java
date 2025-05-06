package com.doc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentResponse {

    private String fileName;
    private String message;

}
