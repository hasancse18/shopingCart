package com.custominterceptor.demo.shpingcart.controller;

import com.custominterceptor.demo.shpingcart.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {


    

    /*@Autowired
    OrderService orderService;

    @GetMapping("/user")
    ResponseEntity<?> getOrder(@RequestParam Long userId)
    {
        return ResponseEntity.ok(orderService.getOrderByUserId(userId));
    }

    @PostMapping("/user")
    ResponseEntity<?> postOrder(@RequestParam Long userId)
    {
        return ResponseEntity.ok(orderService.postOrder(userId));
    }*/
}
