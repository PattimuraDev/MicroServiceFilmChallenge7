package org.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponseJson {
    @Schema(example = "Operasi berhasil")
    private String message;
    @Schema(example = "200")
    private String statusCode;
}
