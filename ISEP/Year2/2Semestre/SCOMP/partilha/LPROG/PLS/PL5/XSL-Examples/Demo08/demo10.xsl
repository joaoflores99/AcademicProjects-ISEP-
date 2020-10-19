<?xml version="1.0" encoding="UTF-8"?>
<!-- Building an "HTML" table --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="cars">
    <table border="1" width="500">
       <xsl:apply-templates />
    </table>
  </xsl:template>

  <xsl:template match="car">
    <tr bgcolor="#dddddd">
      <xsl:apply-templates select="@*" />
    </tr>
  </xsl:template>

  <xsl:template match="@*">
    <td><xsl:value-of select="." /></td>
  </xsl:template>
</xsl:stylesheet>
