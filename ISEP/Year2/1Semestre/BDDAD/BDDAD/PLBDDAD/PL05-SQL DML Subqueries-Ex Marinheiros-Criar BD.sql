
--eliminar tabelas (eventualmente) existentes

DROP TABLE marinheiros  CASCADE CONSTRAINTS PURGE;
DROP TABLE barcos       CASCADE CONSTRAINTS PURGE;
DROP TABLE reservas     CASCADE CONSTRAINTS PURGE;

--criar tabelas

CREATE TABLE marinheiros(
  id_marinheiro 	INTEGER 	CONSTRAINT pk_marinheiros_id_marinheiro PRIMARY KEY,
  nome 	            VARCHAR(30)	CONSTRAINT nn_marinheiros_nome          NOT NULL,
  classificacao 	INTEGER		CONSTRAINT nn_marinheiros_classificacao NOT NULL,
  idade 		    INTEGER	    CONSTRAINT nn_marinheiros_idade         NOT NULL
);

CREATE TABLE barcos(
  id_barco 	INTEGER     CONSTRAINT pk_barcos_id_barcos 	PRIMARY KEY,
  nome      VARCHAR(20) CONSTRAINT nn_barcos_nome       NOT NULL,
  cor       VARCHAR(10) CONSTRAINT nn_barcos_cor        NOT NULL
);

CREATE TABLE reservas(
  id_marinheiro INTEGER,
  id_barco 	    INTEGER,
  data 	        DATE        CONSTRAINT nn_reservas_data  NOT NULL, 
  CONSTRAINT pk_reservas_id_marinheiro_id_barco PRIMARY KEY(id_marinheiro, id_barco, data)
);

ALTER TABLE reservas ADD CONSTRAINT fk_reservas_id_marinheiro   FOREIGN KEY (id_marinheiro) REFERENCES marinheiros(id_marinheiro);
ALTER TABLE reservas ADD CONSTRAINT fk_reservas_id_barco        FOREIGN KEY (id_barco)      REFERENCES barcos(id_barco);

--preencher tabelas
  
INSERT INTO marinheiros VALUES(22, 'Dustin',  7, 45);
INSERT INTO marinheiros VALUES(29, 'Brutus',  1, 33);
INSERT INTO marinheiros VALUES(31, 'Lubber',  8, 55);
INSERT INTO marinheiros VALUES(32, 'Andy',    8, 25);
INSERT INTO marinheiros VALUES(58, 'Rusty',  10, 35);
INSERT INTO marinheiros VALUES(64, 'Horácio', 7, 35);
INSERT INTO marinheiros VALUES(71, 'Zorba',  10, 16);
INSERT INTO marinheiros VALUES(74, 'Horácio', 9, 35);
INSERT INTO marinheiros VALUES(85, 'Art',     3, 25);
INSERT INTO marinheiros VALUES(95, 'Bob',     3, 63);
INSERT INTO marinheiros VALUES(13, 'Popeye',  3, 22);
INSERT INTO marinheiros VALUES(44, 'Haddock', 3, 63);

INSERT INTO barcos VALUES(101, 'Interlake', 'azul');
INSERT INTO barcos VALUES(102, 'Interlake', 'vermelho');
INSERT INTO barcos VALUES(103, 'Clipper',   'verde');
INSERT INTO barcos VALUES(104, 'Marine',    'vermelho');
  
INSERT INTO reservas VALUES(22, 101, TO_DATE('2017/10/10','yyyy/mm/dd'));
INSERT INTO reservas VALUES(22, 102, TO_DATE('2017/10/10','yyyy/mm/dd'));
INSERT INTO reservas VALUES(22, 103, TO_DATE('2017/08/10','yyyy/mm/dd'));
INSERT INTO reservas VALUES(22, 104, TO_DATE('2017/07/10','yyyy/mm/dd'));
INSERT INTO reservas VALUES(31, 102, TO_DATE('2017/10/11','yyyy/mm/dd'));
INSERT INTO reservas VALUES(31, 103, TO_DATE('2017/06/11','yyyy/mm/dd'));
INSERT INTO reservas VALUES(31, 104, TO_DATE('2017/12/11','yyyy/mm/dd'));
INSERT INTO reservas VALUES(64, 101, TO_DATE('2017/05/09','yyyy/mm/dd'));
INSERT INTO reservas VALUES(64, 102, TO_DATE('2017/08/09','yyyy/mm/dd'));
INSERT INTO reservas VALUES(64, 102, TO_DATE('2017/09/09','yyyy/mm/dd'));
INSERT INTO reservas VALUES(64, 102, TO_DATE('2017/10/09','yyyy/mm/dd'));
INSERT INTO reservas VALUES(74, 103, TO_DATE('2017/08/09','yyyy/mm/dd'));
INSERT INTO reservas VALUES(44, 101, TO_DATE('2018/05/09','yyyy/mm/dd'));
INSERT INTO reservas VALUES(44, 101, TO_DATE('2018/09/09','yyyy/mm/dd'));