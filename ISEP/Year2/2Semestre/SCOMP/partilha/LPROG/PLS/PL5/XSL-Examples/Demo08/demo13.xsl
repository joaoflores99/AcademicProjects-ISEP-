<?xml version="1.0" encoding="UTF-8"?>
<!-- Attribute sets --> 

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="cars">
    <xsl:element name="table" use-attribute-sets="tableprop">
       <xsl:apply-templates />
    </xsl:element>
  </xsl:template>

  <xsl:template match="car">
    <xsl:element name="tr" use-attribute-sets="rowprop">
      <xsl:apply-templates select="@*" />
    </xsl:element>
  </xsl:template>

  <xsl:template match="@*">
    <td><xsl:value-of select="." /></td>
  </xsl:template>

  <xsl:attribute-set name="tableprop">
    <xsl:attribute name="border">1</xsl:attribute>
    <xsl:attribute name="width">500</xsl:attribute>
  </xsl:attribute-set>

  <xsl:attribute-set name="rowprop">
    <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
  </xsl:attribute-set>
</xsl:stylesheet>
