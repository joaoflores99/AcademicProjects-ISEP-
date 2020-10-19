--  ##### PL4 #####
-- ## Exercício 4 ##

-- 1
SELECT c.titulo FROM CD c UNION ALL SELECT m.titulo FROM MUSICAS m;

-- 2
SELECT c.titulo FROM CD c UNION SELECT m.titulo FROM MUSICAS m;

-- 3
SELECT c.titulo, LENGTH(c.titulo) AS "TAMANHO CD" FROM CD c UNION SELECT m.titulo, LENGTH(m.titulo) AS TAMANHO FROM MUSICAS m ORDER BY 2 DESC; 

-- 4
SELECT m.duracao FROM MUSICAS m WHERE UPPER(m.interprete) LIKE 'PINK FLOYD' INTERSECT SELECT m.duracao FROM MUSICAS m WHERE UPPER(m.interprete) != 'PINK FLOYD';

-- 5
SELECT m.duracao FROM MUSICAS m WHERE UPPER(m.interprete) LIKE 'PINK FLOYD' INTERSECT SELECT m.duracao FROM MUSICAS m WHERE UPPER(m.interprete) != 'PINK FLOYD' ORDER BY duracao DESC; 

-- 6
SELECT e.id_editora FROM EDITORAS e MINUS SELECT id_editora FROM CD;