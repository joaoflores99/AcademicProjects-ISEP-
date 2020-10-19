DROP TABLE automoveis CASCADE CONSTRAINTS PURGE;
DROP TABLE automoveis_clientes CASCADE CONSTRAINTS PURGE;
DROP TABLE clientes CASCADE CONSTRAINTS PURGE;
DROP TABLE revisoes CASCADE CONSTRAINTS PURGE;

CREATE TABLE automoveis (
    matricula    CHAR(8)        CONSTRAINT pk_automoveis_matricula PRIMARY KEY
                                CONSTRAINT ck_automoveis_matricula CHECK (REGEXP_LIKE(matricula, '[0-9]{2}-[A-Z]{2}-[0-9]{2}|[0-9]{2}-[0-9]{2}-[A-Z]{2}|[A-Z]{2]-[0-9]{2}-[0-9]{2}')),
    marca        VARCHAR(40)    CONSTRAINT nn_automoveis_marca NOT NULL,
    cilindrada   INTEGER        CONSTRAINT nn_automoveis_cilindrada NOT NULL
                                CONSTRAINT ck_automoveis_cilindrada CHECK (cilindrada BETWEEN 1000 AND 6000),                            
    ano_fabrico  INTEGER        CONSTRAINT nn_automoveis_ano_fabrico NOT NULL
                                CONSTRAINT ck_automoveis_ano_fabrico CHECK (ano_fabrico BETWEEN 2000 AND 2018),
    preco_venda  NUMERIC(10,2)  CONSTRAINT nn_automoveis_preco_venda NOT NULL
                                CONSTRAINT ck_automoveis_preco_venda CHECK (preco_venda > 0)
);
 
CREATE TABLE clientes (
    id_cliente              INTEGER GENERATED AS IDENTITY  CONSTRAINT pk_clientes_id_cliente PRIMARY KEY,
    nome                    VARCHAR(40)                    CONSTRAINT nn_clientes_nome NOT NULL,
    nr_identificacao_civil  INTEGER                        CONSTRAINT ck_clientes_nr_identificacao_civil CHECK(REGEXP_LIKE(nr_identificacao_civil, '^\d{6,}$'))
                                                           CONSTRAINT uk_clientes_nr_identificacao_civil UNIQUE,
    nif                     INTEGER                        CONSTRAINT nn_clientes_nif NOT NULL
                                                           CONSTRAINT ck_clientes_nif CHECK(REGEXP_LIKE(nif, '^\d{9}$'))
                                                           CONSTRAINT uk_clientes_nif UNIQUE,
    data_nascimento         DATE                           CONSTRAINT nn_clientes_data_nascimento NOT NULL
);
 
CREATE TABLE automoveis_clientes (
        matricula CHAR(8),
        id_cliente INTEGER,
        
                                CONSTRAINT pk_automoveis_clientes_matricula_id_cliente  PRIMARY KEY (matricula,id_cliente)
        
    );
    
        CREATE TABLE revisoes (
        
    matricula     CHAR(8),
    data_hora_marcacao  TIMESTAMP,
    efetuada   CHAR  DEFAULT 'N'
            CONSTRAINT nn_revisoes_Efetuada NOT NULL
            CONSTRAINT ck_Revisoes_efetuada CHECK(UPPER(efetuada) IN ('S','N')),
            
        CONSTRAINT pk_revisoes_matricula_data_hora_marcacao PRIMARY KEY (matricula, data_hora_marcacao)
);



ALTER TABLE automoveis_clientes ADD CONSTRAINT fk_automoveis_clientes_matricula FOREIGN KEY (matricula) REFERENCES automoveis(matricula); 
ALTER TABLE automoveis_clientes ADD CONSTRAINT fk_automoveis_clientes_id_cliente FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente);
ALTER TABLE revisoes ADD CONSTRAINT fk_revisoes_matricula FOREIGN KEY (matricula) REFERENCES automoveis(matricula);



Insert Into automoveis VALUES ('45-PD-98','Mercedes',2300,2000,34050);
Insert Into automoveis VALUES('65-87-GR','Nissan',1700,2009,23490.50);
Insert Into automoveis VALUES('42-90-AS','Kia',1300,2008,20870);
Insert Into automoveis VALUES('BL-87-23','Wolkswagen',1100,2017,15600.75);
Insert Into automoveis VALUES('83-QD-27','BMW',2100,2014,34600);
Insert Into automoveis VALUES('XO-65-98','Toyota',2100,2010,15940);

Insert Into clientes Values (1,'Sergio Conceição',987345,105098124,1974-11-15);
Insert Into clientes Values (2,'Antonio Oliveira',937587,104052455,1952-10-06);
Insert Into clientes Values (3,'Fernando Santos',NULL,102000906,1954-10-10);
Insert Into clientes Values (4,'Artur Jorge',7098428,100829087,1946-02-13);
Insert Into clientes Values (5,'Jesualdo Ferreira',Null,107559969,1946-05-24);

Insert into automoveis_clientes Values('65-87-GR',1);
Insert into automoveis_clientes Values('83-QD-27',4);
Insert into automoveis_clientes Values('42-90-AS',2);
Insert into automoveis_clientes Values('45-PD-98',1);
Insert into automoveis_clientes Values('XO-65-98',5);
Insert into automoveis_clientes Values('BL-87-23',3);

Insert into revisoes Values ('65-87-GR','2018-10-04 09:00:00','N');
Insert into revisoes Values ('83-QD-27','2018-11-04 14:45:00','N');
Insert into revisoes Values ('42-90-AS','2018-10-23 10:50:00','N');
Insert into revisoes Values ('XO-65-98','2018-12-01 18:30:00','N');
Insert into revisoes Values ('65-87-GR','2015-06-07 10:50:00','S');
Insert into revisoes Values ('XO-65-98','2016-11-22 12:20:00','S');

ALTER TABLE revisoes DROP CONSTRAINT pk_revisoes_data_hora_marcacao;
ALTER TABLE revisoes ADD id_revisoes INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT 
ck_clientes_id_cliente CHECK(id_cliente > 0);
ALTER TABLE revisoes ADD CONSTRAINT pk_revisoes_id_revisoes PRIMARY KEY; 
ALTER TABLE revisoes ADD CONSTRAINT uq_revisoes UNIQUE(matricula,data_hora_marcacao);
ALTER TABLE revisoes ADD CONSTRAINT nn_revisoes_matriculas NOT NULL;
ALTER TABLE revisoes ADD CONSTRAINT nn_revisoes_data_hora_marcacao NOT NULL;

COMMIT; 