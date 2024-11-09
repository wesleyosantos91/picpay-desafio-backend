INSERT INTO tb_wallet (user_id, balance)
VALUES ((SELECT id FROM tb_user WHERE cpf_cnpj = '12345678901'), 1000.00),
       ((SELECT id FROM tb_user WHERE cpf_cnpj = '98765432109'), 500.00),
       ((SELECT id FROM tb_user WHERE cpf_cnpj = '11122233344'), 750.00),
       ((SELECT id FROM tb_user WHERE cpf_cnpj = '55566677788'), 0.00),
       ((SELECT id FROM tb_user WHERE cpf_cnpj = '99900011122'), 250.00),
       ((SELECT id FROM tb_user WHERE cpf_cnpj = '12345678901234'), 10000.00),
       ((SELECT id FROM tb_user WHERE cpf_cnpj = '98765432109876'), 8000.00),
       ((SELECT id FROM tb_user WHERE cpf_cnpj = '11122233344555'), 12000.00),
       ((SELECT id FROM tb_user WHERE cpf_cnpj = '55566677788999'), 3000.00),
       ((SELECT id FROM tb_user WHERE cpf_cnpj = '99900011122333'), 9500.00);
