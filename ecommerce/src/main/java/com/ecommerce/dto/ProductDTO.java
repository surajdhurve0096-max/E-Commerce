package com.ecommerce.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProductDTO {

    @Data
    public static class ProductRequest {
        @NotBlank private String name;
        private String description;
        @NotNull @DecimalMin("0.0") private BigDecimal price;
        @NotNull @Min(0) private Integer stock;
        private String category;
        private String imageUrl;
    }

    @Data
    public static class ProductResponse {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stock;
        private String category;
        private String imageUrl;
    }
}
