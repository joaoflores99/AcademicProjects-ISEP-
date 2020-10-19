<?xml version="1.0" encoding="UTF-8"?>
<!-- This template works as expected --> 
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="tools">
    My tools
    <xsl:apply-templates />     <!-- Very important --> 
  </xsl:template> 

  <xsl:template match="tool">This is one of my tools.</xsl:template>

</xsl:stylesheet>
