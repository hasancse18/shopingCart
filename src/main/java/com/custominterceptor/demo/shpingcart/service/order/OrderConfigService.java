package com.custominterceptor.demo.shpingcart.service.order;

import com.custominterceptor.demo.shpingcart.bean.OrderBean;
import com.custominterceptor.demo.shpingcart.requestbean.RequestBean;
import com.custominterceptor.demo.shpingcart.response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderConfigService {
    @Autowired
    OrderService orderService;
    ObjectMapper objectMapper = new ObjectMapper();

    ApiResponse apiResponse = new ApiResponse();

    public String execute(String json) throws JsonProcessingException {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        RequestBean requestBean = objectMapper.readValue(json, RequestBean.class);
        JsonNode dataNode = objectMapper.valueToTree(requestBean.getData());

        OrderBean orderBean = objectMapper.treeToValue(dataNode, OrderBean.class);

        if(requestBean.getFind() == 1)
        {
            if(orderBean.getQuery().equalsIgnoreCase("orderByUserId"))
            {
                OrderBean order = orderService.getOrderByUserId(orderBean.getUserId());
                apiResponse.setMessage("Order Data");
                apiResponse.setData(order);
            }
        }
        else if(requestBean.getInsert()==1)
        {
            if(orderBean.getQuery().equalsIgnoreCase("postOrder"))
            {
                OrderBean order = orderService.postOrder(orderBean.getUserId());
                apiResponse.setMessage("Order Data");
                apiResponse.setData(order);
            }
        }

        return objectMapper.writeValueAsString(apiResponse);
    }

}
