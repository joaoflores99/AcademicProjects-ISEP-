SET SERVEROUTPUT ON;

-- Exercício 4 --
DECLARE
    l_qtd_livros    INTEGER;
    l_id_editora    editoras.id_editora%TYPE;
    l_contador      INTEGER;
    
BEGIN
l_id_editora := 1500;
    SELECT COUNT(*) 
    INTO l_contador FROM editoras WHERE id_editora = l_id_editora;
    
        if (l_contador!=0) then
            select count(*) into l_qtd_livros
            from edicoes_livros where id_editora = l_id_editora;
            
            DBMS_OUTPUT.PUT_LINE('Quantidade de livros: ' || l_qtd_livros);
        ELSE
            DBMS_OUTPUT.PUT_LINE('Editora ' || l_id_editora || ' inexistente.');
        END IF;
END;
/
-- Exercício 5 --
DECLARE
    l_isbn          edicoes_livros.isbn%TYPE;
    l_stock_min     edicoes_livros.stock_min%TYPE;
    l_stock         edicoes_livros.stock%TYPE;

BEGIN
    l_isbn := '500-1211111191';
    
    SELECT stock_min, stock into l_stock_min, l_stock from Edicoes_Livros el where el.isbn = l_isbn;
    DBMS_OUTPUT.PUT_LINE('Stock mínimo: ' || l_stock_min);
    DBMS_OUTPUT.PUT_LINE('Stock atual: ' || l_stock);

EXCEPTION
    WHEN NO_DATA_FOUND THEN 
    DBMS_OUTPUT.PUT_LINE ('Livro não existe!');

END;
/

-- Exercício 7 --
DECLARE
    CURSOR cur_autores
    is SELECT * FROM Autores ORDER BY nome;
    l_autor autores%ROWTYPE;
    l_linha INTEGER;
    
BEGIN
    DBMS_OUTPUT.PUT_LINE(RPAD('-',4) || RPAD('ID',6) || 'NOME');
    DBMS_OUTPUT.PUT_LINE(RPAD('-',35,'-'));

    FOR l_autor IN cur_autores
    LOOP
        l_linha := cur_autores%ROWCOUNT;
        DBMS_OUTPUT.PUT_LINE(RPAD(l_linha,4) || RPAD(l_autor.id_autor,6) || l_autor.nome);
    END LOOP;
END;
/
-- Exercício 6 --
DECLARE
    CURSOR cur_autores
    is SELECT * FROM Autores ORDER BY nome;
    l_autor autores%ROWTYPE;
    l_linha INTEGER;
    
BEGIN
    DBMS_OUTPUT.PUT_LINE(RPAD('-',4) || RPAD('ID',6) || 'NOME');
    DBMS_OUTPUT.PUT_LINE(RPAD('-',35,'-'));

    OPEN cur_autores;
    LOOP
        FETCH cur_autores INTO l_autor;
        EXIT WHEN cur_autores%NOTFOUND;
        
        l_linha := cur_autores%ROWCOUNT;
        
        DBMS_OUTPUT.PUT_LINE(RPAD(l_linha,4) || RPAD(l_autor.id_autor,6) || l_autor.nome);
    END LOOP;
    CLOSE cur_autores;
END;
/

-- Exercício 8 --
DECLARE
    l_linha INTEGER := 0;
    
BEGIN
    DBMS_OUTPUT.PUT_LINE(RPAD('-',4) || RPAD('ID',6) || 'NOME');
    DBMS_OUTPUT.PUT_LINE(RPAD('-',35,'-'));

    FOR id_autor IN (SELECT * FROM Autores ORDER BY nome)
    LOOP
        l_linha := l_linha  +1;
        DBMS_OUTPUT.PUT_LINE(RPAD(l_autor.l_linha,4) || RPAD(l_autor.id_autor,6) || l_autor.nome);
    END LOOP;
END;

/
-- Exercício 9 --
DECLARE
    CURSOR cur_qtd_vendida(
        p_id_editora        edicoes_livros.id_editora%TYPE,
        p_ano               INTEGER,
        p_mes               INTEGER)
        IS
            SELECT SUM(V.quantidade) FROM vendas v INNER JOIN edicoes_livros E ON V.isbn = E.isbn
            WHERE E.id_editora = p_id_editora
                AND EXTRACT (MONTH FROM V.data_hora) = p_mes
                AND EXTRACT (YEAR FROM V.data_hora) = p_ano;
                
    l_nome                  editoras.nome%TYPE;
    l_id_editora            edicoes_livros.id_editora%TYPE;
    l_ano                   INTEGER;
    l_qtd_vendida           INTEGER;
    l_ano_invalido          EXCEPTION;
    
BEGIN
    l_id_editora := 1500;
    l_ano := 2016;
    
    If l_ano >= EXTRACT (YEAR FROM SYSDATE) THEN
        RAISE l_ano_invalido;
    END IF;
    
    DBMS_OUTPUT.put_line('Editora ' || l_id_editora);
    DBMS_OUTPUT.put_line('Vendas ' || l_ano);
    DBMS_OUTPUT.put_line(' ');
    DBMS_OUTPUT.put_line(RPAD('Mês ',5) || 'Qtd');
    DBMS_OUTPUT.put_line(RPAD('-',10,'-'));
    
    FOR mes IN 1..12
    LOOP
        OPEN cur_qtd_vendida(l_id_editora, l_ano, mes);
        FETCH cur_qtd_vendida INTO l_qtd_vendida;
        CLOSE cur_qtd_vendida;
        
        DBMS_OUTPUT.PUT_LINE(RPAD(mes,5) || NVL(l_qtd_vendida,0));
    END LOOP;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN 
        DBMS_OUTPUT.PUT_LINE ('Editora não existe.');
    WHEN l_ano_invalido THEN
            DBMS_OUTPUT.PUT_LINE ('Ano inválido');

END;
