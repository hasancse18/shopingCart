package com.custominterceptor.demo.shpingcart.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BaseBean {
    String query;
    String brandName;
    String productName;
}
