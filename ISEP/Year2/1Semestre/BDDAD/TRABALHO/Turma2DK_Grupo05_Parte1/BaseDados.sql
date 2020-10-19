

--criar tabelas

create table Fornecedor(
cod_fornecedor Integer generated as identity constraint pk_cd_fornecedor primary key,
nome varchar(20) constraint nm_fornecedor not null,
morada varchar(40) constraint mrd_fornecedor not null,
nif Integer not null check (nif between 100000000 and 999999999),
telefone Integer not null check (telefone between 900000000 and 999999999));

create table Produto (
cod_produto Integer generated as identity constraint pk_cod_produto primary key,
descricao varchar(20) constraint des_prod_descricao not null,
unidade_medida varchar(20) constraint nn_produto_unidade_medida  not null CONSTRAINT ck_produto_unidade_medida 
CHECK (REGEXP_LIKE(unidade_medida, 'cm || centimetro || m || metro || l || litro || kg || kilograma || g || grama||cm3||centimetros cubicos')),
preco NUMERIC(15,3) CONSTRAINT nn_produto_preco NOT NULL CONSTRAINT ck_produto_preco CHECK (preco > 0));

create table Armazem(
cod_armazem Integer generated as identity constraint pk_cod_armazem primary key,
nome varchar(20) constraint nm_armazem not null,
morada varchar(20) constraint mrd_armazem not null,
cidade varchar(20) constraint cd_armazem not null);

create table Empregado(
cod_empregado Integer constraint pk_cd_empregado primary key,
cod_supervisor Integer,
cod_armazem Integer CONSTRAINT fk_cod_armazem REFERENCES Armazem(cod_armazem),
nome varchar(20) constraint nm_empregado not null,
morada varchar(20) constraint mrd_empregado not null,
salario_semanal NUMERIC(15,3) CONSTRAINT nn_empregado_salario_semanal NOT NULL CONSTRAINT ck_empregado_salario_semanal CHECK (salario_semanal > 0),
formacao Varchar(100) CONSTRAINT for_empregado NOT NULL);

create table Fornecedorproduto(
cod_fornecedor Integer, CONSTRAINT fk_cod_fornecedor_fornecedorproduto FOREIGN KEY (cod_fornecedor) REFERENCES Fornecedor(cod_fornecedor)  ON DELETE CASCADE,
cod_produto Integer, CONSTRAINT fk_cod_produto FOREIGN KEY (cod_produto) REFERENCES Produto(cod_produto)ON DELETE CASCADE,
preco_venda NUMERIC(5,2) CONSTRAINT nn_fornecedorProduto_preco_venda NOT NULL CONSTRAINT preco_FonecedorProduto CHECK (preco_venda > 0),
desconto INTEGER CONSTRAINT ck_fornecedorProduto_desconto CHECK (desconto BETWEEN 0 AND 100));

create table ArmazemProduto (
cod_armazem Integer, constraint fk_cod_armazemProduto_armazem FOREIGN KEY (cod_armazem) REFERENCES Armazem(cod_armazem) ON DELETE CASCADE,
cod_produto Integer, constraint fk_armazemProduto_cod_produto FOREIGN KEY(cod_produto)  REFERENCES Produto(cod_produto) on delete cascade,
stock Integer constraint fk_stock_ArmazemProduto NOT NULL constraint stock_ArmazemProduto CHECK (stock >= 0),
stock_minimo Integer constraint fk_stock_minimo_ArmazemProduto NOT NULL constraint stock_minimo_ArmazemProduto CHECK (stock_minimo >= 0),
corredor Integer  not null,
prateleira Integer not null);

create table OrdemCompra(
nr_ordem Integer  generated as identity constraint pk_nr_ordem primary key,
cod_fornecedor Integer CONSTRAINT fk_cod_fornecedor_ordcemcompra REFERENCES Fornecedor(cod_fornecedor) ON DELETE CASCADE,
cod_empregado Integer CONSTRAINT fk_cod_empregado REFERENCES Empregado(cod_empregado) ON DELETE CASCADE,
data_compra TimeStamp CONSTRAINT nn_ordemCompra_data_compra NOT NULL,
valor_total NUMERIC(15,3) CONSTRAINT nn_ordemCompra_valor_total NOT NULL CONSTRAINT ck_ordemCompra_valor_total CHECK (valor_total > 10),
data_entrega TimeStamp CONSTRAINT nn_ordemCompra_data_entrega NOT NULL, CONSTRAINT ck_ordemCompra_data_entrega CHECK (data_compra < data_entrega),
estado INTEGER CONSTRAINT ck_ordemCompra_estado CHECK (REGEXP_LIKE(estado, '[2-3]')) );

create table OrdemCompraProduto(
nr_ordem Integer GENERATED AS IDENTITY, CONSTRAINT fk_nr_ordem FOREIGN KEY (nr_ordem) REFERENCES OrdemCompra(nr_ordem),
linha Integer constraint pk_linha primary key,
cod_produto Integer  REFERENCES Produto(cod_produto),
quantidade_solicitada Integer  CONSTRAINT nn_quantidade_solicitada NOT NULL CONSTRAINT ck_quantidade_solicitada CHECK (quantidade_solicitada >= 0),
desconto_pedido Integer CONSTRAINT ck__desconto_pedido CHECK (desconto_pedido BETWEEN 0 AND 100));
