package com.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notification.entity.Notifications;
@Repository
public interface NotificationsRepository extends JpaRepository<Notifications,Integer> {

}
