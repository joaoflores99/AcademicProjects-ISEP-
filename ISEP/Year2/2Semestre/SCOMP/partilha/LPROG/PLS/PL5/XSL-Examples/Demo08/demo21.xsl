<?xml version="1.0" encoding="UTF-8"?>
<!-- Inserting a processing instruction --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 
  <xsl:template match="/">
    <xsl:processing-instruction name="xml-stylesheet">href="demo03.xsl" type="text/xml"</xsl:processing-instruction>
    <xsl:copy-of select="." />
  </xsl:template>
</xsl:stylesheet>
