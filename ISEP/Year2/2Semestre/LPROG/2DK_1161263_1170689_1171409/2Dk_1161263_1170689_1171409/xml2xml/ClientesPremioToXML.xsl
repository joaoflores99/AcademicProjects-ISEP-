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
        <nomeCliente>
        <xsl:apply-templates select="nome"/>
        </nomeCliente>
        <objetos>
        <xsl:apply-templates select="objetos"/>
        </objetos>
    </xsl:template>
    
    
    <xsl:template match="nome">
        <xsl:value-of select="primeiro"/> <xsl:value-of select="apelido"/>
        <xsl:text>&#xa;</xsl:text>
    </xsl:template>
    
        <xsl:template match="objetos">
            <objeto>
            <xsl:apply-templates select="objeto"/>
            </objeto>
    </xsl:template>
   
    
    
    <xsl:template match="objeto">
        <nomeObjeto>
                <xsl:value-of select="nomeObjeto"/>
        </nomeObjeto>
        <MoradaObjeto>
                <xsl:apply-templates select="moradaObjeto"/>
        </MoradaObjeto>  
        <coberturas>
                <xsl:apply-templates select="coberturas"/>  
        </coberturas>
        <valorPremio>
                <xsl:value-of select="valorPremio"/>  
        </valorPremio>
        <vigencia>
                <xsl:apply-templates select="vigencia"/> 
        </vigencia> 
        <ocorrencias>
                <xsl:apply-templates select="ocurrencias"/>  
        </ocorrencias>
    </xsl:template>
   
    
    <xsl:template match="coberturas">
        <cobertura>
            <xsl:apply-templates select="cobertura"/>  
        </cobertura>
    </xsl:template> 
    
    
    <xsl:template match="cobertura">
        <nomeCobertura>
            <xsl:value-of select="@cobertura"/>
        </nomeCobertura>
    </xsl:template>
    
    
    <xsl:template match="moradaObjeto">
            <rua>
            <xsl:value-of select="rua"/>
            </rua>
            <porta>
            <xsl:value-of select="porta"/>  
            </porta>
            <localidade>
            <xsl:value-of select="localidade"/>
            </localidade>  
            <pais>
            <xsl:value-of select="pais"/> 
            </pais>
            <codigoPostal> 
            <xsl:value-of select="codigo_postal"/>  
            </codigoPostal>
    </xsl:template>
    
    <xsl:template match="vigencia">
            <dataInicio>
            <xsl:value-of select="dataInicio/ano"/>/<xsl:value-of select="dataInicio/mes"/>/<xsl:value-of select="dataInicio/dia"/>
            </dataInicio>
            <dataFim>
            <xsl:value-of select="dataFim/ano"/>/<xsl:value-of select="dataFim/mes"/>/<xsl:value-of select="dataFim/dia"/>
            </dataFim>
    </xsl:template>
    
    
    <xsl:template match="ocurrencias">
            <OcurrenciasTotal>
                <xsl:value-of select="ocurrenciasTotal"/>
            </OcurrenciasTotal>
            <OcorrenciasPenultimo>
            <xsl:value-of select="ocurrenciasPenultimoAno"/>
            </OcorrenciasPenultimo>
            <OcurrenciasUltimo>
            <xsl:value-of select="ocurrenciasUltimoAno"/>
            </OcurrenciasUltimo>
    </xsl:template>
    
</xsl:stylesheet>