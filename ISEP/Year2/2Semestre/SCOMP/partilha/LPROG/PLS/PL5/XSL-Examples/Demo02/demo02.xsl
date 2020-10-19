<?xml version="1.0" encoding="UTF-8"?>
<!--  A series of templates --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
	  <xsl:apply-templates /> <!-- Important --> 
  </xsl:template>

<xsl:template match="packinglist/top" >
    Top:
</xsl:template>

<xsl:template match="packinglist/bottom">
     Bottom:
     <xsl:apply-templates />   <!-- Important --> 
</xsl:template>

<xsl:template match="packinglist/shoes">
    Shoes:
</xsl:template>

<xsl:template match="item">
    -<xsl:value-of select="text()" />
</xsl:template>

</xsl:stylesheet>
