
--a
-- * 60 porque sao as 52 mais dois meses de subsidio 
select * From (Select avg(salario_semanal) from Empregado e, Armazem a where e.cod_armazem=a.cod_armazem  and a.nome like 'Parafusos'),(
select AVG(e.salario_semanal*60) AS salario_anual from empregado e, armazem a WHERE e.cod_armazem = a.cod_armazem AND a.nome LIKE 'Parafusos');

--b
Select oc.* from OrdemCompra oc
    where estado =3
    AND nr_ordem
    NOT In (SELECT nr_ordem FROM ordemcompraproduto WHERE cod_produto
        IN (SELECT fp.cod_produto FROM FornecedorProduto fp, produto p  WHERE fp.cod_produto= p.cod_produto  and fp.desconto
            IN(SELECT MAX(desconto) FROM FornecedorProduto) ));
 

--c
Select Distinct a.nome From armazem a Inner Join armazemProduto ap On a.cod_armazem = ap.cod_armazem Where ap.cod_produto IN
(Select ap.cod_produto From armazemProduto ap Where ap.cod_armazem = (Select e.cod_armazem From empregado e Group By e.cod_armazem 
Having Count(*) = (Select Max(Count(*)) From empregado Group By cod_armazem)))MINUS Select nome From armazem 
Where cod_armazem = (Select cod_armazem From empregado Group By cod_armazem Having Count(*) = 
(Select Max(Count(*)) From empregado Group By cod_armazem));
--d
SELECT a.cod_armazem, a.nome, a.cidade, (SELECT COUNT(*) 
    FROM ordemCompra oc, empregado e
    WHERE oc.cod_empregado = e.cod_empregado 
    AND e.cod_armazem = a.cod_armazem
    AND oc.estado = 2
    And oc.data_compra Between '2018/03/01 00:00:00' AND  '2018/10/15 23:59:59' 
    AND oc.data_entrega  Between '2018/03/01 00:00:00' AND  '2018/10/15 23:59:59')
    AS QUANTIDADE_ORDENS_DE_COMPRA FROM armazem a GROUP BY a.nome, a.cod_armazem, a.cidade
    HAVING (SELECT COUNT(*) FROM ordemCompra oc, empregado e WHERE oc.cod_empregado = e.cod_empregado
            AND e.cod_armazem = a.cod_armazem
            AND oc.estado = 2
            AND (oc.data_compra Between '2018/03/01 00:00:00' AND  '2018/10/15 23:59:59' 
            AND oc.data_entrega  Between '2018/03/01 00:00:00' AND  '2018/10/15 23:59:59')
    ) > ALL (SELECT (SELECT COUNT(*)
        FROM ordemCompra oc, empregado e
        WHERE oc.cod_empregado = e.cod_empregado
        AND e.cod_armazem = a.cod_armazem
        AND oc.estado = 2
       AND( oc.data_compra Between '2018/03/01 00:00:00' AND  '2018/10/15 23:59:59' 
      AND oc.data_entrega  Between '2018/03/01 00:00:00' AND  '2018/10/15 23:59:59')) AS Y
    FROM armazem a
    WHERE a.cidade LIKE 'Porto'
    GROUP BY a.cod_armazem);

--e
SELECT e.nome FROM Empregado e
    ,(SELECT COUNT(DISTINCT(oc1.cod_empregado))  AS numeroOrdemNolmal FROM OrdemCompra oc1, Empregado e1 
    WHERE oc1.cod_empregado = e1.cod_empregado AND e1.cod_supervisor IS NOT NULL) 
    ,(SELECT COUNT(DISTINCT(oc2.cod_empregado)) AS numeroOrdemSupervisor FROM OrdemCompra oc2, Empregado e2
     WHERE oc2.cod_empregado = e2.cod_empregado AND e2.cod_supervisor IS NULL AND (e2.salario_semanal*4) BETWEEN 1000 AND 3000) 
WHERE e.cod_supervisor IS NOT NULL  AND (e.salario_semanal*4) BETWEEN 1000 AND 3000 and numeroOrdemNolmal>numeroOrdemSupervisor;

--f
Select ap.cod_produto, ap.cod_armazem, ap.corredor, ap.prateleira From armazemProduto ap, ordemCompraProduto op 
where ap.cod_produto=op.cod_produto Group by ap.cod_produto , ap.cod_armazem,ap.corredor, ap.prateleira
Having count (*) =(Select Min(count(*)) as cont From ordemCompraproduto Group by cod_produto);

--g
SELECT a.corredor
FROM  armazemproduto a 
INNER JOIN produto p ON a.cod_produto = p.cod_produto
INNER JOIN fornecedorProduto f ON p.cod_produto = f.cod_produto
INNER JOIN OrdemCompraProduto o ON p.cod_produto = o.cod_produto
where f.desconto > 20 AND o.cod_produto IN (SELECT cod_produto
                                        FROM OrdemCompraProduto o2
                                        GROUP BY o2.cod_produto
                                        HAVING count(o2.cod_produto) = (SELECT MAX(count(cod_produto))
                                                                        FROM OrdemCompraProduto
                                                                        GROUP BY cod_produto));
--h
SELECT p.*,                                                 
        SUM(CASE WHEN (EXTRACT(MONTH FROM oc.data_compra)) = 1 THEN (ocp.quantidade_solicitada*p.preco*(ocp.desconto_pedido/100)) ELSE 0 END) AS janeiro,
        SUM(CASE WHEN (EXTRACT(MONTH FROM oc.data_compra)) = 2 THEN ocp.quantidade_solicitada*p.preco*(ocp.desconto_pedido/100) ELSE 0 END) AS fevereiro,
        SUM(CASE WHEN (EXTRACT(MONTH FROM oc.data_compra)) = 3 THEN ocp.quantidade_solicitada*p.preco*(ocp.desconto_pedido/100) ELSE 0 END) AS março,
        SUM(CASE WHEN (EXTRACT(MONTH FROM oc.data_compra)) = 4 THEN ocp.quantidade_solicitada*p.preco*(ocp.desconto_pedido/100) ELSE 0 END) AS abril,
        SUM(CASE WHEN (EXTRACT(MONTH FROM oc.data_compra)) = 5 THEN ocp.quantidade_solicitada*p.preco*(ocp.desconto_pedido/100) ELSE 0 END) AS maio,
        SUM(CASE WHEN (EXTRACT(MONTH FROM oc.data_compra)) = 6 THEN ocp.quantidade_solicitada*p.preco*(ocp.desconto_pedido/100) ELSE 0 END) AS junho,
        SUM(CASE WHEN (EXTRACT(MONTH FROM oc.data_compra)) = 7 THEN ocp.quantidade_solicitada*p.preco*(ocp.desconto_pedido/100) ELSE 0 END) AS julho,
        SUM(CASE WHEN (EXTRACT(MONTH FROM oc.data_compra)) = 8 THEN ocp.quantidade_solicitada*p.preco*(ocp.desconto_pedido/100) ELSE 0 END) AS agosto,
        SUM(CASE WHEN (EXTRACT(MONTH FROM oc.data_compra)) = 9 THEN ocp.quantidade_solicitada*p.preco*(ocp.desconto_pedido/100) ELSE 0 END) AS setembro,
        SUM(CASE WHEN (EXTRACT(MONTH FROM oc.data_compra)) = 10 THEN ocp.quantidade_solicitada*p.preco*(ocp.desconto_pedido/100) ELSE 0 END) AS outobro
    FROM produto p INNER JOIN ordemcompraproduto ocp ON p.cod_produto=ocp.cod_produto
    INNER JOIN ordemcompra oc ON ocp.nr_ordem=oc.nr_ordem  
    INNER JOIN armazemproduto ap ON p.cod_produto=ap.cod_produto
    WHERE stock*0.5>stock_minimo AND REGEXP_LIKE(data_compra, '[^18%]')GROUP BY p.cod_produto, p.descricao, p.unidade_medida,p.preco ORDER BY p.cod_produto;


--i
SELECT oc.*  ,(oc.data_compra) as dataCompra,(oc.data_entrega) as dataEntrega
                from produto p INNER JOIN ordemcompraproduto ocp on p.cod_produto=ocp.cod_produto
                INNER JOIN ordemCompra oc on oc.nr_ordem=ocp.nr_ordem
                where oc.estado=3 and EXTRACT (Hour from oc.data_compra)<10 and EXTRACT (YEAR from oc.data_compra)=2018 and EXTRACT (MONTH from oc.data_compra) BETWEEN 6 and 8
                    and TRUNC(oc.data_entrega, 'DAY') > TRUNC(oc.data_compra, 'DAY') +10;

--j
Select e.nome,(Select Count(*) From ordemCompra Group By cod_empregado, estado, valor_total Having cod_empregado = 1223 
And estado = 3 And valor_total Between 5000 And 10000)/(Select Count(*) From ordemCompra oc Inner Join empregado e 
On oc.cod_empregado = e.cod_empregado where cod_armazem = 
(Select cod_armazem From armazem Where Upper(nome) Like 'TINTAS') And estado = 3 And valor_total Between 5000 And 10000) 
As Percentagem From empregado e Where e.cod_empregado = 1223;