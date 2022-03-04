----- Table: public.categoria

-- DROP TABLE public.categoria;

CREATE TABLE public.categoria
(
    id bigint NOT NULL,
    nome character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT categoria_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.categoria OWNER to postgres;

----- Table: public.musica

-- DROP TABLE public.musica;

CREATE TABLE public.musica
(
    id bigint NOT NULL,
    autor character varying(255) COLLATE pg_catalog."default",
    data date,
    letra oid,
    titulo character varying(255) COLLATE pg_catalog."default",
    id_categoria bigint NOT NULL,
    CONSTRAINT musica_pkey PRIMARY KEY (id),
    CONSTRAINT fk2o3u7w2d0rfw9h69ghrer51fq FOREIGN KEY (id_categoria)
        REFERENCES public.categoria (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.musica OWNER to postgres;
