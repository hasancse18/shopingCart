package com.custominterceptor.demo.shpingcart.controller;

import com.custominterceptor.demo.shpingcart.model.Cart;
import com.custominterceptor.demo.shpingcart.service.cart.CartConfigService;
import com.custominterceptor.demo.shpingcart.service.cart.CartServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.prefix}/")
public class CartController {

    @Autowired
    CartConfigService cartConfigService;

    @PostMapping("cart")
    ResponseEntity<?>cartController(@RequestBody String json) throws JsonProcessingException {
        return ResponseEntity.ok(cartConfigService.execute(json));
    }





    /*@Autowired
    private CartServiceImpl cartService;

    @PostMapping("/{userId}/add/{productId}")
    public Cart addToCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity) {
        return cartService.addToCart(userId, productId, quantity);
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public Cart removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        return cartService.removeFromCart(userId, productId);
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/{userId}/checkout")
    public String checkout(@PathVariable Long userId) {
        cartService.checkout(userId);
        return "Checkout successful!";
    }*/
}
