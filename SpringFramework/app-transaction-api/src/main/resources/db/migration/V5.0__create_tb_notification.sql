CREATE TABLE tb_notification
(
    id               BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    receiver_user_id BINARY(16) NOT NULL,
    transaction_id   BINARY(16) NOT NULL,
    delivery_method  ENUM('EMAIL', 'SMS') NOT NULL,
    status           ENUM('PENDING', 'SENT', 'FAILED') NOT NULL DEFAULT 'PENDING',
    sent_date        TIMESTAMP,
    FOREIGN KEY (receiver_user_id) REFERENCES tb_user (id),
    FOREIGN KEY (transaction_id) REFERENCES tb_transaction (id)
);