CREATE TABLE dias_da_semana (
                                id SERIAL PRIMARY KEY ,
                                descricao VARCHAR(14)
);

CREATE TABLE horarios (
                            id SERIAL PRIMARY KEY,
                            inicio time,
                            fim time,
                            dia_da_semana_id integer,
                            FOREIGN KEY (dia_da_semana_id) REFERENCES dias_da_semana(id),
                            deleted_at timestamp,
                            updated_at timestamp,
                            created_at timestamp
);
CREATE TABLE imagens (
                            id SERIAL PRIMARY KEY ,
                            caminho_link varchar(225)
);

CREATE TABLE barbeiros (
                             id SERIAL PRIMARY KEY,
                             nome varchar(100),
                             imagem_id integer,
                             FOREIGN KEY (imagem_id) REFERENCES imagens(id),
                             deleted_at timestamp,
                             updated_at timestamp,
                             created_at timestamp
);

CREATE TABLE horario_barbeiro (
                                    horario_id integer,
                                    FOREIGN KEY (horario_id) REFERENCES horarios(id),
                                    barbeiro_id integer,
                                    FOREIGN KEY (barbeiro_id) REFERENCES barbeiros(id),
                                    PRIMARY KEY (horario_id, barbeiro_id)

);

CREATE TABLE servicos (
                            id SERIAL PRIMARY KEY,
                            descricao varchar(50),
                            valor numeric(7,2),
                            deleted_at timestamp,
                            updated_at timestamp,
                            created_at timestamp
);

CREATE TABLE servico_barbeiro (
                                    servico_id integer,
                                    FOREIGN KEY (servico_id) REFERENCES servicos(id),
                                    barbeiro_id integer,
                                    FOREIGN KEY (barbeiro_id) REFERENCES barbeiros(id),
                                    PRIMARY KEY (servico_id, barbeiro_id)
);

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY ,
    nome varchar(100),
    contato varchar(14),
    deleted_at timestamp,
    updated_at timestamp,
    created_at timestamp
);

CREATE TABLE clientes (
                            id SERIAL PRIMARY KEY,
                            imagem_id integer,
                            FOREIGN KEY (imagem_id) REFERENCES imagens(id),
                            usuario_id integer,
                            FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                            cpf varchar(14),
                            email varchar(200),
                            password varchar(32),
                            deleted_at timestamp,
                            updated_at timestamp,
                            created_at timestamp
);

CREATE TABLE caixas (
                          id SERIAL PRIMARY KEY,
                          lucro numeric(10,2),
                          dia date,
                          deleted_at timestamp,
                          updated_at timestamp,
                          created_at timestamp
);

CREATE TYPE Etapa AS ENUM ('PENDENTE', 'CONFIRMADO', 'CANCELADO', 'CONCLUIDO');

CREATE TABLE agendas (
                           id SERIAL PRIMARY KEY,
                           usuario_id integer,
                            FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                           horario_id integer,
                            FOREIGN KEY (horario_id) REFERENCES horarios(id),
                           barbeiro_id integer,
                            FOREIGN KEY (barbeiro_id) REFERENCES barbeiros(id),
                           servico_id integer,
                            FOREIGN KEY (servico_id) REFERENCES servicos(id),
                           caixa_id integer,
                            FOREIGN KEY (caixa_id) REFERENCES caixas(id),
                           dia date,
                           valor numeric(7,2),
                           Etapa etapa,
                           deleted_at timestamp,
                           updated_at timestamp,
                           created_at timestamp
);

 CREATE TABLE posts (
     id SERIAL PRIMARY KEY ,
     cliente_id integer,
     FOREIGN KEY (cliente_id) REFERENCES clientes(id),
     imagem_id integer,
     FOREIGN KEY (imagem_id) REFERENCES imagens(id),
     legenda varchar(250),
     avaliacao integer,
     qtd_curtidas integer,
     deleted_at timestamp,
     updated_at timestamp,
     created_at timestamp
 );

 CREATE TABLE comentarios (
     id SERIAL PRIMARY KEY,
     texto varchar(250),
     qtd_curtidas integer,
     cliente_id integer,
     FOREIGN KEY (cliente_id) REFERENCES clientes(id),
     post_id integer,
     FOREIGN KEY (post_id) REFERENCES posts(id),
     comentario_id integer,
     FOREIGN KEY (comentario_id) REFERENCES comentarios(id),
     deleted_at timestamp,
     updated_at timestamp,
     created_at timestamp
 );

 CREATE TABLE curtidas (
     cliente_id    integer,
     FOREIGN KEY (cliente_id) REFERENCES clientes(id),
     post_id       integer,
     FOREIGN KEY (post_id) REFERENCES posts(id),
     comentario_id integer,
     FOREIGN KEY (comentario_id) REFERENCES comentarios(id),
     CONSTRAINT pk PRIMARY KEY (cliente_id, post_id, comentario_id),
     created_at    timestamp
 );

 CREATE TABLE folgas (
     id SERIAL PRIMARY KEY ,
     dia date
 );

INSERT INTO dias_da_semana (descricao) values ('Segunda-feira');

INSERT INTO dias_da_semana (descricao) values ('Terça-feira');

INSERT INTO dias_da_semana (descricao) values ('Quarta-feira');

INSERT INTO dias_da_semana (descricao) values ('Quinta-feira');

INSERT INTO dias_da_semana (descricao) values ('Sexta-feira');

INSERT INTO dias_da_semana (descricao) values ('Sábado');

INSERT INTO dias_da_semana (descricao) values ('Domingo');