CREATE TABLE tb_transaction
(
    id                 BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    sender_wallet_id   BINARY(16) NOT NULL,
    receiver_wallet_id BINARY(16) NOT NULL,
    amount             DECIMAL(10, 2) NOT NULL,
    status             ENUM('PENDING', 'COMPLETED', 'CANCELED') NOT NULL DEFAULT 'PENDING',
    creation_date      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    completion_date    TIMESTAMP,
    FOREIGN KEY (sender_wallet_id) REFERENCES tb_wallet (id),
    FOREIGN KEY (receiver_wallet_id) REFERENCES tb_wallet (id)
);