package com.custominterceptor.demo.shpingcart.service.cart;

import com.custominterceptor.demo.shpingcart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RadishService {
    @Autowired
    private RedisTemplate<String, Cart> redisTemplate;

    private static final String CART_KEY_PREFIX = "cart:";

    public Cart getCart(Long userId) {
        Cart cart = redisTemplate.opsForValue().get(CART_KEY_PREFIX + userId);
        if (cart == null) {
            cart = new Cart(userId);
        }
        return cart;
    }

    public void saveCart(Cart cart) {
        redisTemplate.opsForValue().set(CART_KEY_PREFIX + cart.getUserId(), cart);
    }

    public void deleteCart(Long userId) {
        redisTemplate.delete(CART_KEY_PREFIX + userId);
    }
}
