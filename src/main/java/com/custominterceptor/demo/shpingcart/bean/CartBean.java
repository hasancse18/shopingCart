package com.custominterceptor.demo.shpingcart.bean;

import lombok.Data;

@Data
public class CartBean extends BaseBean{
    private int quantity;
    private Long productId;
    private Long userId;
}
