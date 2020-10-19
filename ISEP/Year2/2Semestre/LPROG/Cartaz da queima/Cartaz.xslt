<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>

	<xsl:template match="/cartaz">
		<html>
			<head>
				<link rel="stylesheet" type="text/css" href="Cartaz.css"/>
			</head>
			<body>
				<h1 align="center">
					Cartaz Queima Porto<br/>
					<xsl:value-of select="@ano"/>
				</h1>
				<xsl:apply-templates select="noite" />
			</body>
		</html>
	</xsl:template>
	
	<xsl:template match="noite">
		<table>
			<tr><th colspan="2"><xsl:value-of select="@de"/></th></tr>
			<xsl:apply-templates select="artista"/>
		</table>
	</xsl:template>

	<xsl:template match="artista">
		<tr>
			<td class="hora"><xsl:value-of select="@horario"/></td>
			<td class="nome"><xsl:apply-templates select="@nome"/></td>
		</tr>
	</xsl:template>
	
	<xsl:template match="@nome[../@url]">
		<xsl:element name="a">
			<xsl:attribute name="href"><xsl:value-of select="../@url"/></xsl:attribute>
			<xsl:attribute name="target">_blank</xsl:attribute>
			<xsl:value-of select="."/>
		</xsl:element>
	</xsl:template>

</xsl:stylesheet>