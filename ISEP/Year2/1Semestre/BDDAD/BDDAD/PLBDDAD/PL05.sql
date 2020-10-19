------------- PL05 -------------
------- A -------
-- 01 --
SELECT m.id_marinheiro, m.IDADE, m.NOME FROM MARINHEIROS m WHERE m.idade = (SELECT MAX(IDADE) FROM MARINHEIROS);

-- 02 --
SELECT m.NOME, m.id_marinheiro FROM MARINHEIROS m WHERE m.id_marinheiro NOT IN (SELECT DISTINCT (id_marinheiro) FROM RESERVAS);

-- 03 --
SELECT m.NOME, m.id_marinheiro, TRUNC((SELECT AVG(IDADE) FROM MARINHEIROS) - idade, 0) AS "Diferença de Idades" FROM MARINHEIROS m  ORDER BY ABS(TRUNC((SELECT AVG(IDADE) FROM MARINHEIROS) - idade, 0)) DESC;

-- 04 --
SELECT COUNT(*) AS nr_total_marinheiros FROM (SELECT R1.id_marinheiro FROM barcos B1 INNER JOIN reservas R1 ON B1.id_barco = R1.id_barco WHERE UPPER(B1.cor) LIKE 'VERMELHO' INTERSECT SELECT R2.id_marinheiro FROM barcos B2 INNER JOIN reservas R2 ON B2.id_barco = r2.id_barco WHERE UPPER(B2.cor) LIKE 'VERDE');

-- 05 --
SELECT r.data FROM RESERVAS r GROUP BY r.data HAVING COUNT(*) = (SELECT MAX(COUNT(*)) FROM reservas r2 GROUP BY r2.data);

-- 06 --
SELECT m.id_marinheiro, m.IDADE, m.NOME FROM MARINHEIROS m WHERE m.idade >= ALL (SELECT IDADE FROM MARINHEIROS);

------- B -------
-- 01 --
SELECT m.id_marinheiro, m.nome, (SELECT COUNT(*) FROM RESERVAS r WHERE r.id_marinheiro = m.id_marinheiro) AS "CONTAGEM" FROM MARINHEIROS m ORDER BY "CONTAGEM" DESC;

-- 02 --
SELECT r.id_marinheiro, r.id_barco, count(*) as nr_total_reservas FROM RESERVAS r group by r.id_marinheiro, r.id_barco having count(*) > (select avg(count(*)) from reservas r2 where r2.id_barco = r.id_barco group by r2.id_marinheiro);

-- 03 --
SELECT m.nome FROM MARINHEIROS m WHERE 