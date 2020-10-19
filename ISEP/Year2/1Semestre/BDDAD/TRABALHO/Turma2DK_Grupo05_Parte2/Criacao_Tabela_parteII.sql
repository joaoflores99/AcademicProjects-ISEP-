--eliminar tabelas (eventualmente) existentes
DROP TABLE Artigo CASCADE CONSTRAINTS PURGE ;
DROP TABLE Zona_Geografica CASCADE CONSTRAINTS PURGE ;
DROP TABLE Tipo_Veiculo CASCADE CONSTRAINTS PURGE ;
DROP TABLE Fatura CASCADE CONSTRAINTS PURGE ;
DROP TABLE Etapas CASCADE CONSTRAINTS PURGE ;
DROP TABLE Armazem CASCADE CONSTRAINTS PURGE ;
DROP TABLE Zonas CASCADE CONSTRAINTS PURGE ;
DROP TABLE Veiculos CASCADE CONSTRAINTS PURGE;
DROP TABLE Cliente CASCADE CONSTRAINTS PURGE ;
DROP TABLE Pagamentos CASCADE CONSTRAINTS PURGE ;
DROP TABLE Funcionario CASCADE CONSTRAINTS PURGE ;
DROP TABLE Historico_tipo_Cliente CASCADE CONSTRAINTS PURGE;
DROP TABLE Historico_Venda CASCADE CONSTRAINTS PURGE ;
DROP TABLE Zona_Artigo CASCADE CONSTRAINTS PURGE ;
DROP TABLE Nota_Encomenda CASCADE CONSTRAINTS PURGE ;
DROP TABLE Guia_Saida CASCADE CONSTRAINTS PURGE ;
DROP TABLE Guia_Transporte CASCADE CONSTRAINTS PURGE ;
DROP TABLE Viagem CASCADE CONSTRAINTS PURGE ;

--criar tabelas 

create table Artigo(
referencia Integer constraint  pk_referencia primary key,
nome varchar(40) constraint  nome not null,
descricao varchar(40) constraint  nn_descricao not null,
preco_compra integer constraint nn_preco_compra not null
                    constraint ck_preco_compra check (preco_compra>0),
unidade_representacao varchar(20) constraint nn_unidade_representacao  not null 
                                CONSTRAINT ck_unidade_representacao CHECK 
                                (REGEXP_LIKE(unidade_representacao, 'cm || centimetro || m || metro || l || litro || kg || kilograma || g || grama||cm3||centimetros cubicos')),
preco_venda integer constraint nn_preco_venda not null
                    constraint ck_preco_venda check (preco_venda>preco_venda)
);

create table Zona_Geografica (
id_zona_geografica Integer constraint pk_id_zonas_geografica primary key,
nome varchar(10) constraint nm_nome not null
);

create table Tipo_veiculo(
tipo varchar(20) constraint pk_tipo primary key,
volume integer constraint volume_tipo_veiculo not null,
peso integer constraint peso_tipo_veiculo not null
);

create table Fatura(
num_fatura Integer  generated as identity constraint pk_num_fatura primary key,
dados_empresa varchar(100) constraint dados_empresa_fatura not null,
data_emissao TimeStamp CONSTRAINT nn_fatura NOT NULL,
iva integer constraint iva_fatura check(iva>0),
desconto integer constraint desconto_fatura check(desconto>=0)
);

create table Etapas(
num_etapas integer constraint pk_num_etapas primary key,
tipo_ocurrencia varchar(50)
);

create table Armazem(
cod_armazem Integer generated as identity constraint pk_cod_armazem primary key,
id_zona_geografica Integer CONSTRAINT fk_id_zona REFERENCES Zona_Geografica(id_zona_geografica) on delete cascade,
nome varchar(20) constraint nn_nome not null,
morada varchar(20) constraint nn_morada not null,
coordenadas varchar(20) constraint nn_coordenadas  not null
);

create table Zonas (
id_zonas Integer constraint pk_id_zonas primary key,
cod_armazem Integer CONSTRAINT fk_cod_armazem REFERENCES Armazem(cod_armazem) on delete cascade,
capacidade Integer constraint nn_capacidade not null
);

create table Veiculos(
matricula_veiculo varchar(15) constraint pk_matricula_veicula primary key CHECK (REGEXP_LIKE(matricula_veiculo, '[0-9]{2}-[A-Z]{2}-[0-9]{2}|[0-9]{2}-[0-9]{2}-[A-Z]{2}|[A-Z]{2]-[0-9]{2}-[0-9]{2}')),
tipo varchar(20) constraint fk_tipo_veiculo references Tipo_veiculo(tipo) on delete cascade,
marca varchar(20) constraint marca_veiculo not null,
modelo varchar(20) constraint modelo_veiculo not null,
numero_apolice_seguro integer constraint nm_ap_sg_veiculo not null,
num_km integer constraint nm_km_veiculo not null,
num_viagem_semanais integer constraint nm_vgm_smn_veiculo not null check(num_viagem_semanais<=4),
tipo_incidente varchar(40)
);

create table Cliente(
cod_cliente Integer constraint pk_cod_cliente primary key,
id_zona_geografica Integer CONSTRAINT fk_id_zona_cliente REFERENCES Zona_Geografica(id_zona_geografica) on delete cascade,
nome varchar(40) constraint nm_cliente not null,
morada varchar (40) constraint morada_cliente not null,
cod_postal integer constraint cod_postal_cliente not null,
telefone Integer constraint telef_cliente not null check (telefone between 900000000 and 999999999),
num_contribuiente Integer check(num_contribuiente between 100000000 and 999999999)
);

create table Pagamentos(
cod_cliente Integer constraint  pk_cod_cliente_pagamentos primary key not null REFERENCES Cliente(cod_cliente),
valor_pago Integer constraint valor_pago_cliente not null check(valor_pago>=0),
data_pagamento TimeStamp CONSTRAINT dt_pagamento NOT NULL
);

create table Funcionario (
cod_funcionario Integer constraint  pk_cod_funcionario primary key,
cod_armazem Integer constraint fk_cod_armazem_funcionario REFERENCES Armazem(cod_armazem) on delete cascade,
cod_supervisor Integer DEFAULT null,
salario_mensal integer constraint  nn_salario_mensal not null Check (salario_mensal>0),
categoria varchar(40) constraint nn_categoria not null Check (REGEXP_LIKE(categoria,'motorista||fiel armazem||vendedor|| supervisor')),
nome varchar(40) constraint  nn_nome_funcionario not null,
morada varchar(40) constraint nn_morada_funcionario not null,
num_contribuinte Integer constraint nn_num_contribuinte not null 
);

create table Historico_tipo_Cliente (
cod_cliente Integer constraint  pk_cod_cliente_historico primary key REFERENCES Cliente(cod_cLiente) on delete cascade,
tipo_cliente varchar(40) constraint nn_tipo_cliente not null Check (REGEXP_LIKE(tipo_cliente,'VIP||pequeno cliente||grande cliente|| medio cliente')),
data_inicio_tipo TIMESTAMP  constraint nn_data_inicio_tipo not null ,
data_fim_tipo TIMESTAMP  constraint nn_data_fim_tipo not null
);

create table Historico_Venda(
referencia Integer constraint  pk_referencia_historico primary key REFERENCES Artigo(referencia) on delete cascade,
id_zonas Integer constraint fk_id_zona_Historico REFERENCES Zonas(id_zonas) on delete cascade,
preco_venda integer constraint nn_preco_venda_Historico not null check (preco_venda>0),
data_inicio_preco TIMESTAMP  constraint nn_data_inicio_preco not null ,
data_fim_preco TIMESTAMP  constraint nn_data_fim_preco not null                 

);

create table Zona_Artigo(
referencia Integer constraint  pk_referencia_Zona_Artigo primary key REFERENCES Artigo(referencia) on delete cascade, 
id_zonas Integer constraint  pk_id_zona_artigo  REFERENCES Zonas(id_zonas) on delete cascade, 
stock_minimo integer constraint   nn_stock_minimo not null,
stock integer constraint stock not null
);

create table Nota_Encomenda(
id_nota_encomenda integer generated as identity constraint pk_id_nota_encomenda primary key,
cod_funcionario Integer constraint  fk_cod_funcionario_nota_Encomenda REFERENCES Funcionario(cod_Funcionario) on delete cascade,
cod_cliente Integer constraint  fk_cod_cliente_nota_Encomenda REFERENCES Cliente(cod_cliente) on delete cascade,
referencia Integer constraint fk_referencia_nota_encomenda References Artigo(referencia) on delete cascade,
num_fatura Integer constraint fk_num_fatura_nota_encomenda References Fatura(num_fatura) on delete cascade,
estado varchar (20) constraint estado_nota_encomenda not null,
quantidade integer constraint qnt_nota_enquantidade check(quantidade>=0),
preco integer constraint preco_nota_encomenda check(preco>=0)
);

create table Guia_Saida(
id_guia Integer generated as identity constraint pk_id_guia primary key,
id_zonas Integer CONSTRAINT fk_id_zona_guia_saida REFERENCES Zonas(id_zonas) on delete cascade,
id_nota_encomenda Integer CONSTRAINT fk_id_nota_incomneda REFERENCES Nota_Encomenda(id_nota_encomenda) on delete cascade

);

create table Guia_Transporte(
id_guia_transporte Integer constraint  pk_id_guia_transporte primary key,
id_guia  Integer constraint fk_id_guia REFERENCES Guia_Saida(id_guia) on delete cascade,
data_hora_saida Timestamp constraint nn_data_hora_saida not null,
dados_empresa varchar(40) constraint nn_dados_empresa not null
);

create table Viagem(
num_viagem integer constraint pk_num_viagem primary key,
cod_armazem Integer CONSTRAINT fk_cod_armazem_viagem REFERENCES Armazem(cod_armazem) on delete cascade,
matricula_veiculo varchar(15) constraint fk_matricula_veiculo_viagem references Veiculos(matricula_veiculo) on delete cascade,
cod_funcionario Integer constraint  fk_cod_funcionario_nota_viagem REFERENCES Funcionario(cod_Funcionario) on delete cascade,
id_guia_transporte Integer constraint  id_guia_transporte_viagem REFERENCES Guia_Transporte(id_guia_transporte) on delete cascade,
num_etapas Integer constraint  num_etapas_viagem REFERENCES Etapas(num_etapas) on delete cascade,
id_nota_encomenda integer constraint id_nota_encomenda_viagem References Nota_Encomenda(id_nota_encomenda) on delete cascade,
data_partida TimeStamp CONSTRAINT dt_ptd_viagem NOT NULL
);

COMMIT;








































