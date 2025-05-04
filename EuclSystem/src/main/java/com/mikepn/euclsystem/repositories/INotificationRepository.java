package com.mikepn.euclsystem.repositories;

import com.mikepn.euclsystem.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface INotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByIssuedDateAfter(LocalDateTime time);

}
