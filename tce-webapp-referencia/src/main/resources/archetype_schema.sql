DELETE FROM archetype.produto;
DROP TABLE archetype.produto;

DELETE FROM archetype.tipo_produto;
DROP TABLE archetype.tipo_produto;


-- tabela tipo_produto --
CREATE TABLE archetype.tipo_produto (
	id NUMBER(2) PRIMARY KEY,
	descricao	 VARCHAR2(20) NOT NULL
);
COMMENT ON TABLE tipo_produto IS 'Tabela com os tipos de produtos existentes.';
COMMENT ON COLUMN tipo_produto.id IS 'Identificador do tipo do produto.';
COMMENT ON COLUMN tipo_produto.descricao IS 'Descrição do tipo do produto.';

INSERT INTO archetype.tipo_produto VALUES (1, 'Roupa');
INSERT INTO archetype.tipo_produto VALUES (2, 'Alimento');
INSERT INTO archetype.tipo_produto VALUES (3, 'Acessório'); 
INSERT INTO archetype.tipo_produto VALUES (99, 'Não identificado');

-- tabela produto --
CREATE TABLE archetype.produto (

     id      			NUMBER(5) PRIMARY KEY,
     descricao  		VARCHAR2(15),
     preco      		NUMBER(5,2),
     id_tipo_produto    NUMBER(2) 
     					CONSTRAINT tipo_produto_fkey 
     					REFERENCES archetype.tipo_produto(id)
               
);
COMMENT ON TABLE  produto IS 'Tabela com os produtos a venda.';
COMMENT ON COLUMN produto.id IS 'Identificador do produto.';
COMMENT ON COLUMN produto.descricao IS 'Descrição do produto.';
COMMENT ON COLUMN produto.preco IS 'Preço de venda do produto.';
COMMENT ON COLUMN produto.id_tipo_produto IS 'Identificador do tipo do produto na tabela tipo_produto.';

INSERT INTO archetype.produto VALUES (1, 'Camisa X', 22.49, 1);
INSERT INTO archetype.produto VALUES (2, 'Calça Y', 55.49, 1);
INSERT INTO archetype.produto VALUES (3, 'Arroz', 2.49, 2);
INSERT INTO archetype.produto VALUES (4, 'Feijão', 3.49, 2);
INSERT INTO archetype.produto VALUES (5, 'Relógio', 99.49, 3);
INSERT INTO archetype.produto VALUES (6, 'Pulseira', 5.49, 3);

DROP SEQUENCE SEQ_PRODUTO;
CREATE SEQUENCE SEQ_PRODUTO START WITH 7 INCREMENT BY 1;


commit;