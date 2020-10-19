<?xml version="1.0" encoding="UTF-8"?>
<!-- Copying just some elements --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="cars">
    <xsl:copy>
      <xsl:copy-of select="car[1]|car[3]" />
    </xsl:copy>
  </xsl:template>
</xsl:stylesheet>
