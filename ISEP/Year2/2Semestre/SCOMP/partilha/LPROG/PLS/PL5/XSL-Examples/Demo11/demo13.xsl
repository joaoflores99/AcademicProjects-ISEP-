<?xml version="1.0" encoding="UTF-8"?>
<!-- A more complex when --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:for-each select="employees/employee">
      *<xsl:value-of select="@name" />*
        <xsl:for-each select="phonenumber">
          <xsl:choose>
            <xsl:when test="@type='fax'">
              Fax:   <xsl:value-of select="." />
            </xsl:when>
            <xsl:when test="@type='mobile' or @type='home'">
              Phone: <xsl:value-of select="." />
            </xsl:when>
          </xsl:choose>
      </xsl:for-each>
    </xsl:for-each>
  </xsl:template>
</xsl:stylesheet>
