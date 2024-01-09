INSERT INTO PRODUTO (ID, NOME, PRECO, DESCRICAO) VALUES (1, 'Kindle', 499.0, 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
INSERT INTO PRODUTO (ID, NOME, PRECO, DESCRICAO) VALUES (3, 'Câmera GoPro Hero 7', 1400.0, 'Desempenho 2x melhor.');

INSERT INTO CLIENTE (ID, NOME) VALUES (1, 'Fernando Medeiros');
INSERT INTO CLIENTE (ID, NOME) VALUES (2, 'Marcos Mariano');

INSERT INTO PEDIDO (ID, CLIENTE_ID, DATA_PEDIDO, TOTAL, STATUS) VALUES (1, 1, SYSDATE(), 100.0, 'AGUARDANDO');

INSERT INTO ITEM_PEDIDO (ID, PEDIDO_ID, PRODUTO_ID, PRECO_PRODUTO, QUANTIDADE) VALUES (1, 1, 1, 5.0, 2);

INSERT INTO CATEGORIA (ID, NOME) VALUES (1, 'Eletrônicos');
