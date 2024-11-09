INSERT INTO tb_transaction (sender_wallet_id, receiver_wallet_id, amount, status)
VALUES
    ((SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '12345678901')),
     (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '98765432109')), 200.00, 'COMPLETED'),

    ((SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '11122233344')),
     (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '55566677788')), 300.00, 'PENDING'),

    ((SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '99900011122')),
     (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '12345678901234')), 1500.00, 'CANCELED'),

    ((SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '55566677788')),
     (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '99900011122')), 100.00, 'COMPLETED'),

    ((SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '98765432109876')),
     (SELECT id FROM tb_wallet WHERE user_id = (SELECT id FROM tb_user WHERE cpf_cnpj = '11122233344555')), 500.00, 'PENDING');
