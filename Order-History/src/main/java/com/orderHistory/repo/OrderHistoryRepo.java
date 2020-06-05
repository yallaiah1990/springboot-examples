package com.orderHistory.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderHistory.entity.OrderHistoryEntity;
import com.orderHistory.entity.OrderId;

public interface OrderHistoryRepo extends JpaRepository<OrderHistoryEntity, OrderId>{
	List<OrderHistoryEntity> findByIdOrderId(Long orderId);
}
