INSERT INTO Fornecedor (nome, morada,nif,telefone) VALUES( 'Joao',  'Rua Santa apolonia', 315682159,963584682);
INSERT INTO Fornecedor (nome, morada,nif,telefone) VALUES('Junior',  'Avenida Das Forcas Unidas', 315642159,963852741);
INSERT INTO Fornecedor (nome, morada,nif,telefone) VALUES( 'Adelio',  'Rua Aveiro II', 315754595,963741852);
INSERT INTO Fornecedor (nome, morada,nif,telefone) VALUES( 'Jailson',  'Porto', 315682893,963456123);
INSERT INTO Fornecedor (nome, morada,nif,telefone) VALUES( 'Chris',  'Rua Santo Antonio', 319342134,963123456);
INSERT INTO Fornecedor (nome, morada,nif,telefone) VALUES( 'Nuno',  'Rua Avenida Das Forca Armadas', 315230150,964723158);
INSERT INTO Fornecedor (nome, morada,nif,telefone) VALUES( 'Samuel',  'Rua 5 de Julho', 315684589,963258741);

INSERT INTO Produto (descricao, unidade_medida,preco) VALUES('rolo papel vegetal', 'cm' ,0.55);
INSERT INTO Produto (descricao, unidade_medida,preco) VALUES( 'Massa', 'm' ,5.00);
INSERT INTO Produto (descricao, unidade_medida,preco) VALUES('tinta', 'l' ,10.65);
INSERT INTO Produto (descricao, unidade_medida,preco) VALUES( 'tijolo', 'kg' ,20.5);
INSERT INTO Produto (descricao, unidade_medida,preco) VALUES( 'relvado sintetico', 'metro' ,70.5);
INSERT INTO Produto (descricao, unidade_medida,preco) VALUES( 'corta relvas ', 'kilograma' ,5000.99);
INSERT INTO Produto (descricao, unidade_medida,preco) VALUES( 'agua destilada', 'litro' ,3.56);
INSERT INTO Produto (descricao, unidade_medida,preco) VALUES( 'parafusos',  'kg' ,2.80);
INSERT INTO Produto (descricao, unidade_medida,preco) VALUES( 'mangueira', ' m' ,1.50);

INSERT INTO Armazem (nome, morada,cidade) VALUES('AKI', 'Rua Santarem' ,'Porto');
INSERT INTO Armazem (nome, morada,cidade) VALUES('Tintas', 'Rua Iago nunes' ,'Porto');
INSERT INTO Armazem (nome, morada,cidade) VALUES('Maxmat', 'Rua do Roubo ' ,'Lisboa');
INSERT INTO Armazem (nome, morada,cidade) VALUES('Parafusos', 'Rua Sara Afonso' ,'Coimbra');
INSERT INTO Armazem (nome, morada,cidade) VALUES('Bricor', 'Rua Gamboa Silva' ,'Braga');
INSERT INTO Armazem (nome, morada,cidade) VALUES('Leroy Merlin', 'Rua Pedro Andrade' ,'Lisboa');
INSERT INTO Armazem (nome, morada,cidade) VALUES('MotorSport', 'Rua Don Enrique' ,'Braganca');

--44
INSERT INTO Empregado (cod_empregado,cod_supervisor,cod_armazem, nome, morada,salario_semanal,formacao) VALUES(1223, 10, 2 ,'Antonio','Salgeiros',760,'Engenharia Biomedica');
INSERT INTO Empregado (cod_empregado,cod_supervisor,cod_armazem, nome, morada,salario_semanal,formacao) VALUES(41, 31, 1 ,'Patrick','Ramalde',790,'Engenharia Eletrotecnica');
INSERT INTO Empregado (cod_empregado,cod_supervisor,cod_armazem, nome, morada,salario_semanal,formacao) VALUES(40, 20, 4 ,'Ana','Francos',800,'Engenharia Informatica');
INSERT INTO Empregado (cod_empregado,cod_supervisor,cod_armazem, nome, morada,salario_semanal,formacao) VALUES(42, 18, 2 ,'Brian','Trindade',720,'Engenharia Mecanica');
INSERT INTO Empregado (cod_empregado,cod_supervisor,cod_armazem, nome, morada,salario_semanal,formacao) VALUES(43, 55, 7 ,'Gelson','Senhora da Hora',720,'Engenharia Mecanica');

INSERT INTO Fornecedorproduto (cod_fornecedor,cod_produto,preco_venda,desconto) VALUES(1, 5, 70 ,12);
INSERT INTO Fornecedorproduto (cod_fornecedor,cod_produto,preco_venda,desconto) VALUES(5, 3, 70 ,35);
INSERT INTO Fornecedorproduto (cod_fornecedor,cod_produto,preco_venda,desconto) VALUES(7, 2, 70 ,19);
INSERT INTO Fornecedorproduto (cod_fornecedor,cod_produto,preco_venda,desconto) VALUES(3, 1, 70 ,8);
INSERT INTO Fornecedorproduto (cod_fornecedor,cod_produto,preco_venda,desconto) VALUES(2, 4, 70 ,0);
INSERT INTO Fornecedorproduto (cod_fornecedor,cod_produto,preco_venda,desconto) VALUES(5, 6, 70 ,1);
INSERT INTO Fornecedorproduto (cod_fornecedor,cod_produto,preco_venda,desconto) VALUES(4, 7, 70 ,75);
INSERT INTO Fornecedorproduto (cod_fornecedor,cod_produto,preco_venda,desconto) VALUES(3, 8, 70 ,25);
INSERT INTO Fornecedorproduto (cod_fornecedor,cod_produto,preco_venda,desconto) VALUES(4, 9, 70 ,50);
INSERT INTO Fornecedorproduto (cod_fornecedor,cod_produto,preco_venda,desconto) VALUES(6, 5, 70 ,40);
INSERT INTO Fornecedorproduto (cod_fornecedor,cod_produto,preco_venda,desconto) VALUES(1, 8, 70 ,30);

INSERT INTO ArmazemProduto (cod_armazem,cod_produto,stock,stock_minimo,corredor,prateleira) VALUES(7, 9, 70 ,12,2,5);
INSERT INTO ArmazemProduto (cod_armazem,cod_produto,stock,stock_minimo,corredor,prateleira) VALUES(5, 2, 40 ,30,2,1);
INSERT INTO ArmazemProduto (cod_armazem,cod_produto,stock,stock_minimo,corredor,prateleira) VALUES(6, 5, 74 ,12,3,2);
INSERT INTO ArmazemProduto (cod_armazem,cod_produto,stock,stock_minimo,corredor,prateleira) VALUES(4, 1, 99 ,20,3,7);
INSERT INTO ArmazemProduto (cod_armazem,cod_produto,stock,stock_minimo,corredor,prateleira) VALUES(2, 2, 150 ,50,3,6);
INSERT INTO ArmazemProduto (cod_armazem,cod_produto,stock,stock_minimo,corredor,prateleira) VALUES(3, 8, 6 ,3,8,5);
INSERT INTO ArmazemProduto (cod_armazem,cod_produto,stock,stock_minimo,corredor,prateleira) VALUES(1, 4, 21 ,5,5,9);
INSERT INTO ArmazemProduto (cod_armazem,cod_produto,stock,stock_minimo,corredor,prateleira) VALUES(2, 1, 55 ,10,7,3);
INSERT INTO ArmazemProduto (cod_armazem,cod_produto,stock,stock_minimo,corredor,prateleira) VALUES(5, 1, 73 ,40,6,7);

INSERT INTO OrdemCompra (cod_fornecedor,cod_empregado,data_compra,valor_total,data_entrega,estado) VALUES(6, 41,('2018/03/01 09:25:45'),30,('2018/10/15 21:55:00'),2);
INSERT INTO OrdemCompra (cod_fornecedor,cod_empregado,data_compra,valor_total,data_entrega,estado) VALUES(1, 40, ('2018/04/25 09:25:45'),35,('2018/04/26 09:25:45'),2);
INSERT INTO OrdemCompra (cod_fornecedor,cod_empregado,data_compra,valor_total,data_entrega,estado) VALUES(3, 43, ('2018/05/16 09:25:45'),150,('2018/06/01 09:25:45'),3);
INSERT INTO OrdemCompra (cod_fornecedor,cod_empregado,data_compra,valor_total,data_entrega,estado) VALUES(6, 42, ('2018/06/27 09:25:45'),8500,('2018/07/12 09:25:45'),3);
INSERT INTO OrdemCompra (cod_fornecedor,cod_empregado,data_compra,valor_total,data_entrega,estado) VALUES(7, 1223, ('2018/07/05 09:25:45'),7500,('2018/08/01 09:25:45'),3);
INSERT INTO OrdemCompra (cod_fornecedor,cod_empregado,data_compra,valor_total,data_entrega,estado) VALUES(2, 40,  ('2018/10/15 09:25:45'),90,('2018/10/15 22:30:00'),2);
Insert INTO OrdemCompra (cod_fornecedor,cod_empregado,data_compra,valor_total,data_entrega,estado) VALues (8,40 ,('2018/07/01 06:25:45'),90,('2018/07/15 05:30:00'),2);

INSERT INTO OrdemCompraProduto (linha, cod_produto,quantidade_solicitada,desconto_pedido) VALUES(1,9,4,20);
INSERT INTO OrdemCompraProduto (linha, cod_produto,quantidade_solicitada,desconto_pedido) VALUES(2, 8,5, 50);
INSERT INTO OrdemCompraProduto (linha, cod_produto,quantidade_solicitada,desconto_pedido) VALUES(3, 7,6, 65);
INSERT INTO OrdemCompraProduto (linha, cod_produto,quantidade_solicitada,desconto_pedido) VALUES(4, 6,10,10);
INSERT INTO OrdemCompraProduto (linha, cod_produto,quantidade_solicitada,desconto_pedido) VALUES(5, 5,30, 15);
INSERT INTO OrdemCompraProduto (linha, cod_produto,quantidade_solicitada,desconto_pedido) VALUES(6, 4,70, 6);


-- mostrar as tabelas com todas as informacoes

select * from Fornecedor;
select * from Produto;
select * from Armazem;
select * from Empregado;
select * from Fornecedorproduto;
select * from ArmazemProduto;
select * from OrdemCompra;
 select * from OrdemCompraProduto;