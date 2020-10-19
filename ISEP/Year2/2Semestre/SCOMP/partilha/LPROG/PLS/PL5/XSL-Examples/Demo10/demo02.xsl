<?xml version="1.0" encoding="UTF-8"?>
<!-- For-each and when example --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <cars>
      <xsl:for-each select="/cars/car">
        <xsl:copy>
          <xsl:copy-of select="@*" />
          <xsl:choose>
            <xsl:when test="@manufacturer='Ford'">
		    <xsl:attribute name="country">UK</xsl:attribute>
            </xsl:when>
            <xsl:when test="@manufacturer='Honda' or @manufacturer='Toyota'">
              <xsl:attribute name="country">Japan</xsl:attribute>
            </xsl:when>
            <xsl:when test="@manufacturer='Volkswagen' or @manufacturer='Opel'">
              <xsl:attribute name="country">Germany</xsl:attribute>
            </xsl:when>
          </xsl:choose>
        </xsl:copy>
      </xsl:for-each>
    </cars>
  </xsl:template>
</xsl:stylesheet>
