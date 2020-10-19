<?xml version="1.0" encoding="UTF-8"?>
<!-- Applying different templates on the same elements --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="cars">
Models:
	    <xsl:apply-templates mode="models" />
Manufacturers:
	    <xsl:apply-templates mode="manufacturers" />
Years:
	    <xsl:apply-templates mode="years" />
  </xsl:template>

  <xsl:template match="car" mode="models">
    -<xsl:value-of select="@model" />
  </xsl:template>

  <xsl:template match="car" mode="manufacturers">
    -<xsl:value-of select="@manufacturer" />
  </xsl:template>

  <xsl:template match="car" mode="years">
    -<xsl:value-of select="@year" />
  </xsl:template>
</xsl:stylesheet>
