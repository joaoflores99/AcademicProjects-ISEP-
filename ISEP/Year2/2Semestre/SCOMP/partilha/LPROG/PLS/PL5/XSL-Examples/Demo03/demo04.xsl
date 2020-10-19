<?xml version="1.0" encoding="UTF-8"?>
<!-- How to use call-template --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="car">
    <xsl:call-template name="car" />
  </xsl:template>

  <xsl:template name="car">
    Manufacturer: <xsl:value-of select="@manufacturer" />
    Model:        <xsl:value-of select="@model" />
    Year:         <xsl:value-of select="@year" />
  </xsl:template>
</xsl:stylesheet>
