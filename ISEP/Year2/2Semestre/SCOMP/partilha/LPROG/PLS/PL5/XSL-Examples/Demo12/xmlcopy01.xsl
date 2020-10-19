<?xml version="1.0" encoding="UTF-8"?>
<!-- XML copy omitting the XML declaration --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="xml" version="1.0"  omit-xml-declaration="yes" />

  <xsl:template match="/">
    <xsl:copy-of select="." />
  </xsl:template>
</xsl:stylesheet>
