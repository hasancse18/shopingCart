package com.custominterceptor.demo.shpingcart.service.cart;

import com.custominterceptor.demo.shpingcart.model.Cart;
import com.custominterceptor.demo.shpingcart.model.CartEntity;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Cart getCart(Long id);
    CartEntity checkout(Long userId);
    Cart removeFromCart(Long userId, Long productId);
    Cart addToCart(Long userId, Long productId, int quantity);
}
