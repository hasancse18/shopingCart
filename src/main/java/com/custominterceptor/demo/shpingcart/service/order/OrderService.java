package com.custominterceptor.demo.shpingcart.service.order;

import com.custominterceptor.demo.shpingcart.bean.OrderBean;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    public OrderBean getOrderByUserId(Long userId);
    public OrderBean postOrder(Long userId);
}
