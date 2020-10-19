------------------------- PL6 -------------------------
------------ Exercícios Obrigatórios ------------
-- 1 --
SELECT m.nome AS "Nome", NVL((
    SELECT mc.nome FROM Medicos mc WHERE m.id_medico_chefe = mc.id_medico), ' ') AS "Chefe" 
FROM Medicos m 
ORDER BY m.nome;


















-- 2 -- WITH
WITH consultas_especialidades AS
    (SELECT e.id_especialidade,
        e.designacao, 
        TO_CHAR(c.data_hora,'DD-MM-YY') AS data ,
        COUNT(*) AS nr_consultas
    FROM consultas c 
        INNER JOIN medicos m ON m.id_medico = c.id_medico 
        INNER JOIN especialidades e ON m.id_especialidade = e.id_especialidade 
    GROUP BY e.id_especialidade, e.designacao, TO_CHAR(c.data_hora,'DD-MM-YY')
    )
SELECT CE1.id_especialidade,
        CE1.designacao,
        CE1.data
FROM consultas_especialidades CE1
WHERE nr_consultas = (SELECT MAX(CE2.nr_consultas)
                        FROM consultas_especialidades CE2
                        WHERE CE2.id_especialidade = CE1.id_especialidade)
ORDER BY 2,3;

-- 3 --
SELECT DISTINCT p.nome 
FROM Consultas I1 
    INNER JOIN Pacientes p ON p.id_paciente = i1.id_paciente
WHERE NOT EXISTS (
    SELECT * 
    FROM Medicos M
    WHERE NOT EXISTS ( 
        SELECT * 
        FROM Consultas I2
        WHERE i2.id_paciente = i1.id_paciente 
            AND I2.id_medico = M.id_medico)
            AND UPPER(M.HOSPITAL) LIKE 'PEDRO HISPANO');
            
-- 4 --
SELECT  m.nome,  
        TO_CHAR(m.data_nascimento,'DD-MM-YYYY') AS "Data de Nascimento", 
        (TRUNC (months_between(SYSDATE, m.data_nascimento)/12))AS "IDADE"
FROM Medicos m 
WHERE TRUNC(MONTHS_BETWEEN(SYSDATE, m.data_nascimento)/12) 
    IN (SELECT DISTINCT TRUNC(MONTHS_BETWEEN(SYSDATE, m2.data_nascimento)/12) 
        FROM medicos m2
        ORDER BY 1  
        FETCH FIRST 3 ROWS ONLY);
        
-- 5 --
SELECT m.nome, 
        NVL(TO_CHAR((SELECT MAX(c.data_hora)
                    FROM CONSULTAS c 
                    WHERE c.id_medico = m.id_medico),'DD-MM-YYY'),' ')
            AS data_ultima_consulta        
        ,NVL(TO_CHAR(EXTRACT(DAY FROM SYSTIMESTAMP - (SELECT MAX(C.data_hora)
                                                    FROM consultas C
                                                    where C.id_medico = m.id_medico))), ' ')
            as nr_dias_sem_consulta                                        
        ,NVL2((SELECT MAX(C.data_hora)
                FROM consultas C
                WHERE C.id_medico = M.id_medico), ' ','sem consultas')
                AS "OBS."
FROM Medicos m
ORDER BY 1;

-- 6 --
SELECT  TO_CHAR(C.data_hora, 'FMDay, DD Month "de" YYYY') AS data,
        TO_CHAR(C.data_hora, 'HH24:MI') AS hora,
        E.designacao AS especialidade,
        CASE TO_CHAR (C.data_hora, 'FMDAY')
            WHEN 'SÁBADO' THEN 'Fim de Semana'
            WHEN 'DOMINGO' THEN 'Fim de Semana'
            ELSE ' '
        END AS "OBS."
    FROM    (especialidades E
            INNER JOIN medicos M ON E.id_especialidade = M.id_especialidade)
            INNER JOIN consultas C on C.id_medico = m.id_medico
    WHERE c.data_hora < (SYSTIMESTAMP - INTERVAL '2' YEAR)
            AND TO_TIMESTAMP(TO_CHAR(C.data_hora,'HH24:mi'),'HH24:MI') >= TO_TIMESTAMP('12:30','HH24:MI')
    ORDER BY TO_TIMESTAMP(TO_CHAR(C.data_hora, 'DD_MM_YYYY'), 'DD-MM-YYYY') DESC,
            hora ASC;