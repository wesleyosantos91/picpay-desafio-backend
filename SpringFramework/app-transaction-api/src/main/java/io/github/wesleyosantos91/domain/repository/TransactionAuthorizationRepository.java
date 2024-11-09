package io.github.wesleyosantos91.domain.repository;

import io.github.wesleyosantos91.domain.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionAuthorizationRepository extends JpaRepository<TransactionEntity, UUID> {
}
