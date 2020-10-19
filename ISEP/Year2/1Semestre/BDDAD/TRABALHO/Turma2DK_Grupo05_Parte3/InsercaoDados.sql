Insert into Zona_Geografica (id_zona_geografica,nome) values (1,'Porto') ;
Insert into Zona_Geografica (id_zona_geografica,nome) values (4,'Margem Sul') ;
 
Insert into Armazem (id_zona_geografica,nome,morada,coordenadas) values (1,'LEROY','Matosinhos','30N-50S');
Insert into Armazem (id_zona_geografica,nome,morada,coordenadas) values (4,'MAXMAT','PVZ','40N-50S');
 
Insert into Artigo (referencia,nome,descricao,preco_compra,unidade_representacao,preco_venda) values (125,'Martelo','xpto',11,'cm',12);
Insert into Artigo (referencia,nome,descricao,preco_compra,unidade_representacao,preco_venda) values (14,'pregos','jesus',30,'mm',33);
Insert into Artigo (referencia,nome,descricao,preco_compra,unidade_representacao,preco_venda) values (15,'trator','trator ligeiro',32000,'unidade',33000);
 
Insert into Zonas (id_zonas,cod_armazem,capacidade) values (1,1,5000) ;
Insert into Zonas (id_zonas,cod_armazem,capacidade) values (4,2,3250) ;
Insert into Zonas (id_zonas,cod_armazem,capacidade) values (5,2,4000) ;
 
Insert into Zona_Artigo (referencia,cod_armazem,id_zonas,stock_minimo,stock) values(125,1,1,300,400);
Insert into Zona_Artigo (referencia,cod_armazem,id_zonas,stock_minimo,stock) values(14,2,4,100,450);
Insert into Zona_Artigo (referencia,cod_armazem,id_zonas,stock_minimo,stock) values(125,1,4,50,100);
Insert into Zona_Artigo (referencia,cod_armazem,id_zonas,stock_minimo,stock) values(14,2,5,450,500);
Insert into Zona_Artigo (referencia,cod_armazem,id_zonas,stock_minimo,stock) values(15,2,5,10,5);
 
Insert into Tipo_veiculo (tipo,volume,peso) values('2 eixos',10,500);
Insert into Tipo_veiculo (tipo,volume,peso) values('4 eixos',100,900);
 
Insert into Marca (marca,modelo) values('Ford','A3');
Insert into Marca (marca,modelo) values('Opel','Transposter');
 
--Insert into Veiculos (matricula_veiculo,marca,tipo,numero_apolice_seguro,num_km,disponibilidade) values('48-AH-96','Ford','2 eixos',4535,47,'DISPONIVEL');
--Insert into Veiculos (matricula_veiculo,marca,tipo,numero_apolice_seguro,num_km,disponibilidade) values('58-26-HM','Opel','4 eixos',3568,52,'INDISPONIVEL');
 
Insert into Cliente (cod_cliente,id_zona_geografica,nome,morada,cod_postal,telefone,num_contribuiente,tipo_cliente,data_ultima_atualizacao) values(47,1,'Walter','Trindade','1244-635',965863214,125689654,'vip','2018/06/27');
Insert into Cliente (cod_cliente,id_zona_geografica,nome,morada,cod_postal,telefone,num_contribuiente,tipo_cliente,data_ultima_atualizacao) values(5,4,'Victor','Amora','5684-325',986574258,365954832,'Pequeno Cliente','2017/09/30');
 
Insert into Pagamentos (data_pagamento,cod_cliente,valor_pago) values(('2018/03/01 09:25:45'),47,50);
Insert into Pagamentos (data_pagamento,cod_cliente,valor_pago) values(('2018/05/17 15:03:26'),5,66000);
 
Insert into Historico_tipo_Cliente (data_inicio_tipo,cod_cliente,tipo_cliente,data_fim_tipo) values(('2018/03/01'),47,'Pequeno Cliente',('2018/03/30'));
Insert into Historico_tipo_Cliente (data_inicio_tipo,cod_cliente,tipo_cliente,data_fim_tipo) values(('2017/05/17'),5,'Grande Cliente',('2018/06/5'));
 
Insert into Historico_Venda (data_inicio_preco,referencia,preco_venda,data_fim_preco) values(('2018/01/01 00:00:01'),125,12,('2018/01/31 23:59:59'));
Insert into Historico_Venda (data_inicio_preco,referencia,preco_venda,data_fim_preco) values(('2018/01/01 00:00:01'),14,33,('2018/01/31 23:59:59'));

Insert into Funcionario (cod_funcionario,cod_armazem,salario_mensal,categoria,nome,morada,num_contribuientef) values(49,1,980,'vendedor','Willian','Porto',123658563);
Insert into Funcionario (cod_funcionario,cod_armazem,salario_mensal,categoria,nome,morada,num_contribuientef) values(34,2,980,'motorista','Edy','Matosinhos',623548556); 
  
Insert into Nota_Encomenda (cod_funcionario,cod_cliente,referencia,estado,quantidade,preco) values(34,47,125,'Pendente',43,65);
Insert into Nota_Encomenda (cod_funcionario,cod_cliente,referencia,estado,quantidade,preco) values(49,47,14,'Processado',3,16);
Insert into Nota_Encomenda (cod_funcionario,cod_cliente,referencia,estado,quantidade,preco) values(34,5,15,'Processado',2,33000);

Insert into Fatura (cod_cliente,id_nota_encomenda,cod_funcionario,dados_empresa,cabecalho,data_emissao,estado) values(47,1,49,'IsepBricolage','Bricolage Decoracao e Jardim','2018/01/01','Pendente');
Insert into Fatura (cod_cliente,id_nota_encomenda,cod_funcionario,dados_empresa,cabecalho,data_emissao,estado) values(5,3,34,'IsepBricolage','Bricolage Decoracao e Jardim','2016/05/01','Pago');
 
Insert into Fatura_Artigo (num_fatura,referencia,quantidade,valor_base,iva,desconto) values(1,125,220,30,0.12,0.30);
Insert into Fatura_Artigo (num_fatura,referencia,quantidade,valor_base,iva,desconto)values(1,14,200,7,0.23,0.4);
Insert into Fatura_Artigo (num_fatura,referencia,quantidade,valor_base,iva,desconto)values(2,15,2,33000,0.13,0.35);

Insert into Guia_Transporte (id_guia_transporte,id_nota_encomenda,data_hora_saida,dados_empresa) values(1,1,('2018/02/01 09:45:34'),'IsepBricolage');
Insert into Guia_Transporte (id_guia_transporte,id_nota_encomenda,data_hora_saida,dados_empresa) values(2,2,('2018/05/20 14:20:03'),'IsepBricolage');
 
Insert into Etapas (num_etapas,tipo_ocurrencia) values(1,'transito');
Insert into Etapas (num_etapas,tipo_ocurrencia) values(2,'acidente');
 
--Insert into Viagem (num_viagem,cod_armazem,matricula_veiculo,cod_funcionario,id_guia_transporte,num_etapas,id_nota_encomenda,data_partida,notificacao) 
--values(1,1,'48-AH-96',49,1,1,1,('2018/05/20'),'HOUVE INCIDENTE');
--Insert into Viagem (num_viagem,cod_armazem,matricula_veiculo,cod_funcionario,id_guia_transporte,num_etapas,id_nota_encomenda,data_partida,notificacao) 
--values(7,2,'58-26-HM',34,2,2,2,('2018/02/01'),'NAO HOUVE INCIDENTE');
 
 Insert into Veiculos (matricula_veiculo,marca,tipo,numero_apolice_seguro,num_km,disponibilidade) values('48-AH-96','Ford','2 eixos',4535,47,'DISPONIVEL');
Insert into Veiculos (matricula_veiculo,marca,tipo,numero_apolice_seguro,num_km,disponibilidade) values('58-26-HM','Opel','4 eixos',3568,52,'INDISPONIVEL');
 
 
Insert into Viagem (num_viagem,cod_armazem,matricula_veiculo,cod_funcionario,id_guia_transporte,num_etapas,id_nota_encomenda,data_partida,notificacao) 
values(1,1,'48-AH-96',49,1,1,1,('2018/05/20'),'HOUVE INCIDENTE');
Insert into Viagem (num_viagem,cod_armazem,matricula_veiculo,cod_funcionario,id_guia_transporte,num_etapas,id_nota_encomenda,data_partida,notificacao) 
values(2,2,'58-26-HM',34,2,2,2,('2018/10/01'),'NAO HOUVE INCIDENTE');

Insert into Viagem (num_viagem,cod_armazem,matricula_veiculo,cod_funcionario,id_guia_transporte,num_etapas,id_nota_encomenda,data_partida,notificacao) 
values(3,1,'48-AH-96',49,1,1,1,('2018/11/29 '),'HOUVE INCIDENTE');
Insert into Viagem (num_viagem,cod_armazem,matricula_veiculo,cod_funcionario,id_guia_transporte,num_etapas,id_nota_encomenda,data_partida,notificacao) 
values(4,1,'48-AH-96',34,2,2,2,('2018/02/01'),'NAO HOUVE INCIDENTE');
Insert into Viagem (num_viagem,cod_armazem,matricula_veiculo,cod_funcionario,id_guia_transporte,num_etapas,id_nota_encomenda,data_partida,notificacao) 
values(5,1,'48-AH-96',49,1,1,1,('2018/11/25 '),'HOUVE INCIDENTE');
Insert into Viagem (num_viagem,cod_armazem,matricula_veiculo,cod_funcionario,id_guia_transporte,num_etapas,id_nota_encomenda,data_partida,notificacao)
values(6,1,'48-AH-96',34,2,2,2,('2018/05/21 '),'NAO HOUVE INCIDENTE');
 

Insert into Guia_Saida (id_zonas,id_nota_encomenda) values(4,1);
Insert into Guia_Saida (id_zonas,id_nota_encomenda) values(1,2);

COMMIT;