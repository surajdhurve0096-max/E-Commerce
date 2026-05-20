package com.ecommerce.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CartRequest {
    private Long productId;
    private Integer quantity;
}
