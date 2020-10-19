<?xml version="1.0" encoding="UTF-8"?>
<!-- getting data from a "parent" --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
	  <xsl:apply-templates />
 </xsl:template>

  <xsl:template match="/packinglist/top/item">
   <xsl:value-of select="parent::*/@title" />: <xsl:value-of select="." />
  </xsl:template>

  <xsl:template match="/packinglist/bottom" />
  <xsl:template match="/packinglist/shoes" />

</xsl:stylesheet>
