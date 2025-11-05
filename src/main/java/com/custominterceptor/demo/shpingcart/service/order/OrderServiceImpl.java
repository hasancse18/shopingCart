package com.custominterceptor.demo.shpingcart.service.order;

import com.custominterceptor.demo.shpingcart.bean.OrderBean;
import com.custominterceptor.demo.shpingcart.model.CartEntity;
import com.custominterceptor.demo.shpingcart.model.OrderEntity;
import com.custominterceptor.demo.shpingcart.repository.CartRepository;
import com.custominterceptor.demo.shpingcart.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public OrderBean getOrderByUserId(Long userId) {

        OrderEntity orderEntity = orderRepository.findByUserId(userId);
        OrderBean orderBean = modelMapper.map(orderEntity, OrderBean.class);
        return orderBean;
    }

    @Override
    public OrderBean postOrder(Long userId) {
        OrderEntity orderEntity = new OrderEntity();

        CartEntity cartEntity = cartRepository.findByUserId(userId);

        orderEntity.setCart(cartEntity);
        orderEntity.setUserId(userId);

        orderRepository.save(orderEntity);

        return modelMapper.map(orderEntity, OrderBean.class);
    }
}
