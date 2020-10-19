<?xml version="1.0" encoding="UTF-8"?>
<!-- Alternative to for-each and when --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:for-each select="employees/employee">
      *<xsl:value-of select="@name" />*
      <xsl:apply-templates  />
    </xsl:for-each>
  </xsl:template>

  <xsl:template match="phonenumber[@type='home']|phonenumber[@type='fax']">
	  <xsl:value-of select="@type" />: <xsl:value-of select="." />  <!-- nodes that are not matched also appear... --> 
  </xsl:template>
  <xsl:template match="phonenumber"></xsl:template>
</xsl:stylesheet>
