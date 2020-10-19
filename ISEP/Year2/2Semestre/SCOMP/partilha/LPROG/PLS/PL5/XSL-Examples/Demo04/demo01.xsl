<?xml version="1.0" encoding="UTF-8"?>
<!-- Applying different templates --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
Models:
    <xsl:apply-templates select="cars/car/model" />
Manufacturers:
    <xsl:apply-templates select="cars/car/manufacturer" />
Years:
    <xsl:apply-templates select="cars/car/year" />
  </xsl:template>

  <xsl:template match="model">
    -<xsl:value-of select="." />
  </xsl:template>

  <xsl:template match="manufacturer">
    -<xsl:value-of select="." />
  </xsl:template>

  <xsl:template match="year">
    -<xsl:value-of select="." />
  </xsl:template>
</xsl:stylesheet>
