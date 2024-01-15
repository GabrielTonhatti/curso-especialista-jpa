insert into PRODUTO (ID, NOME, PRECO, DATA_CRIACAO, DESCRICAO) values (1, 'Kindle', 499.0, DATE_SUB(SYSDATE(), INTERVAL 1 DAY), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into PRODUTO (ID, NOME, PRECO, DATA_CRIACAO, DESCRICAO) values (3, 'Câmera GoPro Hero 7', 1400.0, DATE_SUB(SYSDATE(), INTERVAL 1 DAY), 'Desempenho 2x melhor.');

INSERT INTO CLIENTE (ID, NOME)
VALUES (1, 'Fernando Medeiros');
INSERT INTO CLIENTE (ID, NOME)
VALUES (2, 'Marcos Mariano');

INSERT INTO PEDIDO (ID, CLIENTE_ID, DATA_CRIACAO, TOTAL, STATUS)
VALUES (1, 1, SYSDATE(), 100.0, 'AGUARDANDO');

INSERT INTO ITEM_PEDIDO (ID, PEDIDO_ID, PRODUTO_ID, PRECO_PRODUTO, QUANTIDADE)
VALUES (1, 1, 1, 5.0, 2);

INSERT INTO CATEGORIA (ID, NOME)
VALUES (1, 'Eletrônicos');
