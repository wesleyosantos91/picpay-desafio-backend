package io.github.wesleyosantos91.domain.repository;

import io.github.wesleyosantos91.domain.entity.NotificationEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID> {
}
