<?xml version="1.0" encoding="UTF-8"?>
<!-- Converting elements to attributes --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="cars">
    <cars>
      <xsl:apply-templates />
    </cars>
  </xsl:template>

  <xsl:template match="car">
    <car>
      <xsl:apply-templates />
    </car>
  </xsl:template>

  <xsl:template match="*">
    <xsl:attribute name="{name()}">
      <xsl:value-of select="." />
    </xsl:attribute>
  </xsl:template>
</xsl:stylesheet>
