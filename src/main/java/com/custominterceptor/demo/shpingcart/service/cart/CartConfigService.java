package com.custominterceptor.demo.shpingcart.service.cart;

import com.custominterceptor.demo.shpingcart.bean.CartBean;
import com.custominterceptor.demo.shpingcart.model.Cart;
import com.custominterceptor.demo.shpingcart.requestbean.RequestBean;
import com.custominterceptor.demo.shpingcart.response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartConfigService {

    @Autowired
    CartService cartService;
    ObjectMapper objectMapper = new ObjectMapper();
    ApiResponse apiResponse = new ApiResponse();

    public String execute(String json) throws JsonProcessingException {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        RequestBean requestBean = objectMapper.readValue(json, RequestBean.class);

        JsonNode dataNode = objectMapper.valueToTree(requestBean.getData());
        CartBean cartBean = objectMapper.treeToValue(dataNode, CartBean.class);




        if(requestBean.getFind()==1)
        {
            if(cartBean.getQuery().equalsIgnoreCase("getCart"))
            {
                Cart cart = cartService.getCart(cartBean.getUserId());
                apiResponse.setMessage("Cart Data");
                apiResponse.setData(cart);
            }
        }
        else if(requestBean.getDelete()==1)
        {
            if(cartBean.getQuery().equalsIgnoreCase("removeById"))
            {
                Cart cart = cartService.removeFromCart(cartBean.getUserId(), cartBean.getProductId());
                apiResponse.setMessage("Cart Data");
                apiResponse.setData(cart);
            }
        }
        else if(requestBean.getInsert() == 1)
        {
            if(cartBean.getQuery().equalsIgnoreCase("addProduct"))
            {
                Cart cart = cartService.addToCart(cartBean.getUserId(), cartBean.getProductId(), cartBean.getQuantity());
                apiResponse.setMessage("Cart Data");
                apiResponse.setData(cart);
            }
            else if(cartBean.getQuery().equalsIgnoreCase("processOrder"))
            {
                cartService.checkout(cartBean.getUserId());
                apiResponse.setMessage("Checkout successful!");
            }
        }
        return objectMapper.writeValueAsString(apiResponse);
    }
}
