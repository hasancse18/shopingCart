package com.custominterceptor.demo.shpingcart.repository;

import com.custominterceptor.demo.shpingcart.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findByUserId(Long userId);
}
