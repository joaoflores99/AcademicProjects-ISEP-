<?xml version="1.0" encoding="UTF-8"?>
<!--  An xpath example --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
     Today's Option
     -<xsl:value-of select="/packinglist/top/item[2]" />
     -<xsl:value-of select="/packinglist/bottom/item[3]" />
     -<xsl:value-of select="/packinglist/shoes/item[1]" />
  </xsl:template>

</xsl:stylesheet>
