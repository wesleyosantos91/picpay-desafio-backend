package io.github.wesleyosantos91.domain.repository;

import io.github.wesleyosantos91.domain.entity.TransactionEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
}
