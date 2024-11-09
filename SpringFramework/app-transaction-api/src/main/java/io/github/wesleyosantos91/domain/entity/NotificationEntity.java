package io.github.wesleyosantos91.domain.entity;

import io.github.wesleyosantos91.domain.entity.enums.DeliveryMethod;
import io.github.wesleyosantos91.domain.entity.enums.NotificationStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_notification")
public class NotificationEntity {

    @Id
    @ColumnDefault("(uuid_to_bin(uuid()))")
    @Column(name = "id", nullable = false, length = 16)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiver_user_id", nullable = false)
    private UserEntity receiverUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transaction_id", nullable = false)
    private TransactionEntity transaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_method", nullable = false)
    private DeliveryMethod deliveryMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private NotificationStatus status;

    @Column(name = "sent_date")
    private Instant sentDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserEntity getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(UserEntity receiverUser) {
        this.receiverUser = receiverUser;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public Instant getSentDate() {
        return sentDate;
    }

    public void setSentDate(Instant sentDate) {
        this.sentDate = sentDate;
    }

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "id=" + id +
                ", receiverUser=" + receiverUser +
                ", transaction=" + transaction +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", status='" + status + '\'' +
                ", sentDate=" + sentDate +
                '}';
    }
}