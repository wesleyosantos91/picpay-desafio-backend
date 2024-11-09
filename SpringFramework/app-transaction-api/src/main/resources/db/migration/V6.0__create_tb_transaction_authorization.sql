CREATE TABLE tb_transaction_authorization
(
    id                 BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    transaction_id     BINARY(16) NOT NULL UNIQUE,
    status             ENUM('AUTHORIZED', 'DENIED') NOT NULL,
    authorization_date TIMESTAMP,
    FOREIGN KEY (transaction_id) REFERENCES tb_transaction (id)
);