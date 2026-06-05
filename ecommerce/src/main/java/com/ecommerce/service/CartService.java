package com.ecommerce.service;



import com.ecommerce.dto.CartItemResponse;
import com.ecommerce.dto.CartResponse;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart getCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    Cart cart = Cart.builder().user(user).build();
                    return cartRepository.save(cart);
                });
    }

    public Cart addToCart(Long userId, Long productId, int quantity) {
        Cart cart = getCart(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock for: " + product.getName());
        }

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart).product(product).quantity(quantity).build();
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    public Cart removeFromCart(Long userId, Long productId) {
        Cart cart = getCart(userId);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return cartRepository.save(cart);
    }

    public Cart clearCart(Long userId) {
        Cart cart = getCart(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
    
    public CartResponse mapToCartResponse(Cart cart) {
        List<CartItemResponse> itemResponses = cart.getItems().stream()
                .map(item -> CartItemResponse.builder()
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .category(item.getProduct().getCategory())
                        .price(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .subtotal(item.getProduct().getPrice()
                                .multiply(BigDecimal.valueOf(item.getQuantity())))
                        .build())
                .collect(Collectors.toList());

        BigDecimal totalPrice = itemResponses.stream()
                .map(CartItemResponse::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CartResponse.builder()
                .cartId(cart.getId())
                .username(cart.getUser().getUsername())
                .items(itemResponses)
                .totalPrice(totalPrice)
                .totalItems(itemResponses.size())
                .build();
    }
}
