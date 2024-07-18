package com.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orders.dto.Orders;
import com.orders.model.Order;

public interface OrderRepository extends JpaRepository<Order , Long>{

	void save(Orders orderRequest);

}
