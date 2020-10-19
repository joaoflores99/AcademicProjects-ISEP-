-- 1) Mostrar, numa coluna, o t�tulo de cada CD e de cada uma das m�sicas;

SELECT titulo FROM cd
UNION ALL
SELECT titulo FROM musicas;

-- 2) Copiar e alterar o comando da al�nea anterior, de modo a n�o mostrar os registos repetidos;

SELECT titulo FROM cd
UNION 
SELECT titulo FROM musicas;

/* 3) Copiar e alterar o comando da al�nea anterior, de modo a apresentar tamb�m o comprimento de
cada t�tulo e por ordem decrescente; */

SELECT titulo, LENGTH(titulo) FROM cd
UNION 
SELECT titulo, LENGTH(titulo) FROM musicas
ORDER BY titulo DESC;

-- 4) Mostrar a dura��o das m�sicas dos Pink Floyd que s�o iguais � dura��o de m�sicas de outros int�rpretes;

-- SELECT duracao FROM musicas WHERE LENGTH(duracao LIKE 'pink floyd');
SELECT duracao FROM musicas WHERE LOWER(interprete) LIKE 'pink floyd' INTERSECT SELECT duracao FROM musicas WHERE LOWER(interprete)!='pink floyd'; 

-- 5) Alterar o comando da al�nea anterior, de modo a mostrar a dura��o das m�sicas por ordem decrescente;

SELECT duracao FROM musicas WHERE LOWER(interprete) LIKE 'pink floyd' INTERSECT SELECT duracao FROM musicas WHERE LOWER(interprete)!='pink floyd' ORDER BY duracao DESC;

-- 6) Mostrar o id das editoras que n�o est�o relacionadas com qualquer CD;

SELECT id_editora FROM editoras MINUS SELECT id_editora FROM cd;

-- 7) Alterar o comando da al�nea anterior, de modo a mostrar o resultado por ordem decrescente.

SELECT id_editora FROM editoras MINUS SELECT id_editora FROM cd ORDER BY id_editora DESC;

-- 1) Mostrar apenas a quantidade de CD comprados por local de compra;

SELECT COUNT(*) FROM cd GROUP BY local_compra;

-- 2) Alterar o comando da al�nea anterior, de forma a n�o mostrar registos duplicados;

SELECT DISTINCT COUNT(*) FROM cd GROUP BY local_compra;

-- 3) Mostrar a quantidade de CD comprados por local de compra e o respetivo local de compra;

SELECT local_compra, COUNT(*) FROM cd GROUP BY local_compra ;

-- 4) Copiar e alterar o comando da al�nea anterior, de forma a mostrar o resultado por ordem crescente da quantidade de CD comprados;

SELECT COUNT(*), local_compra FROM cd GROUP BY local_compra ORDER BY 1 ASC;

-- 5) Copiar e alterar o comando da al�nea anterior, de forma a mostrar os registos com locais de compra conhecidos;

SELECT COUNT(*), local_compra FROM cd WHERE local_compra IS NOT NULL GROUP BY local_compra ORDER BY 1 ASC;

-- 6) Copiar e alterar o comando da al�nea anterior, de forma a mostrar tamb�m, para cada local de compra, o valor total pago e o maior valor pago;

SELECT COUNT(*), local_compra, SUM(valor_pago) AS "Valor Total Pago", MAX(valor_pago) FROM cd WHERE local_compra IS NOT NULL GROUP BY local_compra  ORDER BY 1 ASC;

/* 7) Mostrar, para cada CD e respetivos int�rpretes, a quantidade de m�sicas do CD em que o int�rprete participa. 
Al�m da quantidade referida, tamb�m deve ser apresentado o c�digo do CD e o int�rprete;*/

SELECT cod_cd, interprete, COUNT(*)
FROM musicas
GROUP BY cod_cd, interprete
ORDER BY cod_cd;

-- 8) Copiar e alterar o comando da al�nea anterior, de modo a mostrar apenas, o c�digo do CD e o int�rprete;

SELECT cod_cd, interprete
FROM musicas
GROUP BY cod_cd, interprete
ORDER BY cod_cd;

-- 9) Copiar e alterar o comando da al�nea anterior, de modo a mostrar apenas o int�rprete;

SELECT interprete
FROM musicas
GROUP BY cod_cd, interprete
ORDER BY cod_cd;

-- 10) Mostrar a quantidade de CD comprados em cada local de compra;

SELECT local_compra, COUNT(*) FROM cd GROUP BY local_compra;

-- 11) Alterar o comando da al�nea anterior, de modo a mostrar apenas as quantidades superiores a 2;

SELECT local_compra, COUNT(*) FROM cd GROUP BY local_compra HAVING COUNT(*) > 2;

-- 12) Mostrar os locais de compra, cujo m�dia do valor pago por CD � inferior a 10, juntamente com o respetivo total do valor pago.

SELECT local_compra, SUM(valor_pago)
FROM cd
GROUP BY local_compra
HAVING AVG(valor_pago)<10;

-- 13) Mostrar o valor total pago nos locais de compra, cuja quantidade de CD comprados � inferior a 2. O local de compra tamb�m deve ser visualizado;

SELECT local_compra, SUM(valor_pago) FROM cd GROUP BY local_compra HAVING COUNT(*)<2;

/* 14) Mostrar o int�rprete e o c�digo do CD em que o int�rprete participa apenas em 1 m�sica.
O resultado deve ser apresentado por ordem crescente do c�digo do CD e, em caso de igualdade, por ordem alfab�tica do int�rprete;*/

SELECT cod_cd, interprete FROM musicas GROUP BY cod_cd, interprete HAVING COUNT(*)=1 ORDER BY cod_cd, interprete;

-- 15) Copiar e alterar o comando da al�nea anterior, de modo a mostrar apenas os int�rpretes e sem duplicados;

SELECT DISTINCT interprete FROM musicas GROUP BY cod_cd, interprete HAVING COUNT(*)=1 ORDER BY interprete;

-- 16) Copiar e alterar o comando da al�nea anterior, de modo a mostrar apenas os int�rpretes come�ados por E ou L (letras mai�sculas ou min�sculas);

SELECT DISTINCT interprete FROM musicas WHERE REGEXP_LIKE(interprete, '^[EL]', 'i') GROUP BY cod_cd, interprete HAVING COUNT(*)=1 ORDER BY interprete;

-- 17) Copiar e alterar o comando da al�nea 13 para n�o mostrar os locais de compra desconhecidos;

SELECT local_compra, SUM(valor_pago) FROM cd WHERE local_compra IS NOT NULL GROUP BY local_compra HAVING COUNT(*)<2;

-- 18) Mostrar, para cada CD, o t�tulo e a quantidade de m�sicas;

SELECT C.titulo, COUNT(*) FROM cd C, musicas M WHERE C.cod_cd=M.cod_cd GROUP BY C.titulo ;

-- 19) Mostrar, para cada CD, o c�digo, o t�tulo e a quantidade de m�sicas;

SELECT C.cod_cd, C.titulo, COUNT(*) FROM cd C, musicas M WHERE C.cod_cd=M.cod_cd GROUP BY C.cod_Cd, C.titulo ;

-- 20) Mostrar, para cada CD, o c�digo, o t�tulo e a quantidade de m�sicas cuja dura��o � superior a 5;

SELECT C.cod_cd, C.titulo, COUNT(*) FROM cd C, musicas M WHERE C.cod_cd=M.cod_cd GROUP BY C.cod_Cd, C.titulo HAVING M.duracao>5;

-- 21) Mostrar, para cada CD com menos de 6 m�sicas, o c�digo, o t�tulo e a quantidade de m�sicas do CD;

SELECT C.cod_cd, C.titulo, COUNT(*) FROM cd C, musicas M WHERE C.cod_cd=M.cod_cd GROUP BY C.cod_Cd, C.titulo HAVING COUNT(*)<6;

-- 22) Mostrar, para cada CD cujas m�sicas t�m uma dura��o m�dia superior a 4, o c�digo, o t�tulo e a quantidade de m�sicas do CD.

SELECT C.cod_cd, C.titulo, COUNT(*) FROM cd C, musicas M WHERE C.cod_cd=M.cod_cd GROUP BY C.cod_Cd, C.titulo HAVING AVG(M.duracao)>4;
