package com.custominterceptor.demo.shpingcart.service.product;

import com.custominterceptor.demo.shpingcart.bean.ProductBean;
import com.custominterceptor.demo.shpingcart.model.Product;
import com.custominterceptor.demo.shpingcart.requestbean.RequestBean;
import com.custominterceptor.demo.shpingcart.response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductConfigService {
    @Autowired
    ProductService productService;
    ObjectMapper objectMapper = new ObjectMapper();

    ApiResponse response = new ApiResponse();

    public ApiResponse execute(String json){
        try{
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
                    //return objectMapper.writeValueAsString(response);
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

        }catch(Exception e){
            e.printStackTrace();
        }


        return response;
    }
}
