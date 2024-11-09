INSERT INTO tb_transaction_authorization (transaction_id, status, authorization_date)
VALUES
    ((SELECT id FROM tb_transaction WHERE sender_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '12345678901')) AND receiver_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '98765432109'))), 'AUTHORIZED', CURRENT_TIMESTAMP),

    ((SELECT id FROM tb_transaction WHERE sender_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '11122233344')) AND receiver_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '55566677788'))), 'DENIED', CURRENT_TIMESTAMP),

    ((SELECT id FROM tb_transaction WHERE sender_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '99900011122')) AND receiver_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '12345678901234'))), 'AUTHORIZED', CURRENT_TIMESTAMP),

    ((SELECT id FROM tb_transaction WHERE sender_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '55566677788')) AND receiver_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '99900011122'))), 'AUTHORIZED', CURRENT_TIMESTAMP),

    ((SELECT id FROM tb_transaction WHERE sender_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '98765432109876')) AND receiver_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '11122233344555'))), 'DENIED', CURRENT_TIMESTAMP);
