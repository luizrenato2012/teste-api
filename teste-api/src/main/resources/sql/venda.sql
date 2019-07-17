-- 2019-07-17

CREATE TABLE venda.cliente
(
    id integer NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(50) COLLATE pg_catalog."default" NOT NULL,
    telefone character varying(15) COLLATE pg_catalog."default",
    CONSTRAINT pk_cliente PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE venda.cliente
    OWNER to teste;

-- Table: venda.endereco

-- DROP TABLE venda.endereco;

CREATE TABLE venda.endereco
(
    id integer NOT NULL,
    logradouro character varying(50) COLLATE pg_catalog."default" NOT NULL,
    bairro character varying(30) COLLATE pg_catalog."default" NOT NULL,
    cidade character varying(30) COLLATE pg_catalog."default",
    uf character varying(2) COLLATE pg_catalog."default",
    id_cliente integer,
    CONSTRAINT endereco_pkey PRIMARY KEY (id),
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)
        REFERENCES venda.cliente (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE venda.endereco
    OWNER to teste;


-- Table: venda.item_venda

-- DROP TABLE venda.item_venda;

CREATE TABLE venda.item_venda
(
    id integer NOT NULL,
    sequencial integer,
    id_produto integer NOT NULL,
    id_compra integer NOT NULL,
    valor_unitario numeric(8,2),
    quantidade numeric(8,2),
    valor_total numeric(8,2)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE venda.item_venda
    OWNER to teste;

-- Table: venda.permissao

-- DROP TABLE venda.permissao;

CREATE TABLE venda.permissao
(
    codigo integer NOT NULL,
    descricao character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT permissao_pkey PRIMARY KEY (codigo)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE venda.permissao
    OWNER to teste;

-- Table: venda.produto

-- DROP TABLE venda.produto;

CREATE TABLE venda.produto
(
    id integer NOT NULL,
    descricao character varying(50) COLLATE pg_catalog."default" NOT NULL,
    preco numeric(8,2) NOT NULL,
    CONSTRAINT produto_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE venda.produto
    OWNER to teste;

-- Table: venda.usuario

-- DROP TABLE venda.usuario;

CREATE TABLE venda.usuario
(
    codigo integer NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(150) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT usuario_pkey PRIMARY KEY (codigo)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE venda.usuario
    OWNER to teste;

-- Table: venda.usuario_permissao

-- DROP TABLE venda.usuario_permissao;

CREATE TABLE venda.usuario_permissao
(
    codigo_usuario integer NOT NULL,
    codigo_permissao integer NOT NULL,
    CONSTRAINT pk_usuario_permissao PRIMARY KEY (codigo_usuario, codigo_permissao),
    CONSTRAINT fk_usuario_permissao_permissao FOREIGN KEY (codigo_permissao)
        REFERENCES venda.permissao (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_usuario_permissao_usuario FOREIGN KEY (codigo_usuario)
        REFERENCES venda.usuario (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE venda.usuario_permissao
    OWNER to teste;

-- Table: venda.venda

-- DROP TABLE venda.venda;

CREATE TABLE venda.venda
(
    id integer NOT NULL,
    data_hora timestamp without time zone,
    id_cliente integer,
    valor_total numeric(8,0)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE venda.venda
    OWNER to teste;

-- SEQUENCES

CREATE SEQUENCE venda.seq_id_cliente;

ALTER SEQUENCE venda.seq_id_cliente
    OWNER TO teste;

-- SEQUENCE: venda.seq_id_endereco

-- DROP SEQUENCE venda.seq_id_endereco;

CREATE SEQUENCE venda.seq_id_endereco;

ALTER SEQUENCE venda.seq_id_endereco
    OWNER TO teste;

-- SEQUENCE: venda.seq_id_item_venda

-- DROP SEQUENCE venda.seq_id_item_venda;

CREATE SEQUENCE venda.seq_id_item_venda;

ALTER SEQUENCE venda.seq_id_item_venda
    OWNER TO teste;

-- SEQUENCE: venda.seq_id_permissao

-- DROP SEQUENCE venda.seq_id_permissao;

CREATE SEQUENCE venda.seq_id_permissao;

ALTER SEQUENCE venda.seq_id_permissao
    OWNER TO teste;

-- SEQUENCE: venda.seq_id_produto

-- DROP SEQUENCE venda.seq_id_produto;

CREATE SEQUENCE venda.seq_id_produto;

ALTER SEQUENCE venda.seq_id_produto
    OWNER TO teste;

-- SEQUENCE: venda.seq_id_usuario

-- DROP SEQUENCE venda.seq_id_usuario;

CREATE SEQUENCE venda.seq_id_usuario;

ALTER SEQUENCE venda.seq_id_usuario
    OWNER TO teste;

-- SEQUENCE: venda.seq_id_venda

-- DROP SEQUENCE venda.seq_id_venda;

CREATE SEQUENCE venda.seq_id_venda;

ALTER SEQUENCE venda.seq_id_venda
    OWNER TO teste;
