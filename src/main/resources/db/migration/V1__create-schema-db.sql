CREATE TABLE "horarios" (
                            "id" SERIAL PRIMARY KEY,
                            "inicio" time,
                            "fim" time,
                            "deleted_at" timestamp,
                            "updated_at" timestamp,
                            "created_at" timestamp
);

CREATE TABLE "barbeiros" (
                             "id" SERIAL PRIMARY KEY,
                             "nome" varchar(100),
                             "link_imagem" varchar(255),
                             "deleted_at" timestamp,
                             "updated_at" timestamp,
                             "created_at" timestamp
);

CREATE TABLE "horario_barbeiro" (
                                    "horario_id" integer,
                                    FOREIGN KEY (horario_id) REFERENCES horarios(id),
                                    "barbeiro_id" integer,
                                    FOREIGN KEY (barbeiro_id) REFERENCES barbeiros(id)
);

CREATE TABLE "servicos" (
                            "id" SERIAL PRIMARY KEY,
                            "descricao" varchar(50),
                            "valor" numeric(7,2),
                            "duracao" time,
                            "deleted_at" timestamp,
                            "updated_at" timestamp,
                            "created_at" timestamp
);

CREATE TABLE "servico_barbeiro" (
                                    "barbeiro_id" integer,
                                    FOREIGN KEY (barbeiro_id) REFERENCES barbeiros(id),
                                    "servico_id" integer,
                                    FOREIGN KEY (servico_id) REFERENCES servicos(id)
);

CREATE TABLE "clientes" (
                            "id" SERIAL PRIMARY KEY,
                            "nome" varchar(100),
                            "cpf" varchar(14),
                            "contato" varchar(14),
                            "deleted_at" timestamp,
                            "updated_at" timestamp,
                            "created_at" timestamp
);

CREATE TABLE "caixas" (
                          "id" SERIAL PRIMARY KEY,
                          "lucro" numeric(10,2),
                          "dia" date,
                          "deleted_at" timestamp,
                          "updated_at" timestamp,
                          "created_at" timestamp
);

CREATE TYPE etapa AS ENUM ('pendente', 'concluido', 'cancelado', 'remarcado');

CREATE TABLE "agendas" (
                           "id" SERIAL PRIMARY KEY,
                           "cliente_id" integer,
                            FOREIGN KEY (cliente_id) REFERENCES clientes(id),
                           "horario_id" integer,
                            FOREIGN KEY (horario_id) REFERENCES horarios(id),
                           "barbeiro_id" integer,
                            FOREIGN KEY (barbeiro_id) REFERENCES barbeiros(id),
                           "servico_id" integer,
                            FOREIGN KEY (servico_id) REFERENCES servicos(id),
                           "caixa_id" integer,
                            FOREIGN KEY (caixa_id) REFERENCES "caixas"(id),
                           "dia" date,
                           "valor" numeric(7,2),
                           "etapa" etapa,
                           "deleted_at" timestamp,
                           "updated_at" timestamp,
                           "created_at" timestamp
);




