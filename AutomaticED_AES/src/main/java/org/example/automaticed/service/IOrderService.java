package org.example.automaticed.service;

import org.example.automaticed.entity.OrderEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderService {
    List<OrderEntity> save(int count);

    String get(Long orderId);

    OrderEntity getOrderEntity(Long orderId);

    Page<OrderEntity> findPaginated(int pageNo, int pageSize);

    List<OrderEntity> saveByOneself(List<OrderEntity> orderList);
}
