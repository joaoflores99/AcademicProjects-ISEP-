<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <!--  how to create a text file -->   
  <xsl:output method="text" />
  
  <xsl:template match="/">
    <form method="POST" action="post.asp">
      <xsl:for-each select="/cars/car">
        <INPUT type="radio" name="car" value="{@model}" />
        <xsl:value-of select="@manufacturer" />
        <xsl:value-of select="@model" /><br />
      </xsl:for-each>
    </form>
  </xsl:template>
</xsl:stylesheet>
