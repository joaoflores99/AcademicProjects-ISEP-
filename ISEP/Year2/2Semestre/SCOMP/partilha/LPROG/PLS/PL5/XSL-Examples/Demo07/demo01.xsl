<?xml version="1.0" encoding="UTF-8"?>
<!-- Escaping chars in an XSL file --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="apos">
    &quot;<xsl:value-of select="." />&quot;
  </xsl:template>

  <xsl:template match="gt">
    >&quot;<xsl:value-of select="." />"&lt;
  </xsl:template>

  <xsl:template match="amp|lt|quot|other">
    &apos;<xsl:value-of select="." />'
  </xsl:template>
</xsl:stylesheet>
