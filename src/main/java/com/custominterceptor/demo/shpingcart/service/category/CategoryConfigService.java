package com.custominterceptor.demo.shpingcart.service.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryConfigService {

    @Autowired
    CategoryService categoryService;

    ObjectMapper objectMapper = new ObjectMapper();
}
