package com.ecommerce.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;
import lombok.*;

 @NoArgsConstructor @AllArgsConstructor
public class ProductRequest {
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@NotBlank private String name;
    private String description;
    @NotNull @Positive private BigDecimal price;
    @NotNull @Min(0) private Integer stock;
    private String category;
    private String imageUrl;
}
