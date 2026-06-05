package com.ecommerce.controller;

import com.ecommerce.dto.CartResponse;
import com.ecommerce.model.Cart;
import com.ecommerce.service.CartService;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    private Long getUserId(UserDetails userDetails) {
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found")).getId();
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart(
            @AuthenticationPrincipal UserDetails userDetails) {
        Cart cart = cartService.getCart(getUserId(userDetails));
        return ResponseEntity.ok(cartService.mapToCartResponse(cart));
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        Cart cart = cartService.addToCart(getUserId(userDetails), productId, quantity);
        return ResponseEntity.ok(cartService.mapToCartResponse(cart));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<CartResponse> removeFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId) {
        Cart cart = cartService.removeFromCart(getUserId(userDetails), productId);
        return ResponseEntity.ok(cartService.mapToCartResponse(cart));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(
            @AuthenticationPrincipal UserDetails userDetails) {
        cartService.clearCart(getUserId(userDetails));
        return ResponseEntity.ok("Cart cleared successfully");
    }
}