package com.custominterceptor.demo.shpingcart.service.product;

import com.custominterceptor.demo.shpingcart.bean.ImageBean;
import com.custominterceptor.demo.shpingcart.bean.ProductBean;
import com.custominterceptor.demo.shpingcart.exception.ProductNotFoundException;
import com.custominterceptor.demo.shpingcart.model.Category;
import com.custominterceptor.demo.shpingcart.model.Image;
import com.custominterceptor.demo.shpingcart.model.Product;
import com.custominterceptor.demo.shpingcart.repository.CategoryRepository;
import com.custominterceptor.demo.shpingcart.repository.ImageRepository;
import com.custominterceptor.demo.shpingcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    @Transactional
    public Product addProduct(ProductBean productBean)
    {


        Category category = Optional.ofNullable(categoryRepository
                .findByName(productBean
                        .getCategory().getName())).orElseGet(()->{
                            Category newCategory = new Category(productBean.getCategory().getName());
                            return categoryRepository.save(newCategory);
        });
        productBean.setCategory(category);
        return productRepository.save(createProduct(productBean, category));

    }

    private Product createProduct(ProductBean productBean, Category category)
    {
        Product product = new Product();
        product.setName(productBean.getName());
        product.setBrand(productBean.getBrand());
        product.setPrice(productBean.getPrice());
        product.setInventory(productBean.getInventory());
        product.setCategory(category);

        return product;
    }

    @Override
    public List<Product> getAllProduct()
    {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id)
    {
        return productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product Not Found"));
    }

    @Override
    public void deleteProductById(Long id)
    {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        ()->{new ProductNotFoundException("Product Not Found");
        });
    }

    @Override
    public Product updateProductById(ProductBean product, Long id)
    {
           return productRepository.findById(id)
                   .map(existingProduct ->
                           updateExistingProduct(existingProduct, product))
                   .map(productRepository :: save)
                   .orElseThrow(()->new ProductNotFoundException("Product Not Found"));

    }

    private Product updateExistingProduct(Product existingProduct, ProductBean productBean){

        existingProduct.setName(productBean.getName());
        existingProduct.setInventory(productBean.getInventory());
        existingProduct.setBrand(productBean.getBrand());
        existingProduct.setDescription(productBean.getDescription());
        existingProduct.setPrice(productBean.getPrice());

        Category category = categoryRepository.findByName(productBean.getCategory().getName());

        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public List<Product> getProductByCategory(String category)
    {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductByBrand(String brandId)
    {
        return productRepository.findByBrand(brandId);
    }

    @Override
    public List<Product> getProductByCategoryAndBrand(String category, String brand)
    {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductByBrandAndName(String brand, String name)
    {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public ProductBean convertToDto(Product product){
        ProductBean productBean = modelMapper.map(product, ProductBean.class);
        List<Image> image = imageRepository.findByProductId(product.getId());
        List<ImageBean> imageBeanList = image.stream()
                .map(images-> modelMapper.map(images, ImageBean.class))
                .toList();

        productBean.setImageList(imageBeanList);
        return productBean;
    }

    @Override
    public List<ProductBean> getConvertedProducts(List<Product> products){
        return products.stream().map(this::convertToDto).toList();
    }
}
