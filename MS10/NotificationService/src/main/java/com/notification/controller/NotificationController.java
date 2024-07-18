package com.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.entity.Notifications;
import com.notification.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/sendNotification")
	public ResponseEntity<String> sendNotification(@RequestBody Notifications request) {
		notificationService.sendNotification(request.getEmail(), request.getMessage());
		return ResponseEntity.status(HttpStatus.CREATED).body("Notification sent successfully");
	}
}
