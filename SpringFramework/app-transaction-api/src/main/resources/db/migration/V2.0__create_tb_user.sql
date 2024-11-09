CREATE TABLE tb_user
(
    id        BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    full_name VARCHAR(100)        NOT NULL,
    cpf_cnpj  VARCHAR(18) UNIQUE  NOT NULL,
    email     VARCHAR(100) UNIQUE NOT NULL,
    password  VARCHAR(255)        NOT NULL,
    user_type ENUM('CUSTOMER', 'MERCHANT') NOT NULL
);