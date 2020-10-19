<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output encoding="UTF-8"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <cliente>
        <xsl:apply-templates select= "casos/cliente"/>
        </cliente>
    </xsl:template>
    
    
    <xsl:template match="casos/cliente">
        <CC>
            <xsl:apply-templates select="cc"/>
        </CC>
        <Nome>
            <xsl:apply-templates select="nome"/>
        </Nome>
        <Contactos>
            <xsl:apply-templates select="contactos"/>
        </Contactos>
        <Morada>
            <xsl:apply-templates select="morada"/>
        </Morada>
    </xsl:template>
    
    
    <xsl:template match="cc">
        <numero> 
            <xsl:value-of select= "numero"/>
        </numero>
        <dataFim>
            <xsl:value-of select= "dataFim"/>
        </dataFim>
        <xsl:text>&#xa;</xsl:text>
    </xsl:template>
    
    <xsl:template match="nome">
        <Primeiro>
            <xsl:value-of select="primeiro"/>
        </Primeiro>
        <Apelido>
            <xsl:value-of select="apelido"/>
        </Apelido>
        <xsl:text>&#xa;</xsl:text>
    </xsl:template>
    
    <xsl:template match="contactos">
        <Telefone>
            <xsl:value-of select="telefone"/>
        </Telefone>
        <xsl:text>&#xa;</xsl:text>
    </xsl:template> 
    
    
    <xsl:template match="morada">
        <Rua>
            <xsl:value-of select="rua"/>
        </Rua>
        <Porta>
            <xsl:value-of select="porta"/> 
        </Porta>
        <Localidade>
            <xsl:value-of select="localidade"/>
        </Localidade>  
        <Pais>
            <xsl:value-of select="pais"/> 
        </Pais> 
        <CodigoPostal>
            <xsl:value-of select="codigo_postal"/> 
        </CodigoPostal>
        <xsl:text>&#xa;</xsl:text> 
    </xsl:template>

</xsl:stylesheet>