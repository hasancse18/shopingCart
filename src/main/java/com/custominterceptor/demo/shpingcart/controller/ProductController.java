package com.custominterceptor.demo.shpingcart.controller;

import com.custominterceptor.demo.shpingcart.bean.ProductBean;
import com.custominterceptor.demo.shpingcart.exception.ResourceNotFoundException;
import com.custominterceptor.demo.shpingcart.model.Product;
import com.custominterceptor.demo.shpingcart.requestbean.RequestBean;
import com.custominterceptor.demo.shpingcart.response.ApiResponse;
import com.custominterceptor.demo.shpingcart.service.product.ProductConfigService;
import com.custominterceptor.demo.shpingcart.service.product.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductConfigService productConfigService;
    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/product")
    public ResponseEntity<ApiResponse>productController(@RequestBody String jsonString) throws JsonProcessingException {
        return  ResponseEntity.ok(productConfigService.execute(jsonString));
    }
    
}
