 
/*Alinea 7
Trigger
Implementar um trigger designado trg_nota_encomenda_impedir_registo, registada uma nota de encomenda de um
cliente se este tiver faturas por pagar hÂ· mais de trÃ�s meses*/
 
SET SERVEROUTPUT ON
create or replace trigger trg_nota_encomenda_impedir_registo
    before insert or update on Nota_Encomenda
    for each row
    declare
   
    nr_faturas_nao_pagas INTEGER;
   
    BEGIN
    SELECT COUNT(*) INTO nr_faturas_nao_pagas FROM Fatura WHERE Fatura.cod_cliente =: NEW.cod_cliente AND Fatura.estado = 'Pendente' AND Fatura.data_emissao < ADD_Months (SYSDATE, -3);
 
IF nr_faturas_nao_pagas > 0 THEN
raise_application_error(-20678, 'Nota de encomenda não emitida porque existem faturas com mais de 3 meses sem pagamento');
END IF;
   
END;
/
--Alinea 8
--Trigger
--Implementar um trigger designado trg_viagem_impedir_atribuicao_veiculo, que impeça que seja atribuído um veículo a uma nova viagem, 
--se este já efetuou o número máximo de quatro viagens permitidas por semana ou, se está indisponível.

create or replace trigger  trg_viagem_impedir_atribuicao_veiculo
    before insert on viagem
    for each row
    declare
   
    nr_viagens_por_semana INTEGER;
    disponibilidade VARCHAR(40);
   
    BEGIN
    SELECT count(*) INTO nr_viagens_por_semana FROM viagem V 
         where V.matricula_veiculo = :NEW.matricula_veiculo 
         AND TO_CHAR(/**v.data_partida**/ sysdate, 'WW') = TO_CHAR(:NEW.data_partida, 'WW')
        AND EXTRACT(YEAR FROM sysdate/**v.data_partida**/) = EXTRACT(YEAR FROM :NEW.data_partida);  

    select veiculos.disponibilidade into  disponibilidade from veiculos where veiculos.matricula_veiculo = :NEW.matricula_veiculo;
    IF nr_viagens_por_semana >= 4 THEN
        raise_application_error(-20678, 'Número de viagens por semana excedido');
    else
    IF (disponibilidade  = 'INDISPONIVEL') then 
    raise_application_error(-20679, ' VEICULO INVALIDO');
    end if;
    end if;
End;
/
 



--alinea 9
/**

9. Implementar um trigger designado trg_guia_saida_atualizar_stock, que garanta que o stock é
corretamente atualizado de acordo com a informação das guias de saída, ou seja, quando se cria,
altera ou apaga uma linha de uma determinada guia de saída.
**/
create or replace trigger trg_guia_saida_atualizar_stock
before insert or update or delete on Guia_Saida
    for each row
    declare
        Cursor cursor_encomenda Is
            Select ne.id_nota_encomenda,referencia,quantidade from Nota_Encomenda ne where ne.id_nota_encomenda=:New.id_nota_encomenda;
        Cursor cursor_zona Is
            Select z.capacidade,za.referencia,za.id_zonas,za.stock, za.stock_minimo from zona_artigo za Inner Join Zonas z On z.id_zonas=za.id_zonas where z.id_zonas=:New.id_zonas;
        Stock_valor Integer;
        Stock_minimo_valor Integer;
        Quantidade_valor Integer;
        Referencia_valor Integer;
        Nota_linha cursor_encomenda%ROWTYPE;
        Zona_linha cursor_zona%ROWTYPE;
    Begin
        If Inserting Then
            Open cursor_encomenda;
            Open cursor_zona;
            Loop
            Fetch cursor_zona Into Zona_linha;
                Select stock,stock_minimo  Into Stock_valor,Stock_minimo_valor from Zona_Artigo za where za.id_zonas=Zona_linha.id_zonas;
                 Fetch cursor_encomenda Into Nota_linha;
                    Select referencia,quantidade  Into Referencia_valor, Quantidade_valor from Nota_encomenda ne where ne.id_nota_encomenda =Nota_linha.id_nota_encomenda;
           If(Stock_valor-Quantidade_valor>Stock_minimo_valor) Then
                        Insert Into Zona_Artigo 
                        (id_zonas,referencia,Stock_minimo,Stock) Values(Zona_linha.id_zonas,Referencia_valor,Stock_minimo_valor,Stock_valor-Quantidade_valor);
                    Else
                        Raise_application_error(-20678, 'O stock nao consegue corresponder à necessidade');
                    End If;
           End Loop;
            CLOSE cursor_encomenda;
            close cursor_zona;   
        End If;
     
        
        If Deleting Then
            Open cursor_encomenda;
            Open cursor_zona;
            Loop
                Fetch cursor_zona Into Zona_linha;
                    Select stock,stock_minimo  Into Stock_valor,Stock_minimo_valor from Zona_Artigo za where za.id_zonas=Zona_linha.id_zonas;
                Fetch cursor_encomenda Into Nota_linha;
                    Select referencia,quantidade  Into Referencia_valor, Quantidade_valor from Nota_encomenda ne where ne.id_nota_encomenda =Nota_linha.id_nota_encomenda;
                    If(Stock_valor+Quantidade_valor<Zona_linha.capacidade) Then
                        Update Zona_Artigo
                            Set stock = Stock_valor+Quantidade_valor
                        where zona_artigo.referencia=Referencia_valor and zona_artigo.id_zonas=Zona_linha.id_zonas;
                    Else
                        Raise_application_error(-20678, 'O stock ira ultrapassar a capacidade da zona');
                    End If;
            End Loop;
             CLOSE cursor_encomenda;
    close cursor_zona;  
        End If;
     
        If Updating Then
            Open cursor_encomenda;
            Open cursor_zona;
            Loop
                Fetch cursor_zona Into Zona_linha;
                    Select stock,stock_minimo  Into Stock_valor,Stock_minimo_valor from Zona_Artigo za where za.id_zonas=Zona_linha.id_zonas;
                Fetch cursor_encomenda Into Nota_linha;
                    Select referencia,quantidade  Into Referencia_valor, Quantidade_valor from Nota_encomenda ne where ne.id_nota_encomenda =Nota_linha.id_nota_encomenda;
                    If(Stock_valor-Quantidade_valor>Zona_linha.capacidade and Stock_valor+Quantidade_valor<Zona_linha.capacidade) Then
                        Update Zona_Artigo
                            Set stock = Stock_valor-Quantidade_valor
                        where zona_artigo.referencia=Referencia_valor and zona_artigo.id_zonas=Zona_linha.id_zonas;
                    Else
                        Raise_application_error(-20678, 'O stock ira ultrapassar a capacidade da zona');
                    End If;
            End Loop;
            CLOSE cursor_encomenda;
    close cursor_zona;
        End If;
    End;
    
/










