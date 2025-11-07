package com.custominterceptor.demo.shpingcart.controller;

import com.custominterceptor.demo.shpingcart.service.order.OrderConfigService;
import com.custominterceptor.demo.shpingcart.service.order.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}")
public class OrderController {

    @Autowired
    OrderConfigService orderConfigService;

    @PostMapping("/order")
    public ResponseEntity<?>orderController(@RequestBody String json) throws JsonProcessingException {
        return ResponseEntity.ok(orderConfigService.execute(json));
    }


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
