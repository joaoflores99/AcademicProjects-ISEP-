<?xml version="1.0" encoding="UTF-8"?>
<!-- Ancestors in an xpath expression --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/packinglist/top">
      -<xsl:value-of select="item[1]/ancestor::*[1]/@title" />
      -<xsl:value-of select="item[1]/ancestor::packinglist/bottom/@title" />
      -<xsl:value-of select="item[1]/ancestor::*"/> 
    </xsl:template>


  <xsl:template match="/packinglist/bottom" />
  <xsl:template match="/packinglist/shoes" />

</xsl:stylesheet>
