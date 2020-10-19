<?xml version="1.0" encoding="UTF-8"?> 
<!-- Example of incorrect template --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
     <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="/packinglist/top" />

  <xsl:template match="/packinglist/bottom">
     <xsl:value-of select="item" />
  </xsl:template>

  <xsl:template match="/packinglist/shoes" />

</xsl:stylesheet>
