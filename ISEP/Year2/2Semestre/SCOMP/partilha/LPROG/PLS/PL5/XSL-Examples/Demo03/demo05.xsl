<?xml version="1.0" encoding="UTF-8"?>
<!-- Applying two different templates to the same elements -->  
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates mode="index" />
    <xsl:apply-templates mode="info" />
  </xsl:template>

  <xsl:template match="car" mode="index">
    <xsl:value-of select="@model" />
  </xsl:template>

  <xsl:template match="car" mode="info">
    <xsl:value-of select="@model" />
    Manufacturer: <xsl:value-of select="@manufacturer" />
    Year:         <xsl:value-of select="@year" />
  </xsl:template>

</xsl:stylesheet>
