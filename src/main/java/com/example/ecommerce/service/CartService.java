package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartItemRequest;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired private CartItemRepository cartRepo;
    @Autowired private ProductRepository productRepo;

    public List<CartItem> getCart(User user) {
        return cartRepo.findByUser(user);
    }

    public void addToCart(User user, CartItemRequest req) {
        Product product = productRepo.findById(req.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = cartRepo.findByUserAndProductId(user, product.getId())
                .orElse(new CartItem());

        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(item.getId() == null ? req.getQuantity() : item.getQuantity() + req.getQuantity());

        cartRepo.save(item);
    }

    public void removeFromCart(User user, Long productId) {
        cartRepo.findByUserAndProductId(user, productId).ifPresent(cartRepo::delete);
    }

    public void clearCart(User user) {
        cartRepo.deleteByUser(user);
    }
}
