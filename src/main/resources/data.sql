INSERT INTO pessoa (cpf, nome)
VALUES
  	('56637927082', 'Ana'),
  	('56637927082', 'Rafael'),
  	('56637927099', 'Carlos')
    ;

INSERT INTO produto (descricao, valor_unitario)
VALUES
  	('Arroz Integral', 15.0),
  	('Salmão 500g', 63.0),
  	('Coca-Cola 2L', 6.5),
  	('Leite Longa Vida Desnatado', 8.0)
  	;

INSERT INTO pedido (pessoa_id)
VALUES
  	(2),
  	(1),
  	(3)
  	;

INSERT INTO pedido_item (produto_registro, quantidade, pedido_id)
VALUES

    (2, 1, 3),
  	(3, 5, 1),
  	(4, 6, 2),
  	(1, 1, 1)
  	;

INSERT INTO pedido_itens (itens_id, pedido_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (4, 3)
    ;