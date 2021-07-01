CREATE DATABASE IF NOT EXISTS contact;
USE contact ;

CREATE TABLE IF NOT EXISTS contact_information
(
  firstname character varying(255) NOT NULL,
  lastname character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  phone character varying(15) NOT NULL,
  address text NOT NULL,
  id integer NOT NULL DEFAULT nextval('contact_information_id_seq'::regclass),
  CONSTRAINT contact_information_pkey PRIMARY KEY (id)
);
