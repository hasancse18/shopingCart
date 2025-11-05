package com.custominterceptor.demo.shpingcart.repository;

import com.custominterceptor.demo.shpingcart.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    CartEntity findByUserId(Long userId);
}
