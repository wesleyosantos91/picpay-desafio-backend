package io.github.wesleyosantos91.domain.repository;

import io.github.wesleyosantos91.domain.entity.WalletEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, UUID> {
}
