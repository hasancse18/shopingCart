package com.custominterceptor.demo.shpingcart.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse {
    String message;
    Object data;
}
