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
        Nome: <xsl:apply-templates select="nome"/>
        CC:  <xsl:apply-templates select="cc"/>
        Contactos: <xsl:apply-templates select="contactos"/>
        Morada: <xsl:apply-templates select="morada"/>
    </xsl:template>
    
    
    <xsl:template match="cc">
        numero: <xsl:value-of select= "numero"/>
        data Fim:<xsl:value-of select= "dataFim"/>
        <xsl:text>&#xa;</xsl:text>
    </xsl:template>
    
    <xsl:template match="nome">
        <xsl:value-of select="primeiro"/> <xsl:value-of select="apelido"/> 
        <xsl:text>&#xa;</xsl:text>
    </xsl:template>
    
    <xsl:template match="contactos">
        Telefone: <xsl:value-of select="telefone"/>
        <xsl:text>&#xa;</xsl:text>
    </xsl:template> 
    
    
    <xsl:template match="morada">
        Rua: <xsl:value-of select="rua"/>
        Porta: <xsl:value-of select="porta"/> 
        Localidade: <xsl:value-of select="localidade"/> 
        Pais: <xsl:value-of select="pais"/>  
        Codigo Postal: <xsl:value-of select="codigo_postal"/>
        <xsl:text>&#xa;</xsl:text> 
    </xsl:template>
    
</xsl:stylesheet>