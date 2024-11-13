package org.example.automaticed.service;

import org.example.automaticed.entity.OrderEntity;
import org.example.automaticed.repository.IOrderRepository;
import org.example.automaticed.service.impl.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private IOrderRepository orderDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        int count = 5;
        List<OrderEntity> generatedOrders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            OrderEntity order = new OrderEntity();
            order.setUserId(i);
            order.setUserName("User" + i);
            order.setOrderName("Order" + i);
            generatedOrders.add(order);
        }

        when(orderDao.saveAll(any(List.class))).thenReturn(generatedOrders);

        List<OrderEntity> result = orderService.save(count);

        assertNotNull(result);
        assertEquals(count, result.size());
        assertEquals("User0", result.get(0).getUserName());
    }

    @Test
    void testGet() {
        Long orderId = 1L;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderId);
        orderEntity.setUserName("Test User");

        when(orderDao.getReferenceById(orderId)).thenReturn(orderEntity);

        String result = orderService.get(orderId);

        assertEquals("Test User", result);
    }

    @Test
    void testGetOrderEntity() {
        Long orderId = 1L;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderId);
        orderEntity.setUserName("Test User");

        when(orderDao.getReferenceById(orderId)).thenReturn(orderEntity);

        OrderEntity result = orderService.getOrderEntity(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.getOrderId());
    }

    @Test
    void testFindPaginated() {
        int pageNo = 0;
        int pageSize = 1;
        OrderEntity orderEntity = new OrderEntity();
        Page<OrderEntity> page = new PageImpl<>(Collections.singletonList(orderEntity), PageRequest.of(pageNo, pageSize), 1);

        when(orderDao.findAll(any(Pageable.class))).thenReturn(page);

        Page<OrderEntity> result = orderService.findPaginated(pageNo, pageSize);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testSaveByOneself() {
        List<OrderEntity> orderList = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserName("Test User");
        orderList.add(orderEntity);

        when(orderDao.saveAll(orderList)).thenReturn(orderList);

        List<OrderEntity> result = orderService.saveByOneself(orderList);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test User", result.get(0).getUserName());
    }
}
