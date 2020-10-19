<?xml version="1.0" encoding="UTF-8"?>
<!-- Creating elements with an "extracted" name--> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="cars">
    <caryears>
      <xsl:apply-templates />
    </caryears>
  </xsl:template>

  <xsl:template match="car">
    <xsl:element name="{@model}">
      <xsl:value-of select="@year" />
    </xsl:element>
  </xsl:template>
</xsl:stylesheet>
