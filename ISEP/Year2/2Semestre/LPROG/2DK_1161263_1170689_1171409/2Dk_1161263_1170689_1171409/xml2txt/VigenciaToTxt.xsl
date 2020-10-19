<?xml version="1.0" encoding="UTF-8"?>
 
<!--
    Document   : AllClienteData.xsl
    Created on : 19 de Maio de 2019, 17:11
    Author     : morei
    Description:
        Purpose of transformation follows.
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"> 
    <xsl:template match="/casos"> 
                Informacao sobre clientes e seus produtos cujo data de fim da vigencia seja 05/2020
                        <xsl:apply-templates select="cliente"/>
    </xsl:template> 
 
    <xsl:template match="cliente">
        <xsl:if test="objetos/objeto/vigencia/dataFim/mes=05 and objetos/objeto/vigencia/dataFim/ano=2020">
                Nome do Cliente: <xsl:apply-templates select="nome"/>
                Objetos:
            <xsl:apply-templates select="objetos"/>
        </xsl:if>
    </xsl:template>
    
    <xsl:template match="nome">
            <xsl:value-of select="primeiro"/> <xsl:value-of select="apelido"/>
    </xsl:template> 
    
    <xsl:template match="objetos">
            <xsl:apply-templates select="objeto"/>
    </xsl:template>
   
    
    
    <xsl:template match="objeto">
                Nome do Objeto: <xsl:value-of select="nomeObjeto"/>
                Coberturas:
                <xsl:apply-templates select="coberturas"/>  
                Vigencia: <xsl:apply-templates select="vigencia"/> 
    </xsl:template>
   
    
    <xsl:template match="coberturas">
            <xsl:apply-templates select="cobertura"/>  
    </xsl:template> 
    
    
    <xsl:template match="cobertura">
               Nome Cobertura: <xsl:value-of select="@cobertura"/>
               Descricao: <xsl:value-of select="descricao"/>
    </xsl:template>
    
    <xsl:template match="vigencia">
               Data inicio: <xsl:apply-templates select="dataInicio"/>
               Data fim: <xsl:apply-templates select="dataFim"/>
    </xsl:template>
    
    <xsl:template match="dataInicio">
                    <xsl:value-of select="ano"/>/<xsl:value-of select="mes"/>/<xsl:value-of select="dia"/>
    </xsl:template>
    
    <xsl:template match="dataFim">
                <xsl:value-of select="ano"/>/<xsl:value-of select="mes"/>/<xsl:value-of select="dia"/>
    </xsl:template>
    
    
    <xsl:template match="ocurrencias">
            Ocurencias Total: <xsl:value-of select="ocurrenciasTotal"/>
            Ocurencias Penultimo Ano: <xsl:value-of select="ocurrenciasPenultimoAno"/>
            Ocurencias Ultimo Ano: <xsl:value-of select="ocurrenciasUltimoAno"/>
    </xsl:template>
    
</xsl:stylesheet>