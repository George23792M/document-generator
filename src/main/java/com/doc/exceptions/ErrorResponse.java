package com.doc.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@AllArgsConstructor
@Builder

public class ErrorResponse {

    private int statusCode;
    private String message;
    private List<FieldErrorDto> fieldErrors;
}


