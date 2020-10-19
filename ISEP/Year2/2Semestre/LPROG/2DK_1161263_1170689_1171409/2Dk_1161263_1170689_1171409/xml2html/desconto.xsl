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
                <h3> Clientes' Details</h3> 
                <tr> 
                    <xsl:apply-templates select="cliente"/>
                </tr> 
            </body> 
        </html> 
    </xsl:template> 
 
    <xsl:template match="cliente">
        <h5>Nome:
            <xsl:apply-templates select="nome"/>
        </h5>
        <h5>Objetos:
            <xsl:apply-templates select="objetos"/>
        </h5>
    </xsl:template>
    
    <xsl:template match="nome">
        <h6>Nome 
            <xsl:value-of select="primeiro"/>
        </h6>
        <h6>Apelido
            <xsl:value-of select="apelido"/>
        </h6>
    </xsl:template> 
    
    <xsl:template match="objetos">
        <h6>Objecto
            <xsl:apply-templates select="objecto"/>
        </h6>
    </xsl:template>
   
    
    
    <xsl:template match="objecto"> 
        <h6>
            Nome Objeto
            <xsl:value-of select="nomeObjeto"/>
        </h6>
        <h6> 
            Morada Objeto
            <xsl:apply-templates select="moradaObjeto"/>  
        </h6>
        <h6> 
            <xsl:variable 
                name="ocurrenciasUltimoAno" 
                select="/ocurrencias/@ocurrenciasUltimoAno"/>
            Valor Premio Objeto
            <xsl:sequence select="sum(/valorPremio * (1-ocurrenciasUltimoAno)*0.10))"/>  
        </h6>
        <h6> 
            Vigencia Objeto
            <xsl:apply-templates select="vigencia"/>  
        </h6>
        <h6> 
            Ocurencias Objeto
            <xsl:apply-templates select="ocurrencias"/>  
        </h6>
    </xsl:template>
   
    
    
    <xsl:template match="moradaObjeto">
        <tr bgcolor="#9acd32">
            <th>morada</th>
     
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
        </tr>
    </xsl:template>
    
    <xsl:template match="vigencia">
        <h5>Data inicio
            <xsl:value-of select="dataInicio"/>
        </h5>
        <h5>Data fim
            <xsl:value-of select="dataFim"/>
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
