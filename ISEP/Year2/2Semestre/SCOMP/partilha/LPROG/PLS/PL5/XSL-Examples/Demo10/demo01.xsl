<?xml version="1.0" encoding="UTF-8"?>
<!-- for-each example --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <cars>
      <xsl:for-each select="/cars/car[@year='1999']">
        <xsl:copy-of select="." />
      </xsl:for-each>
      <xsl:for-each select="/cars/car[@year='2000']">
        <xsl:copy-of select="." />
      </xsl:for-each>
    </cars>
  </xsl:template>
</xsl:stylesheet>
