package com.custominterceptor.demo.shpingcart.bean;

import com.custominterceptor.demo.shpingcart.model.CartEntity;
import lombok.Data;

@Data
public class OrderBean {
    private Long userId;
    private CartEntity cartEntity;
}
