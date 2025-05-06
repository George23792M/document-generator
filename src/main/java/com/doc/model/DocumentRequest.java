package com.doc.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequest {

   @NotBlank(message = "The file name cannot be blank or empty")
   private String fileName;

   @NotBlank(message = "The url cannot be blank or empty")
   private String url;

   @NotBlank(message = "The type of the document cannot be blank or empty")
   private String type;

}
