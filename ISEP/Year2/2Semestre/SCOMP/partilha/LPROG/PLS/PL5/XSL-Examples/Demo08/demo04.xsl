<?xml version="1.0" encoding="UTF-8"?>
<!-- creating elements "one at a time" --> 
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
      <xsl:apply-templates select="@*" />
    </car>
  </xsl:template>

  <xsl:template match="@model">
    <model><xsl:value-of select="." /></model>
  </xsl:template>

  <xsl:template match="@manufacturer">
    <manufacturer><xsl:value-of select="." /></manufacturer>
  </xsl:template>

  <xsl:template match="@year">
    <year><xsl:value-of select="." /></year>
  </xsl:template>
</xsl:stylesheet>
