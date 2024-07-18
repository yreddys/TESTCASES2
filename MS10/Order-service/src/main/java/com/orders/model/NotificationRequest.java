package com.orders.model;

public class NotificationRequest {
	private String email;
	private String message;

	public NotificationRequest() {
	}

	public NotificationRequest(String email, String message) {
		this.email = email;
		this.message = message;
	}

	// Getters and setters

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

	@Override
	public String toString() {
		return "NotificationRequest{" + "email='" + email + '\'' + ", message='" + message + '\'' + '}';
	}
}
