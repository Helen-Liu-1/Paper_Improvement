package org.example.automaticed.service.impl;

import org.example.automaticed.entity.OrderEntity;
import org.example.automaticed.repository.IOrderRepository;
import org.example.automaticed.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderDao;


    @Override
    public List<OrderEntity> save(int count) {
        List<OrderEntity> orderEntities = generateRandomOrders(count);
        List<OrderEntity> resultList = orderDao.saveAll(orderEntities);
        return resultList;
    }

    @Override
    public String get(Long orderId) {
        return orderDao.getReferenceById(orderId).getUserName();
    }

    @Override
    public OrderEntity getOrderEntity(Long orderId) {
        return orderDao.getReferenceById(orderId);
    }

    public Page<OrderEntity> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<OrderEntity> page = orderDao.findAll(pageable);
        return page;
    }

    @Override
    public List<OrderEntity> saveByOneself(List<OrderEntity> orderList) {
        List<OrderEntity> resultList = orderDao.saveAll(orderList);
        return resultList;
    }

    private static List<OrderEntity> generateRandomOrders(int count) {
        List<OrderEntity> orders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            OrderEntity order = new OrderEntity();
            order.setUserId(Integer.valueOf((int) (Math.random() * 10000))); 
            order.setUserName("User" + (i + 1)); 
            order.setOrderName("Order" + (i + 1)); 
            orders.add(order);
        }
        return orders;
    }
}
