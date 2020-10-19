-- Exercício A
-- 1
SELECT * FROM Equipas;
-- 2
SELECT * FROM EQUIPAS WHERE id_equipa = 12;
-- 3
SELECT id_equipa, nome FROM EQUIPAS;
-- 4
SELECT id_treinador, idade, nome FROM TREINADORES WHERE idade < 40;
-- 5
SELECT * FROM EXPERIENCIAS WHERE escalao LIKE 'juniores' OR anos > 10;
-- 6
SELECT * FROM TREINADORES WHERE idade BETWEEN 45 AND 53 ORDER BY idade DESC;
-- 7
SELECT * FROM BOLAS WHERE fabricante LIKE 'Reebok' OR fabricante LIKE 'Olimpic';
-- 8
SELECT * FROM TREINADORES WHERE UPPER(nome) LIKE 'A%';

-- Exercício B
-- 1
SELECT COUNT(*) FROM EQUIPAS;
-- 2
SELECT COUNT(DISTINCT fabricante) FROM bolas;
-- 3
SELECT COUNT (id_treinador) FROM TREINADORES WHERE idade > 40;
-- 4
SELECT MAX(idade) FROM TREINADORES;

-- Exercício C
-- 1
SELECT id_equipa from BOLAS where UPPER(BOLAS.fabricante) = 'ADIDAS';
-- 2
SELECT DISTINCT(id_equipa) from BOLAS where UPPER(BOLAS.fabricante) = 'ADIDAS';
-- 3
SELECT AVG(IDADE) from TREINADORES, EXPERIENCIAS WHERE TREINADORES.id_treinador = EXPERIENCIAS.id_treinador AND UPPER(EXPERIENCIAS.escalao) LIKE 'JUVENIS';
-- 4
SELECT  t.* from TREINADORES t, EXPERIENCIAS e WHERE t.id_treinador = e.id_treinador AND UPPER(e.escalao) LIKE 'JUNIORES' AND e.anos >= 5;
-- 5
SELECT t.*, e.* from TREINADORES t, EQUIPAS e, EXPERIENCIAS ex WHERE t.id_treinador = ex.id_treinador AND ex.id_equipa = e.id_equipa;
-- 6
SELECT DISTINCT(t.nome) AS "Nome do Treinador", t.telefone AS "Contacto", e.nome AS "Nome da Equipa" from TREINADORES t, EQUIPAS e, EXPERIENCIAS ex WHERE t.id_treinador = ex.id_treinador AND ex.id_equipa = e.id_equipa;
-- 7
SELECT e.*, t.* FROM TREINADORES t, EQUIPAS e, EXPERIENCIAS ex WHERE t.id_treinador = ex.id_treinador AND ex.id_equipa = e.id_equipa AND UPPER(e.nome) LIKE 'ACADÉMICO';
-- VERIFICAR COMO FAZER COM O DISTINCT
-- 8 
SELECT MAX(t.idade) FROM TREINADORES t, EQUIPAS e, EXPERIENCIAS ex WHERE t.id_treinador = ex.id_treinador AND ex.id_equipa = e.id_equipa AND UPPER(e.nome) LIKE 'ACADÉMICO';
-- 9
SELECT DISTINCT(ex.anos) FROM EXPERIENCIAS ex, TREINADORES t, EQUIPAS e WHERE t.id_treinador = ex.id_treinador AND ex.id_equipa = e.id_equipa AND UPPER(e.nome) LIKE 'ACADÉMICO' AND UPPER(t.nome) = 'ANTÓNIO';