package br.com.fiap.cp4.cp4_java.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String sector;

    @NotNull
    @Positive
    private Double size;

    @NotNull
    @Positive
    private Double unitPrice;
}