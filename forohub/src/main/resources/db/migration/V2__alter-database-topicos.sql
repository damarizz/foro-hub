ALTER TABLE topicos
    ADD CONSTRAINT titulo_unique UNIQUE (titulo);

ALTER TABLE topicos
    ADD CONSTRAINT mensaje_unique UNIQUE (mensaje);