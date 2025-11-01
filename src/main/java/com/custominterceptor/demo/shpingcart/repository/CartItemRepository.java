package com.custominterceptor.demo.shpingcart.repository;

import com.custominterceptor.demo.shpingcart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllCartById(Long id);
}
