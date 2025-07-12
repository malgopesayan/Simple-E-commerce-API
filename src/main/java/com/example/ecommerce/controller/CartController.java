package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartItemRequest;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public List<CartItem> viewCart(@AuthenticationPrincipal User user) {
        return cartService.getCart(user);
    }

    @PostMapping
    public void addItem(@AuthenticationPrincipal User user, @RequestBody CartItemRequest request) {
        cartService.addToCart(user, request);
    }

    @DeleteMapping("/{productId}")
    public void removeItem(@AuthenticationPrincipal User user, @PathVariable Long productId) {
        cartService.removeFromCart(user, productId);
    }

    @DeleteMapping
    public void clearCart(@AuthenticationPrincipal User user) {
        cartService.clearCart(user);
    }
}
