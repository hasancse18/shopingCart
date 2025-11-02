package com.custominterceptor.demo.shpingcart.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class Cart implements Serializable {

    private Long userId;
    private Map<Long, CartItem> items = new HashMap<>();
    private BigDecimal totalAmount = BigDecimal.ZERO;

    public Cart() {}

    public Cart(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Map<Long, CartItem> getItems() {
        return items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    // Add or update product in cart
    public void addItem(Product product, int quantity) {
        CartItem item = items.get(product.getId());
        if (item == null) {
            item = new CartItem(product, quantity);
        } else {
            item.setQuantity(item.getQuantity() + quantity);
            item.updateSubtotal();
        }
        items.put(product.getId(), item);
        recalcTotal();
    }

    // Remove product
    public void removeItem(Long productId) {
        items.remove(productId);
        recalcTotal();
    }

    private void recalcTotal() {
        totalAmount = items.values().stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
