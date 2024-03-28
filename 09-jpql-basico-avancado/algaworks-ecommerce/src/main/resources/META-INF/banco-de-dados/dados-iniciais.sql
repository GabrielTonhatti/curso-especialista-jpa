INSERT INTO PRODUTO (ID, NOME, PRECO, DATA_CRIACAO, DESCRICAO) VALUES (1, 'Kindle', 499.0, DATE_SUB(SYSDATE(), INTERVAL 1 DAY), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
INSERT INTO PRODUTO (ID, NOME, PRECO, DATA_CRIACAO, DESCRICAO) VALUES (3, 'Câmera GoPro Hero 7', 1400.0, DATE_SUB(SYSDATE(), INTERVAL 1 DAY), 'Desempenho 2x melhor.');
INSERT INTO PRODUTO (ID, NOME, PRECO, DATA_CRIACAO, DESCRICAO) VALUES (4, 'Câmera Canon 80D', 3500.0, SYSDATE(), 'O melhor ajuste de foco.');

insert into CLIENTE (ID, NOME, CPF) VALUES (1, 'Fernando Medeiros', '000');
insert into CLIENTE (ID, NOME, CPF) VALUES (2, 'Marcos Mariano', '111');

insert into CLIENTE_DETALHE (CLIENTE_ID, SEXO, DATA_NASCIMENTO) VALUES (1, 'MASCULINO', DATE_SUB(SYSDATE(), INTERVAL 27 YEAR));
insert into CLIENTE_DETALHE (CLIENTE_ID, SEXO, DATA_NASCIMENTO) VALUES (2, 'MASCULINO', DATE_SUB(SYSDATE(), INTERVAL 30 YEAR));

INSERT INTO PEDIDO (ID, CLIENTE_ID, DATA_CRIACAO, TOTAL, STATUS) VALUES (1, 1, DATE_SUB(SYSDATE(), INTERVAL  30 DAY), 2398.0, 'AGUARDANDO');
INSERT INTO PEDIDO (ID, CLIENTE_ID, DATA_CRIACAO, TOTAL, STATUS) VALUES (2, 1, SYSDATE(), 499.0, 'AGUARDANDO');
INSERT INTO PEDIDO (ID, CLIENTE_ID, DATA_CRIACAO, TOTAL, STATUS) VALUES (3, 1, DATE_SUB(SYSDATE(), INTERVAL 29 DAY), 3500.0, 'PAGO');
INSERT INTO PEDIDO (ID, CLIENTE_ID, DATA_CRIACAO, TOTAL, STATUS) VALUES (4, 2, DATE_SUB(SYSDATE(), INTERVAL 2 DAY), 499.0, 'PAGO');

INSERT INTO ITEM_PEDIDO (PEDIDO_ID, PRODUTO_ID, PRECO_PRODUTO, QUANTIDADE) VALUES (1, 1, 499, 2);
INSERT INTO ITEM_PEDIDO (PEDIDO_ID, PRODUTO_ID, PRECO_PRODUTO, QUANTIDADE) VALUES (1, 3, 1400, 1);
INSERT INTO ITEM_PEDIDO (PEDIDO_ID, PRODUTO_ID, PRECO_PRODUTO, QUANTIDADE) VALUES (2, 1, 499, 1);
INSERT INTO ITEM_PEDIDO (PEDIDO_ID, PRODUTO_ID, PRECO_PRODUTO, QUANTIDADE) VALUES (3, 4, 3500, 1);
INSERT INTO ITEM_PEDIDO (PEDIDO_ID, PRODUTO_ID, PRECO_PRODUTO, QUANTIDADE) VALUES (4, 1, 499, 1);

-- INSERT INTO PAGAMENTO (PEDIDO_ID, TIPO_PAGAMENTO, STATUS) VALUES (2, 'CARTAO', 'PROCESSANDO');

-- INSERT INTO PAGAMENTO_CARTAO (PEDIDO_ID, NUMERO_CARTAO) VALUES (2, '123');

INSERT INTO PAGAMENTO (PEDIDO_ID, TIPO_PAGAMENTO, STATUS, NUMERO_CARTAO) VALUES (2, 'CARTAO', 'PROCESSANDO', '123');

INSERT INTO ITEM_PEDIDO (PEDIDO_ID, PRODUTO_ID, PRECO_PRODUTO, QUANTIDADE) VALUES (1, 1, 5.0, 2);

INSERT INTO NOTA_FISCAL (PEDIDO_ID, DATA_EMISSAO, XML) VALUES (2, SYSDATE(), '<xml />');

INSERT INTO CATEGORIA (NOME) VALUES ('Eletrodomésticos');
INSERT INTO CATEGORIA (NOME) VALUES ('Livros');
INSERT INTO CATEGORIA (NOME) VALUES ('Esportes');
INSERT INTO CATEGORIA (NOME) VALUES ('Futebol');
INSERT INTO CATEGORIA (NOME) VALUES ('Natação');
INSERT INTO CATEGORIA (NOME) VALUES ('Notebooks');
INSERT INTO CATEGORIA (NOME) VALUES ('Smartphones');
INSERT INTO CATEGORIA (ID, NOME) VALUES (8, 'Câmeras');

INSERT INTO PRODUTO_CATEGORIA (PRODUTO_ID, CATEGORIA_ID) VALUES (1, 2);
INSERT INTO PRODUTO_CATEGORIA (PRODUTO_ID, CATEGORIA_ID) VALUES (3, 8);
INSERT INTO PRODUTO_CATEGORIA (PRODUTO_ID, CATEGORIA_ID) VALUES (4, 8);
