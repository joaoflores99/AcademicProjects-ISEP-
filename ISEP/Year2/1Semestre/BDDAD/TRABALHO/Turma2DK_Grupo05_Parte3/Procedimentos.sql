

--Alinea4 
/**
4. Implementar um procedimento designado proc_faturas_nao_liquidadas, para realizar uma listagem
com as faturas, de um dado período de tempo, que ainda não foram liquidadas. O procedimento tem
como parâmetro o período (data de início e de fim) e no caso de não ser indicada uma das datas
serão analisadas todas as faturas não liquidadas. Deve ser apresentada informação (identificador,
data de faturação e valor total) das faturas em falta de cada cliente, ordenada pela data de faturação,
bem como, o subtotal a liquidar por cliente. De igual forma deve ser apresentado o total de todas as
faturas, de todos os que clientes, que faltam ser liquidadas à IsepBricolage. 
**/

SET SERVEROUTPUT ON;
CREATE OR REPLACE PROCEDURE proc_faturas_nao_liquidadas(data_inicio IN OUT fatura.data_emissao%TYPE, data_fim IN OUT fatura.data_emissao%TYPE)
    Is
        data_inicio_valor fatura.data_emissao%TYPE;
        data_fim_valor fatura.data_emissao%TYPE;
        valor_total Integer :=0;
        valor_intermedio Integer;
        cliente_valor   cliente%ROWTYPE;
        id_cliente_valor Integer;
        Cursor cliente_cursor is
          Select c.cod_cliente from Cliente c;
    Begin    
        If(data_inicio Is Null) Then
            Select Min(data_emissao) into data_inicio_valor from Fatura;
            data_fim_valor:=data_fim;
        End if;
        If(data_fim Is Null) Then
            Select Max(data_emissao) into data_fim_valor from Fatura;
            data_inicio_valor:=data_inicio;
        End if;
        If(data_fim Is Null And data_inicio Is Null) Then
            Select Max(data_emissao) into data_fim_valor from Fatura;
            Select Min(data_emissao) into data_inicio_valor from Fatura;
        End if;
        End if;
        If(data_fim Is Not Null And data_inicio Is Not Null) Then
            data_inicio_valor:=data_inicio;
            data_fim_valor:=data_fim;
        End if;
        Open cliente_cursor;
        Loop  
            Fetch cliente_cursor into cliente_valor;
                Select cod_cliente from Fatura f where lower(f.estado) like 'nao liquidada' and f.data_emissao between data_inicio_valor and data_fim_valor;
               
                valor_intermedio:=0;
                Select SUM(fa.iva*fa.valor_base-fa.desconto) into valor_intermedio from fatura_artigo fa inner Join Fatura f on fa.num_fatura=f.num_fatura WHERE
                    lower(estado) LIKE ( 'nao liquidada' )
                    AND f.cod_cliente = cliente_valor.cod_cliente
                    AND f.data_emissao BETWEEN data_fim_valor AND data_fim_valor
                    ORDER BY
                        f.data_emissao;
                    dbms_output.put_line('Cliente:' || cliente_valor.cod_cliente);
                    dbms_output.put_line('Valor:' || valor_intermedio);
                valor_total:=valor_total + valor_intermedio;
        End Loop;
        Close cliente_cursor;
       
        dbms_output.put_line('Valor total por liquidar :' || valor_total);
    End;
/







--Alinea 6
--PROCEDIMENTOS
--Elaborar um procedimento designado proc_atualizar_tipo_cliente, que atualize o valor do tipo de cliente, para todos os clientes, de acordo com o volume de negócio que efetuaram (ver Tabela 1). 
--O período de volume de negócios a ser considerado é o dos 12 meses anteriores. 
--No entanto, só os clientes que foram atualizados há mais de 12 meses é que podem ser atualizados. Se se entender necessário podem ser realizadas alterações ao modelo relacional.


create or replace procedure proc_atualizar_tipo_cliente is
clientes sys_refcursor;
cliente_temp cliente.cod_cliente%type;

volume_negocio numeric;
valor_de_acrescimo_iva INTEGER;
valor_a_descontar_valortotal Integer;
 --cursor c_clientes is (select cod_cliente from Historico_tipo_Cliente H where months_between(sysdate,H.data_ultima_atualizacao) < 12);    
 
   --r_cliente c_clientes%rowtype;
 begin
     open clientes for select cod_cliente from cliente H where data_ultima_atualizacao<(sysdate-365);--where months_between(sysdate,H.data_ultima_atualizacao) < 12;    
     
    
loop
    fetch clientes into cliente_temp;
    exit when clientes%notfound;
    
    select  (iva *valor_base)  into valor_de_acrescimo_iva from  Fatura_Artigo F 
    where F.num_fatura in (select num_fatura from fatura
    where fatura.cod_cliente = cliente_temp
    --and  months_between(sysdate,fatura.data_emissao) <= 12
    --and fatura.data_emissao is not null
     );  
     
     
    select  (desconto *valor_base)  into valor_a_descontar_valortotal from  Fatura_Artigo F 
    where F.num_fatura in (select num_fatura from fatura
    where fatura.cod_cliente = cliente_temp
    --and  months_between(sysdate,fatura.data_emissao) <= 12
    --and fatura.data_emissao is not null
     );  
     
    
    select sum(((quantidade*valor_base)+valor_de_acrescimo_iva)-valor_a_descontar_valortotal) into volume_negocio
    from Fatura_Artigo Fart where Fart.num_fatura in (select num_fatura from fatura
    where fatura.cod_cliente = cliente_temp
    --and  months_between(sysdate,fatura.data_emissao) <= 12
    --and fatura.data_emissao is not null
    ); 
    
    
if volume_negocio >50000 then
    update Cliente set tipo_cliente = 'VIP' where Cliente.cod_cliente = cliente_temp;
    update Cliente set data_ultima_atualizacao= sysdate  where Cliente.cod_cliente = cliente_temp;
    end if; 
    
    if volume_negocio between 30000 and 50000 then
    update Cliente set tipo_cliente = 'Grande cliente' where Cliente.cod_cliente = cliente_temp;
    update Cliente set data_ultima_atualizacao=sysdate  where Cliente.cod_cliente = cliente_temp;
    end if; 
    
    if volume_negocio<30000 then
    update Cliente set tipo_cliente = 'Pequeno Cliente' where Cliente.cod_cliente = cliente_temp;
    update Cliente set data_ultima_atualizacao=sysdate  where Cliente.cod_cliente = cliente_temp;
    --update Historico_tipo_Cliente set tipo_cliente = 'Pequeno Cliente' where Historico_tipo_Cliente.cod_cliente =cliente_temp;
    --update Historico_tipo_Cliente set data_ultima_atualizacao= sysdate  where Historico_tipo_Cliente.cod_cliente = cliente_temp;
    end if;
end loop;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('Dados inexistente '||SYSDATE);
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Ocorreu um erro '||SYSDATE);
    

close clientes;
end;
/


begin proc_atualizar_tipo_cliente; end;
/

select tipo_cliente from cliente where cod_cliente=5;


select tipo_cliente from Historico_tipo_Cliente H where H.cod_cliente=5;
select data_ultima_atualizacao from Historico_tipo_Cliente H where H.cod_cliente=5;




