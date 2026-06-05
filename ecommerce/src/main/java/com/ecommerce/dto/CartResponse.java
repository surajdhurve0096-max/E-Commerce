package com.ecommerce.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private Long cartId;
    private String username;
    private List<CartItemResponse> items;
    private BigDecimal totalPrice;
    private Integer totalItems;
}