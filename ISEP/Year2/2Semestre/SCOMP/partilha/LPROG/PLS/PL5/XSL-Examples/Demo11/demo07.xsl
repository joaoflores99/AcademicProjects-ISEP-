<?xml version="1.0" encoding="UTF-8"?>
<!-- If test example --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    employees:
    <xsl:for-each select="employees/employee">
      <xsl:value-of select="@name" />
      <xsl:if test="position() != last()">, </xsl:if>
      <xsl:if test="position() = last()">.</xsl:if>
    </xsl:for-each>
  </xsl:template>
</xsl:stylesheet>
