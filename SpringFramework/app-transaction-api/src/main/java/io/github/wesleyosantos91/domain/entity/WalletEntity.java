package io.github.wesleyosantos91.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_wallet")
public class WalletEntity {

    @Id
    @ColumnDefault("(uuid_to_bin(uuid()))")
    @Column(name = "id", nullable = false, length = 16)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ColumnDefault("0.00")
    @Column(name = "balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "creation_date")
    private Instant creationDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "WalletEntity{" +
                "id=" + id +
                ", user=" + user +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                '}';
    }
}