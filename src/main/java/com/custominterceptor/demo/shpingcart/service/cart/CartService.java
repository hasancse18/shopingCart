package com.custominterceptor.demo.shpingcart.service.cart;

import com.custominterceptor.demo.shpingcart.model.Cart;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Cart getCart(Long id);
    void clearCart(Long id);

    Long initializeNewCart();
    Cart getCartByUserId(Long userId);
}
