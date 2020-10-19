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
                <table>
                    <h3>Dados dos clientes e os seus objetos</h3>
                    <tr bgcolor="#9acd32">
                        <th>Nome do Cliente</th>
                        <th>Nome do objeto</th>
                        <th>Morada</th>
                        <th>Coberturas</th>
                        <th>Valor Prémio</th>
                        <th>Vigência</th>
                        <th>Incidências</th>
                    </tr>
                    <tr> 
                        <xsl:apply-templates select="cliente"/>
                    </tr> 
                </table>
            </body> 
        </html> 
    </xsl:template> 
 
    <xsl:template match="cliente">
        <td>
            <h5>
                <xsl:apply-templates select="nome"/>
            </h5>
        </td>
        <h5>
            <xsl:apply-templates select="objetos"/>
        </h5>
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
                <xsl:apply-templates select="moradaObjeto"/>  
            </h6>
        </td>
        <td>
            <h6> 
                <xsl:apply-templates select="coberturas"/>  
            </h6>
        </td>
        <td>
            <h6> 
                <xsl:value-of select="valorPremio"/>  
            </h6>
        </td>
        <td>
            <h6> 
                <xsl:apply-templates select="vigencia"/>  
            </h6>
        </td>
        <td>
            <h6> 
                <xsl:apply-templates select="ocurrencias"/>  
            </h6>
        </td>
    </xsl:template>
   
    
    <xsl:template match="coberturas">
        <h6> 
            <xsl:apply-templates select="cobertura"/>  
        </h6>
    </xsl:template> 
    
    
    <xsl:template match="cobertura">
        <h6>
            <xsl:value-of select="@cobertura"/>
        </h6>
    </xsl:template>
    
    
    <xsl:template match="moradaObjeto">
        <h6>Rua:
            <xsl:value-of select="rua"/>
        </h6>
        <h6> 
            Porta:
            <xsl:value-of select="porta"/>  
        </h6>
        <h6> 
            Localidade:
            <xsl:value-of select="localidade"/>  
        </h6>
        <h6> 
            Pais:
            <xsl:value-of select="pais"/>  
        </h6>
        <h6> 
            Codigo Postal:
            <xsl:value-of select="codigo_postal"/>  
        </h6>
    </xsl:template>
    
    <xsl:template match="vigencia">
        <h5>Data inicio:
            <xsl:value-of select="dataInicio/ano"/>/<xsl:value-of select="dataInicio/mes"/>/<xsl:value-of select="dataInicio/dia"/>
        </h5>
        <h5>Data fim:
            <xsl:value-of select="dataFim/ano"/>/<xsl:value-of select="dataFim/mes"/>/<xsl:value-of select="dataFim/dia"/>
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