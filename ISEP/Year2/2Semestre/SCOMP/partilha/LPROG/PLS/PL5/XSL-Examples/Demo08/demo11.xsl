<?xml version="1.0" encoding="UTF-8"?>
<!-- Building  an "HTML" table --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="cars">
    <table>
      <xsl:attribute name="border">1</xsl:attribute>
      <xsl:attribute name="width">500</xsl:attribute>
       <xsl:apply-templates />
    </table>
  </xsl:template>

  <xsl:template match="car">
    <tr>
      <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
      <xsl:apply-templates select="@*" />
    </tr>
  </xsl:template>

  <xsl:template match="@*">
    <td><xsl:value-of select="." /></td>
  </xsl:template>
</xsl:stylesheet>
