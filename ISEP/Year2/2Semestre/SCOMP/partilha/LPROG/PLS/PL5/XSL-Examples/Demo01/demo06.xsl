<?xml version="1.0" encoding="UTF-8"?>
<!-- Getting the contents of an element --> 
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="tools">
    My tools:
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="tool">
	  <xsl:value-of select="text()" />  <!-- Like this --> 
  </xsl:template>

</xsl:stylesheet>
