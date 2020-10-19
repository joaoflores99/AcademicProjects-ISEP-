--Alinea 1 
--FUNÇÕES
 
-- Implementar uma função designada func_stock_artigo_armazem, 
-- para retornar a quantidade total de um artigo num determinado armazém. 
-- A função tem como parâmetros, o identificador do artigo e o identificador do armazém. 
-- No caso de o identificador do artigo ou do armazém não existir na BD, a função deve retornar o valor NULL.
SET SERVEROUTPUT ON;
create or replace function func_stock_artigo_armazem
(d_referencia integer, d_cod_armazem integer) 
return integer 
is
 stock_count integer;
begin
 select SUM(stock) into stock_count from Zona_Artigo 
 where referencia = d_referencia and cod_armazem = d_cod_armazem; 
    if  stock_count <= 0 OR stock_count = NULL then
        return NULL;
    else 
        return stock_count;
    end if;
   EXCEPTION 
     WHEN no_data_found then
      return null;
       
end;
/
-- test true 
Declare 
    result Integer;
   
 begin 
    result := func_stock_artigo_armazem(125,1);
    if(result is not null) then 
    dbms_output.put_line('quantidade ='|| result);
    else
    dbms_output.put_line('dados do armazem ou do artigo errados');
  end if;
  
END;
/

Declare 
    result Integer;
    
 begin 
    result := func_stock_artigo_armazem(4,3);
     if(result is not null) then 
    dbms_output.put_line('quantidade ='|| result);
    else
    dbms_output.put_line('dados do armazem ou do artigo errados');

  end if;
END;
/

--Alinea 2
/**
2. Implementar uma função designada func_razao_veiculo_transporte, para obter a razão entre o
número de veículos que efetuaram viagens com o maior número de encomendas e o número de
veículos que efetuaram viagens com o menor número de encomendas. O cálculo desta razão deve
ser feito para um dado armazém num dado intervalo de tempo. A função tem como parâmetros, o
identificador do armazém e o período de tempo (data início e data fim). No caso de o identificador
do armazém não existir na BD, a função deve retornar o valor NULL.
**/
SET SERVEROUTPUT ON;
create or replace function func_razao_veiculo_transporte(id INTEGER , dataInicio Viagem.data_partida%TYPE, dataFim Viagem.data_partida%TYPE)
    return Number Is
        razao int;
        ma int;
        mi int;
        Begin
            Select Count (Distinct(v.matricula_veiculo))into mi
                From armazem a
                    Inner Join viagem v on a.cod_armazem=v.cod_armazem
                    Inner Join Nota_Encomenda ne on ne.id_nota_encomenda=v.id_nota_encomenda
                    where v.data_partida between dataInicio and dataFim and a.cod_armazem = id
                        Having Count (*) = (Select min(Count(*)) From nota_encomenda ne Inner Join Guia_Transporte gt on gt.id_nota_encomenda = ne.id_nota_encomenda group by (ne.id_nota_encomenda , gt.id_nota_encomenda));
             Select Count (Distinct(v.matricula_veiculo)) into ma
                From armazem a
                    Inner Join viagem v on a.cod_armazem=v.cod_armazem
                    Inner Join Nota_Encomenda ne on ne.id_nota_encomenda=v.id_nota_encomenda
                    where v.data_partida between dataInicio and dataFim and a.cod_armazem = id
                        Having Count (*) = (Select max(Count(*)) From nota_encomenda ne Inner Join Guia_Transporte gt on gt.id_nota_encomenda = ne.id_nota_encomenda group by (ne.id_nota_encomenda , gt.id_nota_encomenda));
           if(mi<=0 or ma<=0)  then
                dbms_output.put_line('dados insuficentes');
                return null;
            end if;
            razao:=ma/mi;
    return razao;
           Exception
            when NO_DATA_FOUND then
            return null;
       
    end;
/
-- test true
Declare
    result Integer;
 begin
    result := func_razao_veiculo_transporte(1,'2018/05/10','2018/05/25');
    if result is  not null then
        dbms_output.put_line('razao ='|| result);
    else
        dbms_output.put_line('invalido');
  end if;
END;
/
 
-- test false
Declare
    result Integer;
 begin
    result := func_razao_veiculo_transporte(22,'2018/05/10','2018/05/20');
    if result is  not null then
        dbms_output.put_line('razao ='|| result);
    else
        dbms_output.put_line('invalido');
 end if;
END;
/
/*Alinea 3
 
/*3. Implementar uma função designada func_zona_maior_armazenamento, para obter um cursor com
os identificadores das zonas físicas de um dado armazém, que possuem a maior quantidade de
artigos em stock. A função tem como parâmetro o identificador do armazém. No caso de o
identificador do armazém não existir na BD ou a maior quantidade de artigos em stock for zero, a
função deve retornar o valor NULL.
SET SERVEROUTPUT ON;*/
SET SERVEROUTPUT ON;
CREATE OR REPLACE FUNCTION func_zona_maior_armazenamento (codA Armazem.cod_armazem%TYPE) 
    RETURN SYS_REFCURSOR IS
    cur_codzonafisica     SYS_REFCURSOR;
    l_stock_maxima   Zona_Artigo.stock%TYPE;
BEGIN
    SELECT
        MAX(stock_total)
    INTO l_stock_maxima
    FROM
        (
            SELECT
                SUM(za.stock) AS stock_total
            FROM
                Zona_Artigo za
            WHERE
                za.cod_armazem =codA
            GROUP BY
                za.id_zonas
        );
    IF l_stock_maxima <= 0 THEN
        RETURN NULL;
    END IF;
    
    
    OPEN cur_codzonafisica FOR SELECT
                                   id_zonas
                               FROM
                                   (
                                       SELECT
                                           za.id_zonas,
                                           SUM(za.stock) AS stock_total
                                       FROM
                                           Zona_Artigo za
                                       WHERE
                                           za.cod_armazem =codA
                                       GROUP BY
                                           za.id_zonas
                                   )
                               WHERE
                                   stock_total = l_stock_maxima;
    RETURN cur_codzonafisica;
EXCEPTION
    WHEN no_data_found THEN
        RETURN NULL;
END;
/


