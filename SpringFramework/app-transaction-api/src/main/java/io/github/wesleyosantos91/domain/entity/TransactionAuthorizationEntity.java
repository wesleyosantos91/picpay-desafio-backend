package io.github.wesleyosantos91.domain.entity;

import io.github.wesleyosantos91.domain.entity.enums.TransactionAuthorizationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_transaction_authorization")
public class TransactionAuthorizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 16)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transaction_id", nullable = false)
    private TransactionEntity transaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionAuthorizationStatus status;

    @Column(name = "authorization_date")
    private Instant authorizationDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public TransactionAuthorizationStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionAuthorizationStatus status) {
        this.status = status;
    }

    public Instant getAuthorizationDate() {
        return authorizationDate;
    }

    public void setAuthorizationDate(Instant authorizationDate) {
        this.authorizationDate = authorizationDate;
    }

    @Override
    public String toString() {
        return "TransactionAuthorizationEntity{"
                + "id=" + id
                + ", transaction=" + transaction
                + ", status='" + status + '\''
                + ", authorizationDate=" + authorizationDate
                + '}';
    }
}
