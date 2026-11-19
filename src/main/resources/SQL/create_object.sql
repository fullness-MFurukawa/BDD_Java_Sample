CREATE SEQUENCE IF NOT EXISTS public.category_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9999999999
    CACHE 1;
ALTER SEQUENCE public.category_seq
    OWNER TO postgres;

CREATE SEQUENCE IF NOT EXISTS public.product_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9999999999
    CACHE 1;
ALTER SEQUENCE public.product_seq	
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.category
(
    id integer NOT NULL,
    obj_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (id)
)
TABLESPACE pg_default;
ALTER TABLE IF EXISTS public.category
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.product
(
    id integer NOT NULL,
    obj_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    price integer NOT NULL,
    category_id integer NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id),
    CONSTRAINT product_category_fk FOREIGN KEY (category_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

INSERT INTO public.category VALUES(nextval('public.category_seq'),'d1b34e43-ba64-497d-860b-0054f3039af5','文房具');
INSERT INTO public.product VALUES(nextval('public.product_seq'),'0bd71345-c8b8-423b-9d3f-48d17dcb7874','水性ボールペン(黒)',150,currval('public.category_seq'));
INSERT INTO public.product VALUES(nextval('public.product_seq'),'aad387ae-d993-4a5b-9d8c-40d6b748a570','水性ボールペン(赤)',150,currval('public.category_seq'));
INSERT INTO public.product VALUES(nextval('public.product_seq'),'e7d1bef5-80f0-40e8-b4ff-a9f65c10ed41','水性ボールペン(青)',150,currval('public.category_seq'));
INSERT INTO public.product VALUES(nextval('public.product_seq'),'f2b4222b-d584-4517-a297-53b98c8dbc71','油性ボールペン(黒)',130,currval('public.category_seq'));
INSERT INTO public.product VALUES(nextval('public.product_seq'),'fdb75d1f-0f90-453c-8d4e-a8db01647be3','油性ボールペン(赤)',130,currval('public.category_seq'));
INSERT INTO public.product VALUES(nextval('public.product_seq'),'50ec6f65-1203-4b71-9d21-630462740d54','油性ボールペン(青)',130,currval('public.category_seq'));
INSERT INTO public.product VALUES(nextval('public.product_seq'),'40bf7198-6795-4a3d-bc88-e59e1fa52436','蛍光ペン(黄)',180,currval('public.category_seq'));
INSERT INTO public.product VALUES(nextval('public.product_seq'),'7612d4bd-32b9-4d8e-bb40-a8238f4f4104','蛍光ペン(赤)',180,currval('public.category_seq'));
INSERT INTO public.product VALUES(nextval('public.product_seq'),'51f249c6-d223-4ba1-9e13-8750f0a341eb','蛍光ペン(青)',180,currval('public.category_seq'));
INSERT INTO public.product VALUES(nextval('public.product_seq'),'bec62907-d657-4034-bbfb-651e3f7534f6','蛍光ペン(緑)',180,currval('public.category_seq'));
INSERT INTO public.product VALUES(nextval('public.product_seq'),'cce6eadc-38a5-41fe-950b-48281df253b5','色鉛筆(12色)',900,currval('public.category_seq'));
INSERT INTO public.product VALUES(nextval('public.product_seq'),'bf2a41a6-d662-4999-a580-1fd562f51422','色鉛筆(24色)',1800,currval('public.category_seq'));
INSERT INTO public.product VALUES(nextval('public.product_seq'),'e71c1ee3-f2e8-4e9d-94cb-8beca3f0acd2','色鉛筆(36色)',3200,currval('public.category_seq'));
INSERT INTO public.category VALUES(nextval('public.category_seq'),'485d80c6-d2ff-49dc-90f3-42793e7473ff','雑貨');
INSERT INTO public.category VALUES(nextval('public.category_seq'),'49aaa280-c3cf-4c73-9158-4143eefd6148','PC周辺機器');


