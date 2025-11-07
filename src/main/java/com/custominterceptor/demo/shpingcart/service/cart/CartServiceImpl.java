package com.custominterceptor.demo.shpingcart.service.cart;

import com.custominterceptor.demo.shpingcart.model.Cart;
import com.custominterceptor.demo.shpingcart.model.CartEntity;
import com.custominterceptor.demo.shpingcart.model.CartItem;
import com.custominterceptor.demo.shpingcart.model.Product;
import com.custominterceptor.demo.shpingcart.repository.CartRepository;
import com.custominterceptor.demo.shpingcart.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService
{
    @Autowired
    private RadishService redisCartService;

    @Autowired
    private ProductService productService;

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart addToCart(Long userId, Long productId, int quantity) {
        Product product = productService.getProductById(productId);
        Cart cart = redisCartService.getCart(userId);
        cart.addItem(product, quantity);
        redisCartService.saveCart(cart);
        return cart;
}

    @Override
    public Cart removeFromCart(Long userId, Long productId) {
        Cart cart = redisCartService.getCart(userId);
        cart.removeItem(productId);
        redisCartService.saveCart(cart);
        return cart;
    }

    @Override
    public Cart getCart(Long userId)
    {
        return redisCartService.getCart(userId);
    }




    @Override
    public CartEntity checkout(Long userId) {
        CartEntity cartEntity = new CartEntity();
        Cart cart = redisCartService.getCart(userId);
        cartEntity =mapToCartEntity(cart);
        // ✅ Here you would persist to DB
        cartEntity= cartRepository.save(cartEntity);
        redisCartService.deleteCart(userId);
        System.err.println("Cart: "+ cartEntity);

        return cartEntity;
    }

    public CartEntity mapToCartEntity(Cart cart)
    {
        CartEntity cartEntity = new CartEntity();
        var cartItem = cart.getItems();
        List<CartItem> items = new ArrayList<>(cartItem.values());
        cartEntity.setItems(items);
        cartEntity.setTotalAmount(cart.getTotalAmount());
        cartEntity.setUserId(cart.getUserId());
        return cartEntity;
    }


}
