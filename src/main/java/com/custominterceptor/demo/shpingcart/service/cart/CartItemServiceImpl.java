package com.custominterceptor.demo.shpingcart.service.cart;

import com.custominterceptor.demo.shpingcart.model.CartItem;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemServiceImpl implements CartItemService{
    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {

    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {

    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {

    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId) {
        return null;
    }
}
