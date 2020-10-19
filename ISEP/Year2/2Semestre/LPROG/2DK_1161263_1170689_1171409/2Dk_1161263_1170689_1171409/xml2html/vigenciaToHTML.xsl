<?xml version="1.0" encoding="UTF-8"?>
 
<!--
    Document   : AllClienteData.xsl
    Created on : 19 de Maio de 2019, 17:11
    Author     : morei
    Description:
        Purpose of transformation follows.
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"> 
    <xsl:output method="html"/>
    <xsl:template match="/casos"> 
        <html> 
            <body> 
                <h2>Informação sobre clientes e seus produtos cujo data de fim da vigência seja 05/2020</h2>
                <table>
                    <tr bgcolor="#9acd32">
                        <th>Nome do Cliente</th>
                        <th>Nome do objeto</th>
                        <th>Coberturas</th>
                        <th>Vigência</th>
                    </tr>
                    <tr> 
                        <xsl:apply-templates select="cliente"/>
                    </tr> 
                </table>
            </body> 
        </html> 
    </xsl:template> 
 
    <xsl:template match="cliente">
        <xsl:if test="objetos/objeto/vigencia/dataFim/mes=05 and objetos/objeto/vigencia/dataFim/ano=2020">
        <td>
            <h5>
                <xsl:apply-templates select="nome"/>
            </h5>
        </td>
        <h5>
            <xsl:apply-templates select="objetos"/>
        </h5>
        </xsl:if>
    </xsl:template>
    
    <xsl:template match="nome">
        <h6>
            <xsl:value-of select="primeiro"/> 
            <xsl:value-of select="apelido"/>
        </h6>
    </xsl:template> 
    
    <xsl:template match="objetos">
        <h6>
            <xsl:apply-templates select="objeto"/>
        </h6>
    </xsl:template>
   
    
    
    <xsl:template match="objeto">
        <td>
            <h6>
                <xsl:value-of select="nomeObjeto"/>
            </h6>
        </td>
        <td>
            <h6> 
                <xsl:apply-templates select="coberturas"/>  
            </h6>
        </td>
        <td>
            <h6> 
                <xsl:apply-templates select="vigencia"/>  
            </h6>
        </td>
    </xsl:template>
   
    
    <xsl:template match="coberturas">
        <h6> 
            <xsl:apply-templates select="cobertura"/>  
        </h6>
    </xsl:template> 
    
    
    <xsl:template match="cobertura">
        <h6>Nome:
            <xsl:value-of select="@cobertura"/>
        </h6>
        <h6>Descrição:
            <xsl:value-of select="descricao"/>
        </h6>
    </xsl:template>
    
    <xsl:template match="vigencia">
            <h5>Data inicio:
                <xsl:apply-templates select="dataInicio"/>
            </h5>
            <h5>Data fim:
                <xsl:apply-templates select="dataFim"/>
            </h5>
    </xsl:template>
    
    <xsl:template match="dataInicio">
                <h5>
                    <xsl:value-of select="ano"/>/<xsl:value-of select="mes"/>/<xsl:value-of select="dia"/>
                </h5>
    </xsl:template>
    
    <xsl:template match="dataFim">
            <h5>
                <xsl:value-of select="ano"/>/<xsl:value-of select="mes"/>/<xsl:value-of select="dia"/>
            </h5>
    </xsl:template>
    
    
    <xsl:template match="ocurrencias">
        <h5>Ocurencias Total
            <xsl:value-of select="ocurrenciasTotal"/>
        </h5>
        <h5>Ocurencias Penultimo Ano
            <xsl:value-of select="ocurrenciasPenultimoAno"/>
        </h5>
        <h5>Ocurencias Ultimo Ano
            <xsl:value-of select="ocurrenciasUltimoAno"/>
        </h5>
    </xsl:template>
    
</xsl:stylesheet>