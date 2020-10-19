<?xml version="1.0" encoding="UTF-8"?>
<!-- Doing the same thing for more than one element --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="firstname|lastname">
    <xsl:value-of select="." />
  </xsl:template>

</xsl:stylesheet>