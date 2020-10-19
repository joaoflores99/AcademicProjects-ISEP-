<?xml version="1.0" encoding="UTF-8"?>
<!-- A template that works for attributes and elements -->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="car">
    <xsl:apply-templates select="*|@*" />
  </xsl:template>

  <xsl:template match="model|@model">
    Model:        <xsl:value-of select="." />
  </xsl:template>

  <xsl:template match="manufacturer|@manufacturer">
    Manufacturer: <xsl:value-of select="." />
  </xsl:template>

  <xsl:template match="year|@year">
    Year:         <xsl:value-of select="." />
  </xsl:template>
</xsl:stylesheet>
