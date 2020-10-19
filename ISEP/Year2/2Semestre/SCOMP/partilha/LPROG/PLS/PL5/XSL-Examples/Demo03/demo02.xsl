<?xml version="1.0" encoding="UTF-8"?>
<!-- A template that works for attributes or elements --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
Models:
    <xsl:apply-templates select="//model|//car/@model" />
Manufacturers:
    <xsl:apply-templates select="//manufacturer|//car/@manufacturer" />
Years:
    <xsl:apply-templates select="//year|//car/@year" />
  </xsl:template>

  <xsl:template match="model|@model">
    -<xsl:value-of select="." />
  </xsl:template>

  <xsl:template match="manufacturer|@manufacturer">
    -<xsl:value-of select="." />
  </xsl:template>

  <xsl:template match="year|@year">
    -<xsl:value-of select="." />
  </xsl:template>
</xsl:stylesheet>
