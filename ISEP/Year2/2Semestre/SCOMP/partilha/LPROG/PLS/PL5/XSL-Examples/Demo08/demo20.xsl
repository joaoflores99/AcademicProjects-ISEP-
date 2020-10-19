<?xml version="1.0" encoding="UTF-8"?>
<!-- Generating comments --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:comment>template match="/"</xsl:comment>
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="cars">
    <xsl:comment>template match="cars"</xsl:comment>
    <xsl:copy>
      <xsl:apply-templates />
    </xsl:copy>
  </xsl:template>

  <xsl:template match="car">
    <xsl:comment>template match="car"</xsl:comment>
      <xsl:copy />
  </xsl:template>
</xsl:stylesheet>
