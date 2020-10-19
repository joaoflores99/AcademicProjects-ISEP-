<?xml version="1.0" encoding="UTF-8"?>
<!-- forcing the execution path with false() --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:for-each select="employees/employee">
      *<xsl:value-of select="@name" />*
        <xsl:for-each select="phonenumber">
          <xsl:choose>
            <xsl:when test="false()">
              Fax:    <xsl:value-of select="." />
            </xsl:when>
            <xsl:when test="false()">
              Mobile: <xsl:value-of select="." />
            </xsl:when>
            <xsl:otherwise>
              Phone:  <xsl:value-of select="." />
            </xsl:otherwise>
          </xsl:choose>
      </xsl:for-each>
    </xsl:for-each>
  </xsl:template>
</xsl:stylesheet>
