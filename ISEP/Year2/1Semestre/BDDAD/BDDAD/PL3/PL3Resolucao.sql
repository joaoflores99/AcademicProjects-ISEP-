--eliminar tabelas (eventualmente) existentes

DROP TABLE  automoveis           CASCADE CONSTRAINTS PURGE;
DROP TABLE  automoveis_clientes  CASCADE CONSTRAINTS PURGE;
DROP TABLE  clientes             CASCADE CONSTRAINTS PURGE;
DROP TABLE  revisoes             CASCADE CONSTRAINTS PURGE;

--criar tabelas

CREATE TABLE automoveis(

matricula     CHAR(8)        CONSTRAINT pk_automoveis_matricula PRIMARY KEY
                             CONSTRAINT nn_automoveis_matricula NOT NULL,
                          -- CONSTRAINT ck_automoveis_matricula CHECK (REGEXP_LIKE(matricula, '[0-9][2]-[A-Z][2]-[0-9][2]|[A-Z][2]-[0-9][2]-[0-9][2]|[0-9][2]-[0-9][2]-[A-Z][2]')),
                          -- CONSTRAINT ck_automoveis_matricula CHECK (REGEXP_LIKE(matricula, '^[A-Z](2)-\d(2)-\d(2)$-|^\d(2)-[A-Z](2)-\d(2)$|^\d(2)-\d(2)-[A-Z](2)$'))
                          
marca         VARCHAR(40)    CONSTRAINT nn_automoveis_marca NOT NULL,

cilindrada    INTEGER        CONSTRAINT nn_automoveis_cilindrada NOT NULL
                             CONSTRAINT ck_automoveis_cilindrada CHECK (cilindrada BETWEEN 1000 AND 6000),
                             
ano_fabrico   INTEGER        CONSTRAINT nn_automoveis_ano_fabrico NOT NULL
                             CONSTRAINT ck_automoveis_ano_fabrico CHECK (ano_fabrico BETWEEN 2000 AND 2018), -- sysdate só deve ser usado em default
                             
preco_venda   NUMBER(10,2)   CONSTRAINT nn_automoveis_preco_venda NOT NULL
                             CONSTRAINT ck_automoveis_preco_venda CHECK (preco_venda>0)

);

CREATE TABLE automoveis_clientes(
matricula              CHAR(8),

id_cliente             INTEGER 

);

CREATE TABLE clientes(

id_cliente              INTEGER GENERATED AS IDENTITY   CONSTRAINT pk_cliente_id_cliente PRIMARY KEY,

nome                    VARCHAR(20)                     CONSTRAINT nn_cliente_nome NOT NULL,

nr_identificacao_civil  INTEGER                         CONSTRAINT uk_clientes_nr_identificacao_civil UNIQUE,
                                                        CONSTRAINT ck_clientes_nr_identificacao_civil CHECK (REGEXP_LIKE(nr_identificacao_civil, '^\d(6,)$')),
                                                       
nif                     INTEGER                         CONSTRAINT uk_clientes_nif UNIQUE  
                                                        CONSTRAINT ck_clientes_nif CHECK(REGEXP_LIKE(nif, '^\d(9)§'))
                                                        CONSTRAINT nn_clientes_nif NOT NULL,
data_nascimento         DATE

);
    

CREATE TABLE revisoes(

matricula           CHAR(8),

data_hora_marcacao  DATE         CONSTRAINT pf_revisoes_data_hora_marcacao PRIMARY KEY,

efetuada            VARCHAR(1)   DEFAULT 'N'
                                 CONSTRAINT ck_revisoes_efetuada CHECK (REGEXP_LIKE(efetuada, 'S|N' ,'i'))  --case insensitive

);

ALTER TABLE automoveis_clientes ADD CONSTRAINT fk_automoveis_clientes_matricula FOREIGN KEY (matricula) REFERENCES automoveis(matricula);
ALTER TABLE automoveis_clientes ADD CONSTRAINT fk_automoveis_clientes_id_cliente FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente);
ALTER TABLE revisoes ADD CONSTRAINT fk_revisoes_matricula FOREIGN KEY (matricula) REFERENCES automoveis(matricula);

--preencher tabelas

INSERT INTO automoveis VALUES('45-PD-98', 'Mercedes', 2300, 2000, 34050);
INSERT INTO automoveis VALUES('65-87-GR', 'Nissan', 1700, 2009, 23490.5);
INSERT INTO automoveis VALUES('42-87-GR', 'Kia', 1300, 2008, 20870);
INSERT INTO automoveis VALUES('BL-87-23', 'Volkswagen', 1100, 2017, 15600.75);
INSERT INTO automoveis VALUES('83-QD-27', 'BMW', 2100, 2014, 35600);
INSERT INTO automoveis VALUES('XO-65-98', 'Toyota', 2100, 2010, 15940);

INSERT INTO clientes VALUES(DEFAULT, 'Sérgio Conceição', 987345, 105098124, '1974-11-15');
INSERT INTO clientes VALUES(DEFAULT, 'António Oliveira', 937587, 104052455, '1952-10-06');
INSERT INTO clientes VALUES(DEFAULT, 'Fernando Santos', NULL, 102000906, '1954-10-10');
INSERT INTO clientes VALUES(DEFAULT, 'Artur Jorge', 7098428, 100829087, '1946-02-13');
INSERT INTO clientes VALUES(DEFAULT, 'Jesualdo Ferreira', NULL, 1075559969, '1946-05-24');

INSERT INTO automoveis_clientes VALUES('65-87-GR', 1);
INSERT INTO automoveis_clientes VALUES('83-QD-27', 4);
INSERT INTO automoveis_clientes VALUES('42-90-AS', 2);
INSERT INTO automoveis_clientes VALUES('45-PD-98', 1);
INSERT INTO automoveis_clientes VALUES('XO-65-98', 5);
INSERT INTO automoveis_clientes VALUES('BL-87-23', 3);

INSERT INTO revisoes VALUES('65-87-GR', '2018-10-04 09:00:00', 'N');
INSERT INTO revisoes VALUES('83-QD-27', '2018-11-04 14:45:00', 'N');
INSERT INTO revisoes VALUES('42-90-AS', '2018-10-23 10:50:00', 'N');
INSERT INTO revisoes VALUES('XO-65-98', '2018-12-01 18:30:00', 'N');
INSERT INTO revisoes VALUES('65-87-GR', '2015-06-07 10:50:00', 'S');
INSERT INTO revisoes VALUES('XO-65-98', '2016-11-22 12:20:00', 'S');

