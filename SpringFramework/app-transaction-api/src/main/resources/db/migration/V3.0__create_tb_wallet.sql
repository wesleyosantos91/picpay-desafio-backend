CREATE TABLE tb_wallet
(
    id            BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    user_id       BINARY(16) NOT NULL,
    balance       DECIMAL(10, 2) NOT NULL DEFAULT 0,
    creation_date TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES tb_user (id)
);