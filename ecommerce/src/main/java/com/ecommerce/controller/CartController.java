package com.ecommerce.controller;

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
    public ResponseEntity<Cart> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.getCart(getUserId(userDetails)));
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestParam Long productId,
                                           @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addToCart(getUserId(userDetails), productId, quantity));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Cart> removeFromCart(@AuthenticationPrincipal UserDetails userDetails,
                                                @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeFromCart(getUserId(userDetails), productId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        cartService.clearCart(getUserId(userDetails));
        return ResponseEntity.ok("Cart cleared");
    }
}
