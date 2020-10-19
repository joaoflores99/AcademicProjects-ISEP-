<?xml version="1.0" encoding="UTF-8"?>
<!-- How to do templates that fit more tyhan one XML structure --> 

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="employee">
    <xsl:value-of select="@name" />
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="cars/car">   <!-- on the previous file --> 
    Manufacturer: <xsl:value-of select="@manufacturer" />
    Model:        <xsl:value-of select="@model" />
    Year:         <xsl:value-of select="@year" />
  </xsl:template>

  <xsl:template match="employee/car">
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
