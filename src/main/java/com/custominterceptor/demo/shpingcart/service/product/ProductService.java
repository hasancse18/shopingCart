package com.custominterceptor.demo.shpingcart.service.product;

import com.custominterceptor.demo.shpingcart.bean.ProductBean;
import com.custominterceptor.demo.shpingcart.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductBean product);
    List<Product> getAllProduct();
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProductById(ProductBean product, Long id);

    List<Product> getProductByCategory(String categoryId);
    List<Product>getProductByBrand(String brandId);
    List<Product> getProductByCategoryAndBrand(String category, String brand);
    List<Product> getProductByName(String name);
    List<Product> getProductByBrandAndName(String brand, String name);
    Long countProductByBrandAndName(String brand, String name);
    public ProductBean convertToDto(Product product);
    public List<ProductBean> getConvertedProducts(List<Product> products);
}
