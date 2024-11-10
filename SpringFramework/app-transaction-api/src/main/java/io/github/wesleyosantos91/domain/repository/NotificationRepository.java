package io.github.wesleyosantos91.domain.repository;

import static io.github.wesleyosantos91.domain.entity.enums.NotificationStatus.FAILED;

import io.github.wesleyosantos91.domain.entity.NotificationEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID> {

    default NotificationEntity saveNotification(NotificationEntity notification) {

        final var isValid = FAILED.equals(notification.getStatus());

        System.out.println("Notification is valid: " + isValid);
        return save(notification);
    }
}
