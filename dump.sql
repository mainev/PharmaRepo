--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: mmd; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA mmd;


ALTER SCHEMA mmd OWNER TO postgres;

--
-- Name: SCHEMA mmd; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA mmd IS 'mmd inventory';


--
-- Name: rdr; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA rdr;


ALTER SCHEMA rdr OWNER TO postgres;

--
-- Name: SCHEMA rdr; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA rdr IS 'warehouse inventory';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = mmd, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: issued_packaging_material; Type: TABLE; Schema: mmd; Owner: postgres; Tablespace: 
--

CREATE TABLE issued_packaging_material (
    id integer NOT NULL,
    quantity double precision,
    unit character varying(5),
    product_id integer,
    batch_no character varying(10),
    issued_by character varying(50),
    received_packaging_material_id integer,
    date_issued date
);


ALTER TABLE mmd.issued_packaging_material OWNER TO postgres;

--
-- Name: issued_raw_material; Type: TABLE; Schema: mmd; Owner: postgres; Tablespace: 
--

CREATE TABLE issued_raw_material (
    id integer NOT NULL,
    quantity double precision,
    batch_no character varying(10),
    po_no character varying(10),
    issued_by character varying(100),
    dispensed_by character varying(100),
    unit character varying(5),
    received_raw_material_id integer,
    product_id integer
);


ALTER TABLE mmd.issued_raw_material OWNER TO postgres;

--
-- Name: issued_raw_material_id_seq; Type: SEQUENCE; Schema: mmd; Owner: postgres
--

CREATE SEQUENCE issued_raw_material_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mmd.issued_raw_material_id_seq OWNER TO postgres;

--
-- Name: issued_raw_material_id_seq; Type: SEQUENCE OWNED BY; Schema: mmd; Owner: postgres
--

ALTER SEQUENCE issued_raw_material_id_seq OWNED BY issued_raw_material.id;


--
-- Name: received_packaging_material; Type: TABLE; Schema: mmd; Owner: postgres; Tablespace: 
--

CREATE TABLE received_packaging_material (
    id integer NOT NULL,
    packaging_material_id integer,
    date_received date,
    rr_no character varying(10),
    quantity double precision,
    unit character varying(5),
    qc_control_no character varying(15),
    received_by character varying(50)
);


ALTER TABLE mmd.received_packaging_material OWNER TO postgres;

--
-- Name: received_packaging_material_id_seq; Type: SEQUENCE; Schema: mmd; Owner: postgres
--

CREATE SEQUENCE received_packaging_material_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mmd.received_packaging_material_id_seq OWNER TO postgres;

--
-- Name: received_packaging_material_id_seq; Type: SEQUENCE OWNED BY; Schema: mmd; Owner: postgres
--

ALTER SEQUENCE received_packaging_material_id_seq OWNED BY received_packaging_material.id;


--
-- Name: received_raw_material; Type: TABLE; Schema: mmd; Owner: postgres; Tablespace: 
--

CREATE TABLE received_raw_material (
    id integer NOT NULL,
    date_received date,
    quantity double precision,
    unit character varying(5),
    batch_no character varying(10),
    qc_control_no character varying(15),
    manufacturing_date date,
    exp_date date,
    rr_no character varying(10),
    received_by character varying(50),
    raw_material_id integer
);


ALTER TABLE mmd.received_raw_material OWNER TO postgres;

--
-- Name: received_raw_material_id_seq; Type: SEQUENCE; Schema: mmd; Owner: postgres
--

CREATE SEQUENCE received_raw_material_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mmd.received_raw_material_id_seq OWNER TO postgres;

--
-- Name: received_raw_material_id_seq; Type: SEQUENCE OWNED BY; Schema: mmd; Owner: postgres
--

ALTER SEQUENCE received_raw_material_id_seq OWNED BY received_raw_material.id;


--
-- Name: transferred_packaging_material_id_seq; Type: SEQUENCE; Schema: mmd; Owner: postgres
--

CREATE SEQUENCE transferred_packaging_material_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mmd.transferred_packaging_material_id_seq OWNER TO postgres;

--
-- Name: transferred_packaging_material_id_seq; Type: SEQUENCE OWNED BY; Schema: mmd; Owner: postgres
--

ALTER SEQUENCE transferred_packaging_material_id_seq OWNED BY issued_packaging_material.id;


SET search_path = public, pg_catalog;

--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE client (
    id smallint NOT NULL,
    name character varying(10)
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_id_seq OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE client_id_seq OWNED BY client.id;


--
-- Name: packaging_material; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE packaging_material (
    id integer NOT NULL,
    code character varying(15),
    description character varying(100),
    client_id smallint
);


ALTER TABLE public.packaging_material OWNER TO postgres;

--
-- Name: packaging_material_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE packaging_material_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.packaging_material_id_seq OWNER TO postgres;

--
-- Name: packaging_material_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE packaging_material_id_seq OWNED BY packaging_material.id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE product (
    id integer NOT NULL,
    code character varying(5),
    brand_name character varying(200),
    generic_name character varying(200),
    classification character varying(10),
    client_id smallint
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE product_id_seq OWNED BY product.id;


--
-- Name: raw_material; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE raw_material (
    id integer NOT NULL,
    code character varying(15),
    name character varying(100),
    description character varying(100),
    classification character varying(10),
    client_id smallint
);


ALTER TABLE public.raw_material OWNER TO postgres;

--
-- Name: raw_material_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE raw_material_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.raw_material_id_seq OWNER TO postgres;

--
-- Name: raw_material_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE raw_material_id_seq OWNED BY raw_material.id;


SET search_path = rdr, pg_catalog;

--
-- Name: received_packaging_material; Type: TABLE; Schema: rdr; Owner: postgres; Tablespace: 
--

CREATE TABLE received_packaging_material (
    id integer NOT NULL,
    rr_no character varying(10),
    control_no character varying(15),
    po_no character varying(15),
    quantity integer,
    unit character varying(5),
    quantity_per_bundle integer,
    date_received date,
    supplier character varying(20),
    dr_no character varying(20),
    packaging_material_id integer,
    unit_cost double precision
);


ALTER TABLE rdr.received_packaging_material OWNER TO postgres;

--
-- Name: received_pm_id_seq; Type: SEQUENCE; Schema: rdr; Owner: postgres
--

CREATE SEQUENCE received_pm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rdr.received_pm_id_seq OWNER TO postgres;

--
-- Name: received_pm_id_seq; Type: SEQUENCE OWNED BY; Schema: rdr; Owner: postgres
--

ALTER SEQUENCE received_pm_id_seq OWNED BY received_packaging_material.id;


--
-- Name: received_raw_material; Type: TABLE; Schema: rdr; Owner: postgres; Tablespace: 
--

CREATE TABLE received_raw_material (
    batch_no character varying(10),
    rr_no character varying(10),
    date_received date,
    exp_date date,
    quantity double precision,
    unit character varying(5),
    amount_per_container double precision,
    id integer NOT NULL,
    raw_material_id integer,
    unit_cost double precision
);


ALTER TABLE rdr.received_raw_material OWNER TO postgres;

--
-- Name: received_rm_id_seq; Type: SEQUENCE; Schema: rdr; Owner: postgres
--

CREATE SEQUENCE received_rm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rdr.received_rm_id_seq OWNER TO postgres;

--
-- Name: received_rm_id_seq; Type: SEQUENCE OWNED BY; Schema: rdr; Owner: postgres
--

ALTER SEQUENCE received_rm_id_seq OWNED BY received_raw_material.id;


--
-- Name: transferred_packaging_material; Type: TABLE; Schema: rdr; Owner: postgres; Tablespace: 
--

CREATE TABLE transferred_packaging_material (
    date_transferred date,
    ts_no character varying(10),
    purpose character varying(100),
    quantity integer,
    bundle_no smallint,
    status character varying(15),
    is_transferred boolean,
    id integer NOT NULL,
    received_pm_id integer
);


ALTER TABLE rdr.transferred_packaging_material OWNER TO postgres;

--
-- Name: transferred_pm_id_seq; Type: SEQUENCE; Schema: rdr; Owner: postgres
--

CREATE SEQUENCE transferred_pm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rdr.transferred_pm_id_seq OWNER TO postgres;

--
-- Name: transferred_pm_id_seq; Type: SEQUENCE OWNED BY; Schema: rdr; Owner: postgres
--

ALTER SEQUENCE transferred_pm_id_seq OWNED BY transferred_packaging_material.id;


--
-- Name: transferred_raw_material; Type: TABLE; Schema: rdr; Owner: postgres; Tablespace: 
--

CREATE TABLE transferred_raw_material (
    is_transferred boolean,
    ts_no character varying(10),
    so_no character varying(10),
    date_transferred date,
    unit character varying(5),
    quantity double precision,
    container_no smallint,
    id integer NOT NULL,
    product_id integer,
    received_rm_id integer,
    status character varying(15)
);


ALTER TABLE rdr.transferred_raw_material OWNER TO postgres;

--
-- Name: transferred_rm_id_seq; Type: SEQUENCE; Schema: rdr; Owner: postgres
--

CREATE SEQUENCE transferred_rm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rdr.transferred_rm_id_seq OWNER TO postgres;

--
-- Name: transferred_rm_id_seq; Type: SEQUENCE OWNED BY; Schema: rdr; Owner: postgres
--

ALTER SEQUENCE transferred_rm_id_seq OWNED BY transferred_raw_material.id;


SET search_path = mmd, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: mmd; Owner: postgres
--

ALTER TABLE ONLY issued_packaging_material ALTER COLUMN id SET DEFAULT nextval('transferred_packaging_material_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mmd; Owner: postgres
--

ALTER TABLE ONLY issued_raw_material ALTER COLUMN id SET DEFAULT nextval('issued_raw_material_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mmd; Owner: postgres
--

ALTER TABLE ONLY received_packaging_material ALTER COLUMN id SET DEFAULT nextval('received_packaging_material_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mmd; Owner: postgres
--

ALTER TABLE ONLY received_raw_material ALTER COLUMN id SET DEFAULT nextval('received_raw_material_id_seq'::regclass);


SET search_path = public, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY client ALTER COLUMN id SET DEFAULT nextval('client_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY packaging_material ALTER COLUMN id SET DEFAULT nextval('packaging_material_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY raw_material ALTER COLUMN id SET DEFAULT nextval('raw_material_id_seq'::regclass);


SET search_path = rdr, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: rdr; Owner: postgres
--

ALTER TABLE ONLY received_packaging_material ALTER COLUMN id SET DEFAULT nextval('received_pm_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: rdr; Owner: postgres
--

ALTER TABLE ONLY received_raw_material ALTER COLUMN id SET DEFAULT nextval('received_rm_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: rdr; Owner: postgres
--

ALTER TABLE ONLY transferred_packaging_material ALTER COLUMN id SET DEFAULT nextval('transferred_pm_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: rdr; Owner: postgres
--

ALTER TABLE ONLY transferred_raw_material ALTER COLUMN id SET DEFAULT nextval('transferred_rm_id_seq'::regclass);


SET search_path = mmd, pg_catalog;

--
-- Data for Name: issued_packaging_material; Type: TABLE DATA; Schema: mmd; Owner: postgres
--

INSERT INTO issued_packaging_material VALUES (1, 56, 'ml', 1, 'asd', 'asd', 1, NULL);


--
-- Data for Name: issued_raw_material; Type: TABLE DATA; Schema: mmd; Owner: postgres
--



--
-- Name: issued_raw_material_id_seq; Type: SEQUENCE SET; Schema: mmd; Owner: postgres
--

SELECT pg_catalog.setval('issued_raw_material_id_seq', 1, false);


--
-- Data for Name: received_packaging_material; Type: TABLE DATA; Schema: mmd; Owner: postgres
--

INSERT INTO received_packaging_material VALUES (1, 1, NULL, NULL, NULL, NULL, NULL, NULL);


--
-- Name: received_packaging_material_id_seq; Type: SEQUENCE SET; Schema: mmd; Owner: postgres
--

SELECT pg_catalog.setval('received_packaging_material_id_seq', 1, true);


--
-- Data for Name: received_raw_material; Type: TABLE DATA; Schema: mmd; Owner: postgres
--



--
-- Name: received_raw_material_id_seq; Type: SEQUENCE SET; Schema: mmd; Owner: postgres
--

SELECT pg_catalog.setval('received_raw_material_id_seq', 1, false);


--
-- Name: transferred_packaging_material_id_seq; Type: SEQUENCE SET; Schema: mmd; Owner: postgres
--

SELECT pg_catalog.setval('transferred_packaging_material_id_seq', 1, true);


SET search_path = public, pg_catalog;

--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO client VALUES (1, 'APT-HEALTH');


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('client_id_seq', 1, true);


--
-- Data for Name: packaging_material; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO packaging_material VALUES (1, 'pm1', 'laptop', 1);


--
-- Name: packaging_material_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('packaging_material_id_seq', 1, true);


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO product VALUES (1, 'p11', 'brandx', 'na', 'LIQUID', 1);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('product_id_seq', 1, true);


--
-- Data for Name: raw_material; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO raw_material VALUES (1, 'ww', 'water', 'clear', 'LIQUID', 1);


--
-- Name: raw_material_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('raw_material_id_seq', 1, true);


SET search_path = rdr, pg_catalog;

--
-- Data for Name: received_packaging_material; Type: TABLE DATA; Schema: rdr; Owner: postgres
--

INSERT INTO received_packaging_material VALUES (3, 'dd', NULL, 'dd', 60, NULL, 35, '2015-04-27', 'dd', '4re', 1, 22);
INSERT INTO received_packaging_material VALUES (4, 'okj', NULL, 'jjko', 90, NULL, 60, '2015-04-27', 'ikj', '9oik', 1, 73);
INSERT INTO received_packaging_material VALUES (5, 'fdve', NULL, 'jjko', 90, NULL, 60, '2015-04-27', 'ikj', '9oik', 1, 73);
INSERT INTO received_packaging_material VALUES (6, 'bnmk', NULL, 'hh', 80, NULL, 40, '2015-04-27', 'hh', 'efsa', 1, 35);
INSERT INTO received_packaging_material VALUES (7, 'plo', NULL, 'hjko', 90, NULL, 30, '2015-04-27', 'mmkl', 'hjk', 1, 56);
INSERT INTO received_packaging_material VALUES (8, 'wdfvb', NULL, '4444', 100, NULL, 50, '2015-04-27', '3444', '33e', 1, 34);


--
-- Name: received_pm_id_seq; Type: SEQUENCE SET; Schema: rdr; Owner: postgres
--

SELECT pg_catalog.setval('received_pm_id_seq', 8, true);


--
-- Data for Name: received_raw_material; Type: TABLE DATA; Schema: rdr; Owner: postgres
--

INSERT INTO received_raw_material VALUES ('fff', 'fff', '2015-04-27', '2015-04-17', 60, 'L', 30, 4, 1, 35);
INSERT INTO received_raw_material VALUES ('SSSSS', 'SSSSSS', '2015-04-27', '2017-04-15', 90, 'L', 35, 5, 1, 25);
INSERT INTO received_raw_material VALUES ('werty', 'werty', '2015-04-27', '2017-04-14', 60, 'L', 25, 6, 1, 22);
INSERT INTO received_raw_material VALUES ('aaaa', 'aaaa', '2015-04-27', '2015-04-17', 60, 'L', 25, 7, 1, 55);
INSERT INTO received_raw_material VALUES ('thg', 'vbn', '2015-04-27', '2016-04-23', 100, 'mcL', 50, 8, 1, 2);
INSERT INTO received_raw_material VALUES ('bbb', 'wbbb', '2015-04-30', '2016-04-09', 90, 'L', 35, 9, 1, 55);


--
-- Name: received_rm_id_seq; Type: SEQUENCE SET; Schema: rdr; Owner: postgres
--

SELECT pg_catalog.setval('received_rm_id_seq', 9, true);


--
-- Data for Name: transferred_packaging_material; Type: TABLE DATA; Schema: rdr; Owner: postgres
--

INSERT INTO transferred_packaging_material VALUES ('2015-04-27', 'fff', 'ffff', 25, 2, 'QUARANTINE', true, 4, 3);
INSERT INTO transferred_packaging_material VALUES (NULL, NULL, NULL, 35, 1, 'APPROVED', false, 5, 3);
INSERT INTO transferred_packaging_material VALUES (NULL, NULL, NULL, 60, 1, 'QUARANTINE', false, 7, 4);
INSERT INTO transferred_packaging_material VALUES (NULL, NULL, NULL, 30, 2, 'QUARANTINE', false, 8, 5);
INSERT INTO transferred_packaging_material VALUES (NULL, NULL, NULL, 60, 1, 'QUARANTINE', false, 9, 5);
INSERT INTO transferred_packaging_material VALUES (NULL, NULL, NULL, 40, 2, 'QUARANTINE', false, 10, 6);
INSERT INTO transferred_packaging_material VALUES (NULL, NULL, NULL, 30, 3, 'QUARANTINE', false, 12, 7);
INSERT INTO transferred_packaging_material VALUES ('2015-04-27', 'sd', 'sd', 30, 2, 'QUARANTINE', true, 6, 4);
INSERT INTO transferred_packaging_material VALUES ('2015-04-27', 'hehehe', 'hehehe', 25, 1, 'QUARANTINE', true, 15, 7);
INSERT INTO transferred_packaging_material VALUES ('2015-04-27', 'plmn', 'ooooi', 5, 1, 'QUARANTINE', true, 14, 7);
INSERT INTO transferred_packaging_material VALUES ('2015-04-27', 'ghgvhvhg', 'nbcf', 30, 2, 'QUARANTINE', true, 13, 7);
INSERT INTO transferred_packaging_material VALUES ('2015-04-27', 'sdsd', 'adeg', 40, 1, 'QUARANTINE', true, 11, 6);
INSERT INTO transferred_packaging_material VALUES (NULL, NULL, NULL, 50, 2, 'QUARANTINE', false, 16, 8);
INSERT INTO transferred_packaging_material VALUES ('2015-04-27', 'we3', 'wed3', 50, 1, 'QUARANTINE', true, 17, 8);


--
-- Name: transferred_pm_id_seq; Type: SEQUENCE SET; Schema: rdr; Owner: postgres
--

SELECT pg_catalog.setval('transferred_pm_id_seq', 17, true);


--
-- Data for Name: transferred_raw_material; Type: TABLE DATA; Schema: rdr; Owner: postgres
--

INSERT INTO transferred_raw_material VALUES (true, 'sss', 'sss', '2015-04-27', 'L', 30, 2, 4, 1, 4, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'L', 30, 1, 3, NULL, 4, 'APPROVED');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'L', 35, 2, 5, NULL, 5, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'L', 35, 1, 6, NULL, 5, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (true, 'FFF', 'FFFF', '2015-04-27', 'L', 20, 3, 7, 1, 5, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'L', 10, 3, 8, NULL, 6, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'L', 25, 1, 9, NULL, 6, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'L', 25, 2, 10, NULL, 6, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'L', 10, 3, 11, NULL, 7, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'L', 25, 2, 12, NULL, 7, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'L', 25, 1, 13, NULL, 7, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'mcL', 50, 1, 14, NULL, 8, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (true, 'ddd', 'dddd', '2015-04-27', 'mcL', 50, 2, 15, 1, 8, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'L', 35, 1, 16, NULL, 9, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'L', 20, 3, 17, NULL, 9, 'QUARANTINE');
INSERT INTO transferred_raw_material VALUES (false, NULL, NULL, NULL, 'L', 35, 2, 18, NULL, 9, 'QUARANTINE');


--
-- Name: transferred_rm_id_seq; Type: SEQUENCE SET; Schema: rdr; Owner: postgres
--

SELECT pg_catalog.setval('transferred_rm_id_seq', 18, true);


SET search_path = mmd, pg_catalog;

--
-- Name: issued_raw_material_pkey; Type: CONSTRAINT; Schema: mmd; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY issued_raw_material
    ADD CONSTRAINT issued_raw_material_pkey PRIMARY KEY (id);


--
-- Name: received_packaging_material_pkey; Type: CONSTRAINT; Schema: mmd; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY received_packaging_material
    ADD CONSTRAINT received_packaging_material_pkey PRIMARY KEY (id);


--
-- Name: received_raw_material_pkey; Type: CONSTRAINT; Schema: mmd; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY received_raw_material
    ADD CONSTRAINT received_raw_material_pkey PRIMARY KEY (id);


--
-- Name: transferred_packaging_material_pkey; Type: CONSTRAINT; Schema: mmd; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY issued_packaging_material
    ADD CONSTRAINT transferred_packaging_material_pkey PRIMARY KEY (id);


SET search_path = public, pg_catalog;

--
-- Name: client_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_name_key UNIQUE (name);


--
-- Name: client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: packaging_material_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY packaging_material
    ADD CONSTRAINT packaging_material_pkey PRIMARY KEY (id);


--
-- Name: product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: raw_material_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY raw_material
    ADD CONSTRAINT raw_material_pkey PRIMARY KEY (id);


SET search_path = rdr, pg_catalog;

--
-- Name: received_pm_pkey; Type: CONSTRAINT; Schema: rdr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY received_packaging_material
    ADD CONSTRAINT received_pm_pkey PRIMARY KEY (id);


--
-- Name: received_rm_pkey; Type: CONSTRAINT; Schema: rdr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY received_raw_material
    ADD CONSTRAINT received_rm_pkey PRIMARY KEY (id);


--
-- Name: transferred_pm_pkey; Type: CONSTRAINT; Schema: rdr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY transferred_packaging_material
    ADD CONSTRAINT transferred_pm_pkey PRIMARY KEY (id);


--
-- Name: transferred_rm_pkey; Type: CONSTRAINT; Schema: rdr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY transferred_raw_material
    ADD CONSTRAINT transferred_rm_pkey PRIMARY KEY (id);


SET search_path = mmd, pg_catalog;

--
-- Name: issued_packaging_material_product_id_fkey; Type: FK CONSTRAINT; Schema: mmd; Owner: postgres
--

ALTER TABLE ONLY issued_packaging_material
    ADD CONSTRAINT issued_packaging_material_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: issued_packaging_material_received_packaging_material_id_fkey; Type: FK CONSTRAINT; Schema: mmd; Owner: postgres
--

ALTER TABLE ONLY issued_packaging_material
    ADD CONSTRAINT issued_packaging_material_received_packaging_material_id_fkey FOREIGN KEY (received_packaging_material_id) REFERENCES received_packaging_material(id);


--
-- Name: issued_raw_material_product_id_fkey; Type: FK CONSTRAINT; Schema: mmd; Owner: postgres
--

ALTER TABLE ONLY issued_raw_material
    ADD CONSTRAINT issued_raw_material_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: issued_raw_material_received_raw_material_id_fkey; Type: FK CONSTRAINT; Schema: mmd; Owner: postgres
--

ALTER TABLE ONLY issued_raw_material
    ADD CONSTRAINT issued_raw_material_received_raw_material_id_fkey FOREIGN KEY (received_raw_material_id) REFERENCES received_raw_material(id);


--
-- Name: received_packaging_material_packaging_material_id_fkey; Type: FK CONSTRAINT; Schema: mmd; Owner: postgres
--

ALTER TABLE ONLY received_packaging_material
    ADD CONSTRAINT received_packaging_material_packaging_material_id_fkey FOREIGN KEY (packaging_material_id) REFERENCES public.packaging_material(id);


--
-- Name: received_raw_material_raw_material_id_fkey; Type: FK CONSTRAINT; Schema: mmd; Owner: postgres
--

ALTER TABLE ONLY received_raw_material
    ADD CONSTRAINT received_raw_material_raw_material_id_fkey FOREIGN KEY (raw_material_id) REFERENCES public.raw_material(id);


SET search_path = public, pg_catalog;

--
-- Name: packaging_material_client_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY packaging_material
    ADD CONSTRAINT packaging_material_client_id_fkey FOREIGN KEY (client_id) REFERENCES client(id);


--
-- Name: product_client_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_client_id_fkey FOREIGN KEY (client_id) REFERENCES client(id);


--
-- Name: raw_material_client_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY raw_material
    ADD CONSTRAINT raw_material_client_id_fkey FOREIGN KEY (client_id) REFERENCES client(id);


SET search_path = rdr, pg_catalog;

--
-- Name: received_pm_packaging_material_id_fkey; Type: FK CONSTRAINT; Schema: rdr; Owner: postgres
--

ALTER TABLE ONLY received_packaging_material
    ADD CONSTRAINT received_pm_packaging_material_id_fkey FOREIGN KEY (packaging_material_id) REFERENCES public.packaging_material(id);


--
-- Name: received_rm_raw_material_id_fkey; Type: FK CONSTRAINT; Schema: rdr; Owner: postgres
--

ALTER TABLE ONLY received_raw_material
    ADD CONSTRAINT received_rm_raw_material_id_fkey FOREIGN KEY (raw_material_id) REFERENCES public.raw_material(id);


--
-- Name: transferred_pm_received_pm_id_fkey; Type: FK CONSTRAINT; Schema: rdr; Owner: postgres
--

ALTER TABLE ONLY transferred_packaging_material
    ADD CONSTRAINT transferred_pm_received_pm_id_fkey FOREIGN KEY (received_pm_id) REFERENCES received_packaging_material(id);


--
-- Name: transferred_rm_product_id_fkey; Type: FK CONSTRAINT; Schema: rdr; Owner: postgres
--

ALTER TABLE ONLY transferred_raw_material
    ADD CONSTRAINT transferred_rm_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: transferred_rm_received_rm_id_fkey; Type: FK CONSTRAINT; Schema: rdr; Owner: postgres
--

ALTER TABLE ONLY transferred_raw_material
    ADD CONSTRAINT transferred_rm_received_rm_id_fkey FOREIGN KEY (received_rm_id) REFERENCES received_raw_material(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

