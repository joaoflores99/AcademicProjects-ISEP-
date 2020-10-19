<?xml version="1.0" encoding="UTF-8"?>
<!-- What happens if we use a wrong xpath expression --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
     Shoes of the Day:
     <xsl:value-of select="/packinglist/shoes/item[2]" />
     Price: <xsl:value-of select="/packinglist/shoes/item[2]/price" />
  </xsl:template>

</xsl:stylesheet>
