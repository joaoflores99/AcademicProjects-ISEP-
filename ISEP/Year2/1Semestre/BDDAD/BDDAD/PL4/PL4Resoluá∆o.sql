-- 1) Mostrar, numa coluna, o título de cada CD e de cada uma das músicas;

SELECT titulo FROM cd
UNION ALL
SELECT titulo FROM musicas;

-- 2) Copiar e alterar o comando da alínea anterior, de modo a não mostrar os registos repetidos;

SELECT titulo FROM cd
UNION 
SELECT titulo FROM musicas;

/* 3) Copiar e alterar o comando da alínea anterior, de modo a apresentar também o comprimento de
cada título e por ordem decrescente; */

SELECT titulo, LENGTH(titulo) FROM cd
UNION 
SELECT titulo, LENGTH(titulo) FROM musicas
ORDER BY titulo DESC;

-- 4) Mostrar a duração das músicas dos Pink Floyd que são iguais à duração de músicas de outros intérpretes;

-- SELECT duracao FROM musicas WHERE LENGTH(duracao LIKE 'pink floyd');
SELECT duracao FROM musicas WHERE LOWER(interprete) LIKE 'pink floyd' INTERSECT SELECT duracao FROM musicas WHERE LOWER(interprete)!='pink floyd'; 

-- 5) Alterar o comando da alínea anterior, de modo a mostrar a duração das músicas por ordem decrescente;

SELECT duracao FROM musicas WHERE LOWER(interprete) LIKE 'pink floyd' INTERSECT SELECT duracao FROM musicas WHERE LOWER(interprete)!='pink floyd' ORDER BY duracao DESC;

-- 6) Mostrar o id das editoras que não estão relacionadas com qualquer CD;

SELECT id_editora FROM editoras MINUS SELECT id_editora FROM cd;

-- 7) Alterar o comando da alínea anterior, de modo a mostrar o resultado por ordem decrescente.

SELECT id_editora FROM editoras MINUS SELECT id_editora FROM cd ORDER BY id_editora DESC;

-- 1) Mostrar apenas a quantidade de CD comprados por local de compra;

SELECT COUNT(*) FROM cd GROUP BY local_compra;

-- 2) Alterar o comando da alínea anterior, de forma a não mostrar registos duplicados;

SELECT DISTINCT COUNT(*) FROM cd GROUP BY local_compra;

-- 3) Mostrar a quantidade de CD comprados por local de compra e o respetivo local de compra;

SELECT local_compra, COUNT(*) FROM cd GROUP BY local_compra ;

-- 4) Copiar e alterar o comando da alínea anterior, de forma a mostrar o resultado por ordem crescente da quantidade de CD comprados;

SELECT COUNT(*), local_compra FROM cd GROUP BY local_compra ORDER BY 1 ASC;

-- 5) Copiar e alterar o comando da alínea anterior, de forma a mostrar os registos com locais de compra conhecidos;

SELECT COUNT(*), local_compra FROM cd WHERE local_compra IS NOT NULL GROUP BY local_compra ORDER BY 1 ASC;

-- 6) Copiar e alterar o comando da alínea anterior, de forma a mostrar também, para cada local de compra, o valor total pago e o maior valor pago;

SELECT COUNT(*), local_compra, SUM(valor_pago) AS "Valor Total Pago", MAX(valor_pago) FROM cd WHERE local_compra IS NOT NULL GROUP BY local_compra  ORDER BY 1 ASC;

/* 7) Mostrar, para cada CD e respetivos intérpretes, a quantidade de músicas do CD em que o intérprete participa. 
Além da quantidade referida, também deve ser apresentado o código do CD e o intérprete;*/

SELECT cod_cd, interprete, COUNT(*)
FROM musicas
GROUP BY cod_cd, interprete
ORDER BY cod_cd;

-- 8) Copiar e alterar o comando da alínea anterior, de modo a mostrar apenas, o código do CD e o intérprete;

SELECT cod_cd, interprete
FROM musicas
GROUP BY cod_cd, interprete
ORDER BY cod_cd;

-- 9) Copiar e alterar o comando da alínea anterior, de modo a mostrar apenas o intérprete;

SELECT interprete
FROM musicas
GROUP BY cod_cd, interprete
ORDER BY cod_cd;

-- 10) Mostrar a quantidade de CD comprados em cada local de compra;

SELECT local_compra, COUNT(*) FROM cd GROUP BY local_compra;

-- 11) Alterar o comando da alínea anterior, de modo a mostrar apenas as quantidades superiores a 2;

SELECT local_compra, COUNT(*) FROM cd GROUP BY local_compra HAVING COUNT(*) > 2;

-- 12) Mostrar os locais de compra, cujo média do valor pago por CD é inferior a 10, juntamente com o respetivo total do valor pago.

SELECT local_compra, SUM(valor_pago)
FROM cd
GROUP BY local_compra
HAVING AVG(valor_pago)<10;

-- 13) Mostrar o valor total pago nos locais de compra, cuja quantidade de CD comprados é inferior a 2. O local de compra também deve ser visualizado;

SELECT local_compra, SUM(valor_pago) FROM cd GROUP BY local_compra HAVING COUNT(*)<2;

/* 14) Mostrar o intérprete e o código do CD em que o intérprete participa apenas em 1 música.
O resultado deve ser apresentado por ordem crescente do código do CD e, em caso de igualdade, por ordem alfabética do intérprete;*/

SELECT cod_cd, interprete FROM musicas GROUP BY cod_cd, interprete HAVING COUNT(*)=1 ORDER BY cod_cd, interprete;

-- 15) Copiar e alterar o comando da alínea anterior, de modo a mostrar apenas os intérpretes e sem duplicados;

SELECT DISTINCT interprete FROM musicas GROUP BY cod_cd, interprete HAVING COUNT(*)=1 ORDER BY interprete;

-- 16) Copiar e alterar o comando da alínea anterior, de modo a mostrar apenas os intérpretes começados por E ou L (letras maiúsculas ou minúsculas);

SELECT DISTINCT interprete FROM musicas WHERE REGEXP_LIKE(interprete, '^[EL]', 'i') GROUP BY cod_cd, interprete HAVING COUNT(*)=1 ORDER BY interprete;

-- 17) Copiar e alterar o comando da alínea 13 para não mostrar os locais de compra desconhecidos;

SELECT local_compra, SUM(valor_pago) FROM cd WHERE local_compra IS NOT NULL GROUP BY local_compra HAVING COUNT(*)<2;

-- 18) Mostrar, para cada CD, o título e a quantidade de músicas;

SELECT C.titulo, COUNT(*) FROM cd C, musicas M WHERE C.cod_cd=M.cod_cd GROUP BY C.titulo ;

-- 19) Mostrar, para cada CD, o código, o título e a quantidade de músicas;

SELECT C.cod_cd, C.titulo, COUNT(*) FROM cd C, musicas M WHERE C.cod_cd=M.cod_cd GROUP BY C.cod_Cd, C.titulo ;

-- 20) Mostrar, para cada CD, o código, o título e a quantidade de músicas cuja duração é superior a 5;

SELECT C.cod_cd, C.titulo, COUNT(*) FROM cd C, musicas M WHERE C.cod_cd=M.cod_cd GROUP BY C.cod_Cd, C.titulo HAVING M.duracao>5;

-- 21) Mostrar, para cada CD com menos de 6 músicas, o código, o título e a quantidade de músicas do CD;

SELECT C.cod_cd, C.titulo, COUNT(*) FROM cd C, musicas M WHERE C.cod_cd=M.cod_cd GROUP BY C.cod_Cd, C.titulo HAVING COUNT(*)<6;

-- 22) Mostrar, para cada CD cujas músicas têm uma duração média superior a 4, o código, o título e a quantidade de músicas do CD.

SELECT C.cod_cd, C.titulo, COUNT(*) FROM cd C, musicas M WHERE C.cod_cd=M.cod_cd GROUP BY C.cod_Cd, C.titulo HAVING AVG(M.duracao)>4;
