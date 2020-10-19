set serveroutput on;

-- Exercício 5 --
CREATE OR REPLACE TRIGGER trg_vendas_impedir_alteracoes
BEFORE INSERT OR UPDATE OR DELETE ON VENDAS
DECLARE
    l_hora_invalida     EXCEPTION;
    l_dia_invalido      EXCEPTION;
BEGIN
    IF (TO_CHAR(SYSDATE, 'DY') IN ('SÁB', 'DOM')) THEN
        RAISE l_dia_invalido;
    END IF;
    
    IF (TO_NUMBER(TO_CHAR(SYSDATE, 'HH24')) NOT BETWEEN 9 AND 19) THEN
        RAISE l_hora_invalida;
    END IF;
    
EXCEPTION
    WHEN l_hora_invalida then
        RAISE_APPLICATION_ERROR(-20000, 'Hora inválida!');
    WHEN l_dia_invalido THEN
        RAISE_APPLICATION_ERROR(-20001, 'Dia inválido!');
END;
/

-- Exercício 5 | Teste INSERT --
BEGIN
    INSERT INTO vendas (nr_venda, nif_cliente, isbn, data_hora, quantidade)
    VALUES(100, 900800100, '500-1234567891', SYSDATE, 2);
    ROLLBACK;    
END;
/

-- Exercício 6 --
ALTER TRIGGER trg_vendas_impedir_alteracoes DISABLE;
/

-- Exercício 7 --
ALTER TRIGGER trg_vendas_impedir_alteracoes DISABLE;
/

-- Exercício 8 --
CREATE OR REPLACE TRIGGER tgr_precos_edicoes_livros_impedir_registo
AFTER INSERT ON Precos_Edicoes_Livros
FOR EACH ROW WHEN (NEW.data_inicio <= SYSDATE)
BEGIN
    RAISE_APPLICATION_ERROR(-20000, 'Data inválida.');
END;
/

-- Exercício 8 | Teste Inválido -> Dia Atual --
BEGIN
    INSERT INTO Precos_Edicoes_Livros
    VALUES('500-1234567891', SYSDATE, 50.0);
    ROLLBACK;
END;    
/

-- Exercício 8 | Teste Inválido -> Dia Seguinte --
BEGIN
    INSERT INTO Precos_Edicoes_Livros
    VALUES('500-1234567891', SYSDATE - 1, 50.0);
    ROLLBACK;
END;    
/

-- Exercício 8 | Teste Válido --
BEGIN
    INSERT INTO Precos_Edicoes_Livros
    VALUES('500-1234567891', SYSDATE + 1, 50.0);
    ROLLBACK;
END;    
/

-- Exercício 9 --
CREATE OR REPLACE TRIGGER trg_vendas_saldos_cartao_cliente
AFTER INSERT OR DELETE ON Vendas
FOR EACH ROW WHEN (TO_CHAR(NEW.data_hora, 'DY') IN ('TER', 'QUA') 
    OR TO_CHAR(OLD.data_hora, 'DY') IN ('TER', 'QUA'))
DECLARE
    l_preco         precos_edicoes_livros.preco%TYPE;
    l_isbn          precos_edicoes_livros.isbn%TYPE;
    l_valor         precos_edicoes_livros.preco%TYPE;
    l_quantidade    vendas.quantidade%TYPE;
    l_data_hora     vendas.data_hora%TYPE;
    l_nif_cliente   clientes.nif_cliente&TYPE;
    l_qtd_cartoes   cartoes_clentes.nr_cartao%TYPE;
    
    l_percentagem_bonus     CONSTANT NUMERIC(3) := 5;
    
BEGIN
    -- /DEBUG/
    -- DBMS_OUTPUT.PUT_LINE('Iniciou ação');
    IF INSERTING THEN
        l_isbn := :NEW.isbn;
        l_nif_cliente := :NEW.nif_cliente;
        l_quantidade := :NEW.quantidade;
        l_data_hora := :NEW.data_hora;
    ELSE
        l_isbn := :OLD.isbn;
        l_nif_cliente := :OLD.nif_cliente;
        l_quantidade := :OLD.quantidade;
        l_data_hora := :OLD.data_hora;
    END IF;
    
    SELECT COUNT(*) INTO l_qtd_cartoes
    FROM cartoes_clientes WHERE nif_cliente = l_nif_cliente;
    
    IF l_qtd_cartoes > 0 THEN
        -- /DEBUG/
        -- DBMS_OUTPUT.PUT_LINE('Cliente tem cartão');
        SELECT preco INTO l_preco
END;
/