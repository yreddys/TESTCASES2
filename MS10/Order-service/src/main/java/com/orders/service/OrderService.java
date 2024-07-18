package com.orders.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orders.dto.Orders;
import com.orders.exception.ProductNotAvailableException;
import com.orders.model.NotificationRequest;
import com.orders.model.Order;
import com.orders.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTemplate;

	private static final String INVENTORY_SERVICE_URL = "http://inventory-env-1.eba-maafrfpr.ap-south-1.elasticbeanstalk.com/api/inventory";
	private static final String NOTIFICATION_SERVICE_URL = "http://notification-env-1.eba-7ce2qaxq.ap-south-1.elasticbeanstalk.com/notification";

	public ResponseEntity<String> placeOrder(Orders orderRequest) {
		String skuCode = orderRequest.getSkuCode();
		Integer quantity = orderRequest.getQuantity();
		String email = orderRequest.getCustomerEmail();

		// Construct inventory service URL
		String fullInventoryServiceUrl = INVENTORY_SERVICE_URL + "/update-quantity?skuCode=" + skuCode + "&quantity="
				+ quantity;

		// Call Inventory service to update the quantity
		ResponseEntity<String> inventoryResponse = restTemplate.postForEntity(fullInventoryServiceUrl, null,
				String.class);

		if (inventoryResponse.getStatusCode() == HttpStatus.OK) {
			orderRepository.save(orderRequest);

			// Prepare notification request
			NotificationRequest notificationRequest = new NotificationRequest(email,
					"Your order has been placed successfully.");

			// Send notification to notification microservice
			String fullNotificationServiceUrl = NOTIFICATION_SERVICE_URL + "/sendNotification";
			ResponseEntity<String> notificationResponse = restTemplate.postForEntity(fullNotificationServiceUrl,
					notificationRequest, String.class);

			if (notificationResponse.getStatusCode() == HttpStatus.CREATED) {
				return ResponseEntity.ok("Order placed successfully and notification sent");
			} else {
				// Handle notification failure gracefully
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send notification");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is not available");
		}
	}

	public List<Orders> getOrderById(Long orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		if (order.isPresent()) {
			return (List<Orders>) order.get();
		} else {
			throw new ProductNotAvailableException("Order with ID " + orderId + " not found");
		}
	}

	public void deleteOrderById(Long orderId) {
		if (orderRepository.existsById(orderId)) {
			orderRepository.deleteById(orderId);
		} else {
			throw new ProductNotAvailableException("Order with ID " + orderId + " not found");
		}
	}
}
