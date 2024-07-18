package com.notification.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notifications {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notificationId;
	private String email;
	private String message;
	public Long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Notifications(Long notificationId, String email, String message) {
		
		this.notificationId = notificationId;
		this.email = email;
		this.message = message;
	}
	public Notifications() {}
	@Override
	public String toString() {
		return "Notifications [notificationId=" + notificationId + ", email=" + email + ", message=" + message + "]";
	}
	
}
