<?xml version="1.0" encoding="UTF-8"?> 
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"> 
    <xsl:output method="html"/>
    <xsl:template match="/casos"> 
        <html> 
            <body> 
                <table border="1">
                    <h3>Clientes e os seus detalhes</h3>
                    <tr bgcolor="#9acd32">
                        <th>Nome do Cliente</th>
                        <th>Número CC</th>
                        <th>Data validade CC</th>
                        <th>Contacto</th>
                        <th>Morada</th>
                    </tr>
                    <tr> 
                        <xsl:apply-templates select="cliente"/>
                    </tr> 
                </table>    
            </body> 
        </html> 
    </xsl:template> 
    <xsl:template match="casos">
        <html>
            <body>
                <xsl:apply-templates select="cliente"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="cliente">
        <h5>
            <xsl:apply-templates select="nome"/>
        </h5>
        <h3>
            <xsl:apply-templates select="cc"/>
        </h3>
        <h5>
            <xsl:apply-templates select="contactos"/>
        </h5>
        <h5>
            <xsl:apply-templates select="morada"/>
        </h5>
    </xsl:template>
    
    <xsl:template match="cc">
        <td>
            <h6>Numero:
                <xsl:value-of select="numero"/>
            </h6>
        </td>
        <td>
            <h6> 
                Data Fim:
                <xsl:value-of select="dataFim"/>  
            </h6>
        </td>
    </xsl:template>
    
    
    
    <xsl:template match="nome">
        <td>
            <h6>
                <xsl:value-of select="primeiro"/> 
                <xsl:value-of select="apelido"/> 
            </h6>
        </td>
    </xsl:template>
    
    
    <xsl:template match="contactos">
        <td>
            <h6>Telefone:
                <xsl:value-of select="telefone"/>
            </h6>
        </td>
    </xsl:template> 
    
    
    <xsl:template match="morada">
        
        <th>
     
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
        </th>
    </xsl:template>
</xsl:stylesheet>