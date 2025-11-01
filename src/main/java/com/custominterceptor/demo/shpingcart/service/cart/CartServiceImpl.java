package com.custominterceptor.demo.shpingcart.service.cart;

import com.custominterceptor.demo.shpingcart.exception.ResourceNotFoundException;
import com.custominterceptor.demo.shpingcart.model.Cart;
import com.custominterceptor.demo.shpingcart.repository.CartItemRepository;
import com.custominterceptor.demo.shpingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;
    private final AtomicLong cartIdGenerator = new AtomicLong(0);

    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cart Not Found"));
       return cart;
    }

    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllCartById(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);
    }

    @Override
    public Long initializeNewCart() {
        Cart newCart = new Cart();
        Long id = cartIdGenerator.incrementAndGet();
        newCart.setId(id);
        return cartRepository.save(newCart).getId();
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
