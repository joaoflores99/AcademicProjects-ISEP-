<?xml version="1.0" encoding="UTF-8"?>
<!-- Another way of generating a "table"--> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="cars">
    <xsl:element name="table">
      <xsl:apply-templates />
    </xsl:element>
  </xsl:template>

  <xsl:template match="car">
    <xsl:element name="tr">
      <xsl:apply-templates select="@*" />
    </xsl:element>
  </xsl:template>

  <xsl:template match="@*">
    <xsl:element name="td">
      <xsl:value-of select="." />
    </xsl:element>
  </xsl:template>
</xsl:stylesheet>
