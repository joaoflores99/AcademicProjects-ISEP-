<?xml version="1.0" encoding="UTF-8"?>
<!-- if inside a for-each --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:for-each select="employees/employee/phonenumber">
      <xsl:if test="position()=2">
        *<xsl:value-of select="../@name" />*
        <xsl:value-of select="@type" />: <xsl:value-of select="." />
      </xsl:if>
    </xsl:for-each>
  </xsl:template>
</xsl:stylesheet>