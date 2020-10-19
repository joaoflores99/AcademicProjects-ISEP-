<?xml version="1.0" encoding="UTF-8"?>
<!-- the meaning of "*" in xpath --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/packinglist/*/item">
     -<xsl:value-of select="text()" />
  </xsl:template>

</xsl:stylesheet>
