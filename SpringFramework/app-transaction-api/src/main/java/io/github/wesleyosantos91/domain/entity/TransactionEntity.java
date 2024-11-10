package io.github.wesleyosantos91.domain.entity;

import io.github.wesleyosantos91.domain.entity.enums.TransactionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tb_transaction")
public class TransactionEntity {

    @Id
    @ColumnDefault("(uuid_to_bin(uuid()))")
    @Column(name = "id", nullable = false, length = 16)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_wallet_id", nullable = false)
    private WalletEntity senderWallet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiver_wallet_id", nullable = false)
    private WalletEntity receiverWallet;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "completion_date")
    private Instant completionDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public WalletEntity getSenderWallet() {
        return senderWallet;
    }

    public void setSenderWallet(WalletEntity senderWallet) {
        this.senderWallet = senderWallet;
    }

    public WalletEntity getReceiverWallet() {
        return receiverWallet;
    }

    public void setReceiverWallet(WalletEntity receiverWallet) {
        this.receiverWallet = receiverWallet;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Instant completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public String toString() {
        return "TransactionEntity{"
                + "id=" + id
                + ", senderWallet=" + senderWallet
                + ", receiverWallet=" + receiverWallet
                + ", amount=" + amount
                + ", status='" + status + '\''
                + ", creationDate=" + creationDate
                + ", completionDate=" + completionDate
                + '}';
    }
}
