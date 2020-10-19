<?xml version="1.0" encoding="UTF-8"?> 
<!--  "*" can be used togeter with indexing --> 

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
     Shoes of the Day:
     <xsl:value-of select="/packinglist/*[3]/item[2]" />
     Price: <xsl:value-of select="/packinglist/*[3]/item[2]/@*[2]" />
  </xsl:template>

</xsl:stylesheet>
