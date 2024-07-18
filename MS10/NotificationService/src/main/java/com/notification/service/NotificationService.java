package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.entity.Notifications;
import com.notification.repository.NotificationsRepository;

import jakarta.transaction.Transactional;

@Service
public class NotificationService {

    @Autowired
    private NotificationsRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void sendNotification(String email, String message) {
        Notifications notification = new Notifications();
        notification.setEmail(email);
        notification.setMessage(message);
        notificationRepository.save(notification);

        // Send email notification
        emailService.sendEmail(email, "Notification from MyCompany", message);
    }
}
