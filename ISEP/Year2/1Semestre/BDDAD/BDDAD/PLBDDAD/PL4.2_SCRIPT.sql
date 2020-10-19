--  ##### PL4 #####
-- ## Exercício 5 ##

-- 1
SELECT COUNT(*) FROM CD c GROUP BY c.local_compra;

-- 2
SELECT DISTINCT(COUNT(*)) FROM CD c GROUP BY c.local_compra;

-- 3
SELECT c.local_compra, COUNT(*) FROM CD c GROUP BY c.local_compra;

-- 4
SELECT c.local_compra, COUNT(*) AS "CONTAGEM" FROM CD c GROUP BY c.local_compra ORDER BY CONTAGEM ASC;

-- 5
SELECT c.local_compra, COUNT(*) AS "CONTAGEM" FROM CD c WHERE c.local_compra IS NOT NULL GROUP BY c.local_compra ORDER BY CONTAGEM ASC;

-- 6
SELECT c.local_compra, COUNT(*) AS "CONTAGEM" , SUM(c.valor_pago) AS "Valor Pago", MAX(c.valor_pago) AS "Maior Valor Pago" FROM CD c WHERE c.local_compra IS NOT NULL GROUP BY c.local_compra ORDER BY CONTAGEM ASC;

-- 7
SELECT COD_CD, interprete, COUNT(*) FROM MUSICAS GROUP BY COD_CD, INTERPRETE ORDER BY COD_CD;

-- 8
SELECT COD_CD, interprete FROM MUSICAS GROUP BY COD_CD, INTERPRETE ORDER BY COD_CD;

-- 9
SELECT interprete FROM MUSICAS GROUP BY COD_CD, INTERPRETE ORDER BY COD_CD;

-- 10
SELECT COUNT(*) FROM CD c GROUP BY c.local_compra;

-- 11
SELECT COUNT(*) AS "CONTADOR" FROM CD c GROUP BY c.local_compra HAVING COUNT(*)> 2;

-- 12
SELECT local_compra, SUM(valor_pago) AS "VALOR PAGO" FROM CD GROUP BY local_compra HAVING AVG(valor_pago) < 10;

-- 13
SELECT SUM(valor_pago) AS "VALOR PAGO", local_compra AS "Local de Compra" FROM CD GROUP BY local_compra HAVING COUNT(*) < 2;

-- 14
SELECT cod_cd, interprete FROM musicas GROUP BY cod_cd, interprete HAVING COUNT(*) = 1 ORDER BY cod_cd, interprete;

-- 15
SELECT DISTINCT(interprete) FROM musicas GROUP BY cod_cd, interprete HAVING COUNT(*) = 1 ORDER BY interprete;

-- 16
SELECT DISTINCT(interprete) FROM musicas WHERE REGEXP_LIKE (interprete, '^[EL]', 'i')  GROUP BY cod_cd, interprete HAVING COUNT(*) = 1 ORDER BY interprete;

-- 17
SELECT SUM(valor_pago) AS "VALOR PAGO", local_compra AS "Local de Compra" FROM CD WHERE local_compra IS NOT NULL GROUP BY local_compra HAVING COUNT(*) < 2;

-- 18
SELECT c.titulo, COUNT(*) FROM CD c, MUSICAS m WHERE C.cod_cd = m.cod_cd GROUP BY c.titulo;

-- 19
SELECT c.titulo, COUNT(*), c.cod_cd FROM CD c, MUSICAS m WHERE C.cod_cd = m.cod_cd GROUP BY c.titulo, c.cod_cd;

-- 20