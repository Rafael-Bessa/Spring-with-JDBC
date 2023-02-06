CREATE TABLE pessoa (
  pessoa_id INT AUTO_INCREMENT  PRIMARY KEY,
  cpf VARCHAR(11) NOT NULL,
  nome VARCHAR(150) NOT NULL
);

CREATE TABLE produto (
  registro INT AUTO_INCREMENT  PRIMARY KEY,
  descricao VARCHAR(250) NOT NULL,
  valor_unitario NUMERIC(10,2) NOT NULL
);

CREATE TABLE pedido (
  pedido_id INT AUTO_INCREMENT  PRIMARY KEY,
  pessoa_id INT NOT NULL,
  CONSTRAINT fk_pedido_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa (pessoa_id)
);

CREATE TABLE pedido_item (
  pedido_item_id INT AUTO_INCREMENT  PRIMARY KEY,
  produto_registro INT NOT NULL,
  quantidade INT NOT NULL,
  pedido_id INT NOT NULL,
  CONSTRAINT fk_pedido_item_produto FOREIGN KEY (produto_registro) REFERENCES produto (registro),
  CONSTRAINT fk_pedido_id FOREIGN KEY (pedido_id) REFERENCES pedido (pedido_id)
);

CREATE TABLE pedido_itens (
  itens_id INT NOT NULL,
  pedido_id INT NOT NULL,
  CONSTRAINT fk_pedido_item FOREIGN KEY (pedido_id) REFERENCES pedido (pedido_id),
  CONSTRAINT fk_item_id FOREIGN KEY (itens_id) REFERENCES pedido_item (pedido_item_id)
);

