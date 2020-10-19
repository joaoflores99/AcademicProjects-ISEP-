<?xml version="1.0" encoding="UTF-8"?>
<!--  getting the contents of "/packinglist/shoes" --> 
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:value-of select="/packinglist/shoes" />
  </xsl:template>

</xsl:stylesheet>
