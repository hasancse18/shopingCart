package com.custominterceptor.demo.shpingcart.requestbean;

import lombok.Data;

@Data
public class RequestBean {
    Integer find;
    Integer delete;
    Integer update;
    Integer insert;
    Object data;
}
