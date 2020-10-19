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
        Nome 
        <xsl:value-of select="primeiro"/>
        Apelido
        <xsl:value-of select="apelido"/>
        
    </xsl:template> 
    
    <xsl:template match="objetos">
        Objecto
        <xsl:apply-templates select="objecto"/>
        
    </xsl:template>
   
    
    
    <xsl:template match="objecto"> 
        
        Nome Objeto
        <xsl:value-of select="nomeObjeto"/>
        
        Morada Objeto
        <xsl:apply-templates select="moradaObjeto"/>  
     
        <xsl:variable 
            name="ocurrenciasUltimoAno" 
            select="/ocurrencias/@ocurrenciasUltimoAno"/>
        Valor Premio Objeto
        <xsl:sequence select="sum(/valorPremio * (1-ocurrenciasUltimoAno)*0.10))"/>  
    
        Vigencia Objeto
        <xsl:apply-templates select="vigencia"/>  
    
        Ocurencias Objeto
        <xsl:apply-templates select="ocurrencias"/>  
    
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
        Data inicio
        <xsl:value-of select="dataInicio"/>
        Data fim
        <xsl:value-of select="dataFim"/>
        
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
