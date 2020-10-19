<?xml version="1.0" encoding="UTF-8"?>
<!-- Template match with "*" --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="cars">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="*">		<!-- Now it matches  --> 
    xyz
  </xsl:template>

  <xsl:template match="ccc">    <!-- Does not match - wrong string -->  
	  Model: <xsl:value-of select="@model" /> 
  </xsl:template>

</xsl:stylesheet>
