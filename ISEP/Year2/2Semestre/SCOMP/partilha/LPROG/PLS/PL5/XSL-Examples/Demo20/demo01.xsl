<?xml version="1.0" encoding="UTF-8"?>
<!-- HTML Techniques --> 
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" version="4.0" encoding="UTF-8" />

  <xsl:variable name="bgcolor">#cccccc</xsl:variable>
  <xsl:variable name="altbgcolor">#ffffff</xsl:variable>

  <xsl:template match="/">
    <html>
    <body bgcolor="{$bgcolor}">
      <xsl:apply-templates />
    </body>
    </html>
  </xsl:template>

  <xsl:template match="cars">
    <table bgcolor="{$altbgcolor}" width="75%">
      <xsl:for-each select="car">
        <xsl:choose>
          <xsl:when test="position() mod 2 = 0">
            <xsl:call-template name="car">
              <xsl:with-param name="carbgcolor" select="$altbgcolor" />
            </xsl:call-template>
          </xsl:when>
          <xsl:when test="position() mod 2 = 1">
            <xsl:call-template name="car">
              <xsl:with-param name="carbgcolor" select="$bgcolor" />
            </xsl:call-template>
          </xsl:when>
          <xsl:otherwise>
            <xsl:call-template name="car" />
          </xsl:otherwise>
        </xsl:choose>
      </xsl:for-each>
    </table>
  </xsl:template>

  <xsl:template name="car">
    <xsl:param name="carbgcolor">#ffffff</xsl:param>
    <tr bgcolor="{$carbgcolor}">
      <td><xsl:value-of select="@model" /></td>
      <td><xsl:value-of select="@manufacturer" /></td>
      <td><xsl:value-of select="@year" /></td>
    </tr>
  </xsl:template>
</xsl:stylesheet>
