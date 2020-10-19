<?xml version="1.0" encoding="UTF-8"?>
<!-- Changing just one attribute into an element --> 
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
    <xsl:copy>
      <xsl:apply-templates select="@model" />
    </xsl:copy>
  </xsl:template>

  <xsl:template match="@model">
    <xsl:element name="{name()}">
      <xsl:value-of select="." />
    </xsl:element>
  </xsl:template>
</xsl:stylesheet>
