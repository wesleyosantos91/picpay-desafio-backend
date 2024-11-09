INSERT INTO tb_notification (receiver_user_id, transaction_id, delivery_method, status)
VALUES
    ((SELECT id FROM tb_user WHERE cpf_cnpj = '98765432109'),
     (SELECT id FROM tb_transaction WHERE sender_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '12345678901')) AND receiver_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '98765432109'))),
     'EMAIL', 'PENDING'),

    ((SELECT id FROM tb_user WHERE cpf_cnpj = '55566677788'),
     (SELECT id FROM tb_transaction WHERE sender_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '11122233344')) AND receiver_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '55566677788'))),
     'SMS', 'SENT'),

    ((SELECT id FROM tb_user WHERE cpf_cnpj = '99900011122'),
     (SELECT id FROM tb_transaction WHERE sender_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '99900011122')) AND receiver_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '12345678901234'))),
     'EMAIL', 'FAILED'),

    ((SELECT id FROM tb_user WHERE cpf_cnpj = '55566677788'),
     (SELECT id FROM tb_transaction WHERE sender_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '55566677788')) AND receiver_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '99900011122'))),
     'SMS', 'SENT'),

    ((SELECT id FROM tb_user WHERE cpf_cnpj = '98765432109876'),
     (SELECT id FROM tb_transaction WHERE sender_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '98765432109876')) AND receiver_wallet_id = (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '11122233344555'))),
     'EMAIL', 'PENDING');
