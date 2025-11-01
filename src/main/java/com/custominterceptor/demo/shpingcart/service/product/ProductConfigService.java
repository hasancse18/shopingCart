package com.custominterceptor.demo.shpingcart.service.product;

import com.custominterceptor.demo.shpingcart.bean.ProductBean;
import com.custominterceptor.demo.shpingcart.model.Product;
import com.custominterceptor.demo.shpingcart.requestbean.RequestBean;
import com.custominterceptor.demo.shpingcart.response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProductConfigService {
    @Autowired
    ProductService productService;
    ObjectMapper objectMapper = new ObjectMapper();

    ApiResponse response = new ApiResponse();

    public ApiResponse execute(String json) throws JsonProcessingException {

            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            RequestBean requestBean = objectMapper.readValue(json, RequestBean.class);
            JsonNode dataNode = objectMapper.valueToTree(requestBean.getData());
            ProductBean productBean = objectMapper.treeToValue(dataNode, ProductBean.class);
            String query = productBean.getQuery();


            if(requestBean.getFind()==1){

                if(query.equalsIgnoreCase("getAll")){
                    List<Product> products = productService.getAllProduct();
                    List<ProductBean> convertedProducts = productService.getConvertedProducts(products)
                            .stream().filter(Objects::nonNull).collect(Collectors.toList());

                    response.setData(convertedProducts);
                    response.setMessage("Get Product");
                    return response;

                }
                else if(query.equalsIgnoreCase("getProductById")){
                    Product product = productService.getProductById(productBean.getId());
                    ProductBean productById = productService.convertToDto(product);

                    response.setMessage("Get Single Product");
                    response.setData(productById);

                }
                else if (query.equalsIgnoreCase("productByBrandName"))
                {
                   List<Product> productByBrand = productService
                           .getProductByBrandAndName(productBean.getProductName(), productBean.getBrandName());

                    List<ProductBean> convertedProducts = productService.getConvertedProducts(productByBrand)
                            .stream().filter(Objects::nonNull).collect(Collectors.toList());

                    response.setData(convertedProducts);
                    response.setMessage("Get Product");
                }
                else if(query.equalsIgnoreCase("getProductByCategoryAndBrand"))
                {
                    List<Product> products = productService.getProductByCategoryAndBrand(productBean.getCategoryName(), productBean.getBrand());
                    if (products.isEmpty())
                    {
                        response.setMessage("No Data Present");
                        return response;
                    }
                    List<ProductBean> convertedProducts = productService.getConvertedProducts(products);
                    response.setData(convertedProducts);
                    response.setMessage("Product");
                }
                else if(query.equalsIgnoreCase("getProductByName"))
                {
                    List<Product> products = productService.getProductByName(productBean.getProductName());
                    if (products.isEmpty()) {
                        response.setMessage("No Data Present");
                        return response;
                    }
                    List<ProductBean> convertedProducts = productService.getConvertedProducts(products);
                    response.setData(convertedProducts);
                    response.setMessage("Product");

                }
                else if(query.equalsIgnoreCase("findProductByBrand"))
                {
                    List<Product> products = productService.getProductByBrand(productBean.getBrandName());
                    if (products.isEmpty()) {
                        response.setMessage("No Data Present");
                        return response;
                    }
                    List<ProductBean> convertedProducts = productService.getConvertedProducts(products);
                    response.setData(convertedProducts);
                    response.setMessage("Product");
                }
                else if(query.equalsIgnoreCase("countProductsByBrandAndName"))
                {
                    var productCount = productService.countProductByBrandAndName(productBean.getBrandName(), productBean.getProductName());
                    response.setMessage("Number Product Present Of "+productBean.getBrandName()+" "+productCount);
                    response.setData(productCount);
                }
                else
                {
                    response.setMessage("Correct your request json");
                }


            }
            else if(requestBean.getUpdate() == 1) {
                if(query.equalsIgnoreCase("updateById")){
                    Product upProduct = productService.updateProductById(productBean,productBean.getId());
                    response.setData(productService.convertToDto(upProduct));
                    response.setMessage("Update Success");
                }
            }
            else if(requestBean.getDelete() ==1){
                if(query.equalsIgnoreCase("deleteById")){
                    productService.deleteProductById(productBean.getId());
                    response.setData("Deleted Id"+productBean.getId());
                    response.setMessage("Delete Success");
                }

            }
            else if (requestBean.getInsert() ==1){

                if(query.equalsIgnoreCase("addProduct"))
                {
                    Product theProduct = productService.addProduct(productBean);
                    ProductBean addProduct = productService.convertToDto(theProduct);

                    response.setMessage("Add Product");
                    response.setData(addProduct);
                }
            }
            //System.err.println("productBean "+productBean);



        return response;
    }
}
