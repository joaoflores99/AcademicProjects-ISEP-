<?xml version="1.0" encoding="UTF-8"?>
<!-- Converting attributes into elements --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="*">
    <xsl:element name="{name()}">
      <xsl:apply-templates select="@*" />
      <xsl:apply-templates />
    </xsl:element>
  </xsl:template>

  <xsl:template match="@*">
    <xsl:element name="{name()}">
      <xsl:value-of select="." />
    </xsl:element>
  </xsl:template>

  <xsl:template match="text()">
    <xsl:value-of select="." />
  </xsl:template>
</xsl:stylesheet>
