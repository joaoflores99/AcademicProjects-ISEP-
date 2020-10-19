
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
INSERT INTO marinheiros VALUES(64, 'Hor�cio', 7, 35);
INSERT INTO marinheiros VALUES(71, 'Zorba',  10, 16);
INSERT INTO marinheiros VALUES(74, 'Hor�cio', 9, 35);
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

Select id_marinheiro, nome, idade from Marinheiros 
where idade= (Select MAX(idade) from Marinheiros);

--A1
Select M1.nome,M1.idade,M1.id_Marinheiro from Marinheiros M1 where M1.idade=(Select MAX(M2.idade) from (Marinheiros M2)); 
--A2
Select id_marinheiro, nome from marinheiros where id_marinheiro NOT in (Select id_marinheiro from reservas);

--A3
Select M1.id_marinheiro,M1.nome, (SELECT TRUNC (AVG(M2.idade-M1.idade)) from Marinheiros M2) AS Diferenca FROM Marinheiros M1 order by ABS(Diferenca) DESC;


--4?
select M.id_marinheiro, m.Nome from Marinheiros m, reservas r, barcos b where m.id_marinheiro =r.id_marinheiro and r.id_barco=b.id_barco and upper(B.cor) = 'VERMELHO';
--A4

SELECT COUNT (*) 
FROM (SELECT R.id_marinheiro FROM reservas R 
INNER JOIN barcos B ON R.id_barco = B.id_barco 
WHERE UPPER (B.cor) LIKE 'VERMELHO' 
INTERSECT SELECT R.id_marinheiro FROM Reservas R 
INNER JOIN barcos B ON R.id_barco = B.id_barco WHERE UPPER (B.cor) LIKE 'VERDE');


--A5
select R.data,count(*) AS MAXRESERVAS from  reservas R group By R.data having COunt(*)= (select MAX(count (*)) From  Reservas R Group By R.data );

--A6
Select M1.nome,M1.idade,M1.id_Marinheiro from Marinheiros M1 where M1.idade>= ALL (Select MAX(M2.idade) from (Marinheiros M2)); 

--A7
Select M.nome, M.idade, M.id_Marinheiro from Marinheiros M where M.idade<ANY (Select M.idade From Marinheiros M) order by M.idade desc; 

--B1

Select  M.id_marinheiro, M.nome, (SELECT count(*) from Reservas R Where R.id_marinheiro = M.id_marinheiro)
AS NR_ToTal_RESERVAS 
From MArinheiros M
ORder by NR_Total_RESERVAS DESC;

--B2
Select R.id_marinheiro, R.id_barco, count(*) AS NR_TOTAL_RESERVAS from Reservas R
Group by r.id_marinheiro, r.id_barco
Having count (*) > (select  AVG(count (*)) From Reservas R2 where R.Id_barco = R2.id_barco group by r2.id_marinheiro);

--B3

select M.nome From Marinheiros M
Where not Exists (select B.id_barco from barcos B where Upper(b.nome)like 'INTERLAKE' Minus Select B.id_barco from barcos B inner join reservas R on B.id_barco=R.id_barco Where UPPER(B.nome) like 'INTERLAKE' AND r.id_marinheiro=M.id_marinheiro);

Commit;