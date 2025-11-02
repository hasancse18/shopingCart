package com.custominterceptor.demo.shpingcart.service.cart;

import com.custominterceptor.demo.shpingcart.model.Cart;
import com.custominterceptor.demo.shpingcart.model.Product;
import com.custominterceptor.demo.shpingcart.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl
{
    @Autowired
    private RadishService redisCartService;

    @Autowired
    private ProductService productService; // Assume you already have this

    public Cart addToCart(Long userId, Long productId, int quantity) {
        Product product = productService.getProductById(productId);
        Cart cart = redisCartService.getCart(userId);
        cart.addItem(product, quantity);
        redisCartService.saveCart(cart);
        return cart;
}

    public Cart removeFromCart(Long userId, Long productId) {
        Cart cart = redisCartService.getCart(userId);
        cart.removeItem(productId);
        redisCartService.saveCart(cart);
        return cart;
    }

public Cart getCart(Long userId) {
    return redisCartService.getCart(userId);
}

public void checkout(Long userId) {
    Cart cart = redisCartService.getCart(userId);
    // ✅ Here you would persist to DB
    // cartRepository.save(mapToEntity(cart));
    System.err.println("Cart: "+ cart);
    //redisCartService.deleteCart(userId);
}
}
