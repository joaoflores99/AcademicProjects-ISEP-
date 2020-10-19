-- Criar tabelas
DROP TABLE Automoveis CASCADE CONSTRAINTS PURGE;
DROP TABLE Revisoes CASCADE CONSTRAINTS PURGE;
DROP TABLE automoveis_clientes CASCADE CONSTRAINTS PURGE;
DROP TABLE Clientes CASCADE CONSTRAINTS PURGE;


-- TABELA AUTOMÓVEL --
create table Automoveis (
    matricula       CHAR(8)         CONSTRAINT pk_automoveis_matricula PRIMARY KEY                                          ,
    marca           VARCHAR(50)     CONSTRAINT nn_automoveis_marca NOT NULL                                                 ,
    cilindrada      INTEGER         CONSTRAINT nn_automoveis_cilindrada NOT NULL
                                    CONSTRAINT ck_automoveis_cilindrada CHECK (cilindrada BETWEEN 1000 AND 6000)            ,
    ano_fabrico     INTEGER         CONSTRAINT nn_automoveis_ano_fabrico NOT NULL
                                    CONSTRAINT ck_automoveis_ano_fabrico CHECK (ano_fabrico >= 2000)                        ,
    preco_venda     NUMBER(10, 2)   CONSTRAINT nn_automoveis_preco_venda NOT NULL
                                    CONSTRAINT ck_automoveis_preco_venda CHECK (preco_venda > 0)                            ,    
                                    CONSTRAINT ck_automoveis_matricula CHECK (REGEXP_LIKE(matricula, '^[A-Z][2]-\d[2]-\d[2]$ | ^\d[2]-[A-Z][2]-\d[2]$ | ^\d[2]-\d[2]-[A-Z][2]$'))
);

create table Clientes (
    id_cliente                      INTEGER         GENERATED AS IDENTITY
                                                    CONSTRAINT pk_clientes_id_cliente PRIMARY KEY                                                           ,
    nome                            VARCHAR(100)    CONSTRAINT nn_clientes_nome NOT NULL                                                                    ,
    nr_identificacao_civil          INTEGER         CONSTRAINT ck_clientes_nr_identificacao_civil CHECK (REGEXP_LIKE(nr_identificacao_civil, '^\d{6,}$'))   
                                                    CONSTRAINT uk_clientes_nr_identificacao_civil UNIQUE                                                    ,
    nif                             INTEGER         CONSTRAINT nn_clientes_nif NOT NULL
                                                    CONSTRAINT ck_clientes_nif CHECK (REGEXP_LIKE(nif, '^\d{9}$'))                                          
                                                    CONSTRAINT uk_clientes_nif UNIQUE                                                                                                                            
);

create table automoveis_clientes (
    matricula       CHAR(9),
    id_cliente      INTEGER,   
    
    CONSTRAINT pk_automoveis_clientes_matricula_id_cliente PRIMARY KEY (matricula, id_cliente)  
);

create table Revisoes (
    matricula           CHAR(9),
    data_hora_marcacao  TIMESTAMP,
    efetuada            CHAR            DEFAULT 'N'
                                        CONSTRAINT nn_revisoes_efetuada NOT NULL
                                        CONSTRAINT ck_revisoes_efetuada CHECK(REGEXP_LIKE(efetuada, 'S|N', 'i')),
    CONSTRAINT pk_revisoes_matricula_data_ora_marcacao PRIMARY KEY (matricula, data_hora_marcacao)
    
    );
                                    
ALTER TABLE automoveis_clientes ADD CONSTRAINT fk_automoveis_clientes_matricula FOREIGN KEY (matricula) REFERENCES Automoveis(matricula);                                    
ALTER TABLE automoveis_clientes ADD CONSTRAINT fk_automoveis_clientes_id_cliente FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente);                                    
ALTER TABLE Revisoes ADD CONSTRAINT fk_revisoes_matricula FOREIGN KEY (matricula) REFERENCES Automoveis(matricula);