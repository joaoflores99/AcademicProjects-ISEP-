<?xml version="1.0" encoding="UTF-8"?>
<!-- Replacing attributes --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="cars">
    <xsl:copy>
      <xsl:apply-templates />
    </xsl:copy>
  </xsl:template>

  <xsl:template match="car">
    <xsl:copy use-attribute-sets="year">
      <xsl:attribute name="model">Focus</xsl:attribute>    
    </xsl:copy>
  </xsl:template>

  <xsl:attribute-set name="year">
    <xsl:attribute name="year">1999</xsl:attribute>
  </xsl:attribute-set>
</xsl:stylesheet>
