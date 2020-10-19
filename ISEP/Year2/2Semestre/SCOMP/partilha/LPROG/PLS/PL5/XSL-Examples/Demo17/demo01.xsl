<?xml version="1.0" encoding="UTF-8"?>
<!-- XSL variables and arithmetic --> 
<xsl:stylesheet version="1.0" 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="text" />

  <xsl:variable name="week">7</xsl:variable>

  <xsl:variable name="weektop"
       select="/packinglist/top/item[position() =
       ((($week - 1) mod count(/packinglist/top/item)) + 1)]" />

  <xsl:variable name="weekbottom"
       select="/packinglist/bottom/item[position() =
       ((($week - 1) mod count(/packinglist/bottom/item)) + 1)]" />

  <xsl:variable name="weekshoes"
       select="/packinglist/shoes/item[position() =
       ((($week - 1) mod count(/packinglist/shoes/item)) + 1)]" />

  <xsl:template match="/">
    <xsl:text>This week's promos:&#xA;</xsl:text>
    <xsl:text>- </xsl:text><xsl:value-of select="$weektop" />
    <xsl:text> $</xsl:text><xsl:value-of select="$weektop/@price" />
    <xsl:text>&#xA;- </xsl:text><xsl:value-of select="$weekbottom" />
    <xsl:text> $</xsl:text><xsl:value-of select="$weekbottom/@price" />
    <xsl:text>&#xA;- </xsl:text><xsl:value-of select="$weekshoes" />
    <xsl:text> $</xsl:text><xsl:value-of select="$weekshoes/@price" />
  </xsl:template>
</xsl:stylesheet>
