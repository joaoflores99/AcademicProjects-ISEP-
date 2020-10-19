<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output encoding="UTF-8"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:apply-templates select= "casos/cliente"/>
    </xsl:template>
    
    
    <xsl:template match="casos/cliente">
        Nome:
        <xsl:apply-templates select="nome"/>
        
        Objetos:
        <xsl:apply-templates select="objetos"/>
    </xsl:template>
    
    
    <xsl:template match="nome">
        <xsl:value-of select="primeiro"/> <xsl:value-of select="apelido"/>
        <xsl:text>&#xa;</xsl:text>
    </xsl:template>
    
        <xsl:template match="objetos">
            <xsl:apply-templates select="objeto"/>
    </xsl:template>
   
    
    
    <xsl:template match="objeto">
        Nome do objeto:
                <xsl:value-of select="nomeObjeto"/>
        Morada do objeto:
                <xsl:apply-templates select="moradaObjeto"/>  
        Coberturas:
                <xsl:apply-templates select="coberturas"/>  
        Valor prémio:
                <xsl:value-of select="valorPremio"/>  
        Vigência:
                <xsl:apply-templates select="vigencia"/>  
        Ocorrências:
                <xsl:apply-templates select="ocurrencias"/>  
    </xsl:template>
   
    
    <xsl:template match="coberturas">
            <xsl:apply-templates select="cobertura"/>  
    </xsl:template> 
    
    
    <xsl:template match="cobertura">
        Cobertura:
            <xsl:value-of select="@cobertura"/>
    </xsl:template>
    
    
    <xsl:template match="moradaObjeto">
            Rua:
            <xsl:value-of select="rua"/>
            Porta:
            <xsl:value-of select="porta"/>  
            Localidade:
            <xsl:value-of select="localidade"/>  
            Pais:
            <xsl:value-of select="pais"/>  
            Codigo Postal:
            <xsl:value-of select="codigo_postal"/>  
    </xsl:template>
    
    <xsl:template match="vigencia">
            Data inicio:
            <xsl:value-of select="dataInicio/ano"/>/<xsl:value-of select="dataInicio/mes"/>/<xsl:value-of select="dataInicio/dia"/>
            Data fim:
            <xsl:value-of select="dataFim/ano"/>/<xsl:value-of select="dataFim/mes"/>/<xsl:value-of select="dataFim/dia"/>
    </xsl:template>
    
    
    <xsl:template match="ocurrencias">
            Ocurencias Total
            <xsl:value-of select="ocurrenciasTotal"/>
            Ocurencias Penultimo Ano
            <xsl:value-of select="ocurrenciasPenultimoAno"/>
            Ocurencias Ultimo Ano
            <xsl:value-of select="ocurrenciasUltimoAno"/>
    </xsl:template>
    
</xsl:stylesheet>