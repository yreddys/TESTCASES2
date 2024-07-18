package com.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orders.dto.Orders;
import com.orders.service.OrderService;

@RestController
@RequestMapping("api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/placed")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> placeOrder(@RequestBody Orders orderRequest) {
		return orderService.placeOrder(orderRequest);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<List<Orders>> getOrder(@PathVariable Long orderId) {
		List<Orders> order = orderService.getOrderById(orderId);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	@DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
