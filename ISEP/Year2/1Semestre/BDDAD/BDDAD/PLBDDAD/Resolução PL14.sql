SET SERVEROUTPUT ON;
-- Exercício 5 --
create or replace function func_stock_max
return edicoes_livros.stock%TYPE
IS
    l_stock_max     edicoes_livros.stock%TYPE;
BEGIN
    SELECT MAX(stock) INTO l_stock_max
    FROM edicoes_livros;
    
    RETURN l_stock_max;
END;
/
-- Exercício 6 --
create or replace procedure proc_titulos_edicoes_stock_max
IS
    CURSOR cur_titulos_edicoes_stock_max
    IS
        SELECT DISTINCT L.titulo
        from edicoes_livros e inner join Livros l ON l.id_livro = e.id_livro where e.stock=func_stock_max;
        
    l_titulo        VARCHAR(100);
    l_linha         INTEGER;
    l_cabecalho     VARCHAR(50);
    l_stock_zero    EXCEPTION;

BEGIN
    if func_stock_max = 0 then
        RAISE l_stock_zero;
    END if;
    
    l_cabecalho := 'Títulos com o maior stock (' ||func_stock_max || ')';
    DBMS_OUTPUT.PUT_LINE(l_cabecalho);
    DBMS_OUTPUT.PUT_LINE(RPAD('-',LENGTH(l_cabecalho),'-'));
    FOR l_titulo IN cur_titulos_edicoes_stock_max
    LOOP
        l_linha := cur_titulos_edicoes_stock_max%ROWCOUNT;
        DBMS_OUTPUT.PUT_LINE(RPAD(l_linha,'4') || l_titulo.titulo);
    END LOOP;
    
EXCEPTION
    WHEN l_stock_zero THEN
        DBMS_OUTPUT.PUT_LINE('Não existem livros em stock.');
END;
/
-- Exercício 7 --
create or replace function func_tem_livros_editora(p_id_editora edicoes_livros.id_editora%TYPE)
return boolean
IS
    l_contador      INTEGER;
BEGIN
    select sum(stock) into l_contador from edicoes_livros
        where p_id_editora = id_editora;
    return (l_contador > 0);
END;
/

-- Exercício 7 TESTE --
DECLARE
  P_ID_EDITORA NUMBER;
  v_Return BOOLEAN;
BEGIN
  P_ID_EDITORA := 1500;

  v_Return := FUNC_TEM_LIVROS_EDITORA(P_ID_EDITORA);

    IF (v_Return) THEN 
    DBMS_OUTPUT.PUT_LINE('v_Return = ' || 'TRUE');
  ELSE
    DBMS_OUTPUT.PUT_LINE('v_Return = ' || 'FALSE');
  END IF;

  --:v_Return := v_Return;
rollback; 
END;

/

-- Exercício 8 --
create or replace function func_stock_ano_editora
(p_id_editora edicoes_livros.id_editora%TYPE, p_ano_edicao edicoes_livros.ano_edicao%TYPE DEFAULT extract (YEAR from sysdate))
return integer
IS
    l_contador      INTEGER;
    l_parametro_invalido    EXCEPTION;
begin
    if not func_tem_livros_editora(p_id_editora) OR p_ano_edicao > extract (YEAR from sysdate) then
        raise l_parametro_invalido;
    END IF;
    
    SELECT sum(stock) into l_contador FROM edicoes_livros 
        WHERE p_ano_edicao = ano_edicao
        AND p_id_editora = id_editora;
    
    return l_contador;
    
exception
    WHEN l_parametro_invalido THEN
        DBMS_OUTPUT.PUT_LINE('Um ou mais parâmetros são inválidos.');
        return null;
END;
/

-- Exercício 8 TESTE 1 (retorna 5, tudo válido)--
DECLARE
  P_ID_EDITORA NUMBER;
  P_ANO_EDICAO NUMBER;
  v_Return NUMBER;
BEGIN
  P_ID_EDITORA := 1500;
  P_ANO_EDICAO := 2010;

  v_Return := FUNC_STOCK_ANO_EDITORA(
    P_ID_EDITORA => P_ID_EDITORA,
    P_ANO_EDICAO => P_ANO_EDICAO
  );
--   Legacy output: 
DBMS_OUTPUT.PUT_LINE('v_Return = ' || v_Return);
 
  :v_Return := v_Return;
--rollback; 
END;
/

-- Exercício 8 TESTE 2 (retorna 40, sem ano)--
DECLARE
  P_ID_EDITORA NUMBER;
  P_ANO_EDICAO NUMBER;
  v_Return NUMBER;
BEGIN
  P_ID_EDITORA := 1500;

  v_Return := FUNC_STOCK_ANO_EDITORA(
    P_ID_EDITORA => P_ID_EDITORA
  );
--   Legacy output: 
DBMS_OUTPUT.PUT_LINE('v_Return = ' || v_Return);
 
  :v_Return := v_Return;
--rollback; 
END;
/

-- Exercício 8 TESTE 3 (retorna null, ano inválido)--
DECLARE
  P_ID_EDITORA NUMBER;
  P_ANO_EDICAO NUMBER;
  v_Return NUMBER;
BEGIN
  P_ID_EDITORA := 1500;
  P_ANO_EDICAO := 2019;

  v_Return := FUNC_STOCK_ANO_EDITORA(
    P_ID_EDITORA => P_ID_EDITORA,
    P_ANO_EDICAO => P_ANO_EDICAO
  );
--   Legacy output: 
DBMS_OUTPUT.PUT_LINE('v_Return = ' || v_Return);
 
  :v_Return := v_Return;
--rollback; 
END;
/

-- Exercício 8 TESTE 4 (retorna null, editora inválido)--
DECLARE
  P_ID_EDITORA NUMBER;
  P_ANO_EDICAO NUMBER;
  v_Return NUMBER;
BEGIN
  P_ID_EDITORA := null;
  P_ANO_EDICAO := 2015;

  v_Return := FUNC_STOCK_ANO_EDITORA(
    P_ID_EDITORA => P_ID_EDITORA,
    P_ANO_EDICAO => P_ANO_EDICAO
  );
--   Legacy output: 
DBMS_OUTPUT.PUT_LINE('v_Return = ' || v_Return);
 
  :v_Return := v_Return;
--rollback; 
END;
/

-- Exercício 9 --
create or replace function func_titulos_ano
(p_ano_edicao edicoes_livros.ano_edicao%TYPE)
return sys_refcursor
is
    cur_livros_titulo   sys_refcursor;
begin
    if (p_ano_edicao > extract (year from sysdate)) then
        return null;
    end if;
    OPEN cur_livros_titulo FOR
        select distinct titulo 
        from livros l inner join edicoes_livros el 
            on l.id_livro = el.id_livro 
        where p_ano_edicao = el.ano_edicao;
    return cur_livros_titulo;
end;

/

-- Exercício 10 --
create or replace procedure proc_titulos
(p_cur_titulo sys_refcursor)
is
    l_titulo CHAR(150);
begin
    if p_cur_titulo is null then
        -- fazer exceção  
    end if;
    loop
        fetch p_cur_titulo into l_titulo;
        exit when p_cur_titulo%NOTFOUND;
        DBMS_OUTPUT.PUT_LINe(l_titulo);
    end loop;
end;
/