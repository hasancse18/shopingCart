package com.custominterceptor.demo.shpingcart.bean;
import com.custominterceptor.demo.shpingcart.model.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductBean extends BaseBean {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
    private List<ImageBean> imageList;
}
