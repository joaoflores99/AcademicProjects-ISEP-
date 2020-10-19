<?xml version="1.0" encoding="UTF-8"?>
<!-- Templates in "cascade" --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="employee">
    <xsl:value-of select="@name" />
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="car">
    Manufacturer: <xsl:value-of select="@manufacturer" />
    Model:        <xsl:value-of select="@model" />
    Year:         <xsl:value-of select="@year" />
    Plate:        <xsl:value-of select="@plate" />
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="parkinglot">
    Parking Lot:  <xsl:value-of select="@number" />
  </xsl:template>
</xsl:stylesheet>
