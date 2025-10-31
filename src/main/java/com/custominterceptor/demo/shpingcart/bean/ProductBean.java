package com.custominterceptor.demo.shpingcart.bean;
import com.custominterceptor.demo.shpingcart.model.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductBean {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
    private List<ImageBean> imageList;
}
