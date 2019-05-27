CREATE TABLE venda.usuario
(
    codigo integer NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(150) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT usuario_pkey PRIMARY KEY (codigo)
);


CREATE TABLE venda.permissao
(
    codigo integer NOT NULL,
    descricao character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT permissao_pkey PRIMARY KEY (codigo)
);



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
);

begin; --commit	rollback
INSERT INTO venda.usuario (codigo, nome, email, senha) values (1, 'Administrador', 'admin@email.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO venda.usuario (codigo, nome, email, senha) values (2, 'Maria Silva', 'maria@email.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

begin; --commit	rollback
INSERT INTO venda.permissao (codigo, descricao) values (1, 'ROLE_CADASTRAR_PRODUTO');
INSERT INTO venda.permissao (codigo, descricao) values (2, 'ROLE_PESQUISAR_PRODUTO');

INSERT INTO venda.permissao (codigo, descricao) values (3, 'ROLE_CADASTRAR_CLIENTE');
INSERT INTO venda.permissao (codigo, descricao) values (4, 'ROLE_REMOVER_CLIENTE');
INSERT INTO venda.permissao (codigo, descricao) values (5, 'ROLE_PESQUISAR_CLIENTE');

INSERT INTO venda.permissao (codigo, descricao) values (6, 'ROLE_CADASTRAR_VENDA');
INSERT INTO venda.permissao (codigo, descricao) values (7, 'ROLE_REMOVER_VENDA');
INSERT INTO venda.permissao (codigo, descricao) values (8, 'ROLE_PESQUISAR_VENDA');

-- admin
begin; --commit	rollback
INSERT INTO venda.usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);
INSERT INTO venda.usuario_permissao (codigo_usuario, codigo_permissao) values (1, 2);
INSERT INTO venda.usuario_permissao (codigo_usuario, codigo_permissao) values (1, 3);
INSERT INTO venda.usuario_permissao (codigo_usuario, codigo_permissao) values (1, 4);
INSERT INTO venda.usuario_permissao (codigo_usuario, codigo_permissao) values (1, 5);
INSERT INTO venda.usuario_permissao (codigo_usuario, codigo_permissao) values (1, 6);
INSERT INTO venda.usuario_permissao (codigo_usuario, codigo_permissao) values (1, 7);
INSERT INTO venda.usuario_permissao (codigo_usuario, codigo_permissao) values (1, 8);

-- maria
INSERT INTO venda.usuario_permissao (codigo_usuario, codigo_permissao) values (2, 2);
INSERT INTO venda.usuario_permissao (codigo_usuario, codigo_permissao) values (2, 5);
INSERT INTO venda.usuario_permissao (codigo_usuario, codigo_permissao) values (2, 8);
