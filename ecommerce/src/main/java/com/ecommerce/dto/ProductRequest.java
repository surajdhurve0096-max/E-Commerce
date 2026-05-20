package com.ecommerce.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;
import lombok.*;

 @NoArgsConstructor @AllArgsConstructor
public class ProductRequest {
    @NotBlank private String name;
    private String description;
    @NotNull @Positive private BigDecimal price;
    @NotNull @Min(0) private Integer stock;
    private String category;
    private String imageUrl;
}
