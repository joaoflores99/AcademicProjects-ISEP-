<?xml version="1.0" encoding="UTF-8"?>
<!-- Templates as an alternative to for-each --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:for-each select="employees/employee">
      *<xsl:value-of select="@name" />*
      <xsl:apply-templates />
    </xsl:for-each>
  </xsl:template>

  <xsl:template match="phonenumber">
    -<xsl:value-of select="@type" />: <xsl:value-of select="." />
  </xsl:template>
</xsl:stylesheet>
