CREATE TABLE public.categoria
(
    id_categoria integer NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT categoria_pkey PRIMARY KEY (id_categoria)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
ALTER TABLE public.categoria OWNER to postgres;


CREATE TABLE public.produto
(
    id_produto integer NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    preco double precision NOT NULL,
    id_categoria integer,
    CONSTRAINT produto_pkey PRIMARY KEY (id_produto),
    CONSTRAINT fkbb0k43mtsufg8bfhq0gyaxhhm FOREIGN KEY (id_categoria)
        REFERENCES public.categoria (id_categoria) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
ALTER TABLE public.produto OWNER to postgres;
