<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
                xmlns:ns="http://www.dei.isep.ipp.pt/lprog"
                exclude-result-prefixes="ns"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output encoding="UTF-16"/>
    <xsl:template match="/">
        <html>
            <head>
               <title>Relatório de Linguagens e Programação</title>
            </head>
            <body text="black" link="#4d1a00">
                <div align="center">
                    <font face="Berlin Sans FB">
                        <img src="https://www.inovinter.pt/wp-content/uploads/2017/05/header_dei_isep-758x220.jpg"/>
                        <br></br>
                        <br></br>
                        <t1 id="titulo1">
                            <font size="8">
                                <b>Relatório de Linguagens e Programação</b>
                            </font>
                        </t1>
                        <br></br>
                        <br></br>
                        <font size="5" color="black">
                            <font color="black"></font>
                            <i>
                                <xsl:value-of select="ns:relatório/ns:páginaRosto/ns:tema"/>
                            </i>
                        </font>
                        <br></br>
                        <br></br>
                        <font size="6" >
                            <xsl:value-of select="ns:relatório/ns:páginaRosto/ns:disciplina/ns:tema"/>
                            <font size="5">
                                <b>
                                    <xsl:value-of select="ns:relatório/ns:páginaRosto/ns:disciplina/ns:sigla"/>
                                    <xsl:text>  </xsl:text>
                                </b>
                            </font>

                            <br></br>

                            <font size="5" >Ano Letivo: <b> 2018/2019</b></font>

                            <br></br>
                            <font size="5">
                                <font >Turma: </font>
                                <xsl:value-of select="ns:relatório/ns:páginaRosto/ns:turma"/>
                                <br></br>
                            </font>
                            <font size="5">
                                <font>
                                    <b>Autores:  </b>
                                </font>
                                <br></br>
                                <xsl:for-each select="ns:relatório/ns:páginaRosto/ns:autor">
                                    <xsl:value-of select="ns:nome "/>
                                    (
                                    <xsl:value-of select="ns:número"/>
                                    )
                                    <br></br>
                                </xsl:for-each>
                            </font>
                            <font size="5">
                                <b>Docentes: </b>
                                <br></br>
                                <xsl:for-each select="ns:relatório/ns:páginaRosto/ns:professor">

                                    <xsl:value-of select="@sigla"/>
                                    <xsl:text> - </xsl:text>
                                    <xsl:value-of select="@tipoAula"/>


                                    <br></br>
                                </xsl:for-each>
                            </font>
                            <p></p>
                            <div style="background-color: #D8D8D8;">
                            <font size="6" color="#0d0d0d">
                                <b>Índice</b>
                            </font>
                            <p></p>
                            <font size="5">
                                <a href="#introducao">Introdução</a>
                                <br></br>
                                <a href="#analise">Análise</a>
                                <br></br>
                                <a href="#linguagem">Linguagem</a>
                                <br></br>
                                <a href="#transformacoes">Transformações</a>
                                <br></br>
                                <a href="#conclusao">Conclusão</a>
                                <br></br>
                                <a href="#referencias">Referências </a>
                            </font>
                            <p></p>
                            </div>
                            <div id="introducao">
                                <t1>
                                    <font size="5" >
                                        <b>Introdução</b>
                                    </font>
                                </t1>
                                <font size="5">
                                    <p align="justify">
                                        <span style="margin-left:2em">
                                            <xsl:value-of select="ns:relatório/ns:corpo/ns:introdução/ns:parágrafo[1]"/>
                                        </span>
                                        <br/>
                                        <span style="margin-left:2em">
                                            <xsl:value-of select="ns:relatório/ns:corpo/ns:introdução/ns:parágrafo[2]"/>
                                        </span>
                                        <br/>
                                        <span style="margin-left:2em">
                                            <xsl:value-of select="ns:relatório/ns:corpo/ns:introdução/ns:parágrafo[3]"/>
                                        </span>
                                        <br/>
                                        <br/>
                                        <span style="margin-left:2em">
                                            <xsl:value-of select="ns:relatório/ns:corpo/ns:introdução/ns:parágrafo[4]"/>
                                        </span>
                                        <br/>
                                        <br/>
                                        <span style="margin-left:2em">
                                            <xsl:value-of select="ns:relatório/ns:corpo/ns:introdução/ns:parágrafo[5]"/>
                                        </span>
                                        <br/>
                                        <br/>
                                        <span style="margin-left:2em">
                                            <xsl:value-of select="ns:relatório/ns:corpo/ns:introdução/ns:parágrafo[6]"/>
                                        </span>
                                        <br/>
                                        <br/>
                                        <span style="margin-left:2em">
                                            <xsl:value-of select="ns:relatório/ns:corpo/ns:introdução/ns:parágrafo[7]"/>
                                        </span>
                                        <br/>
                                    </p>
                                </font>
                            </div>
                            <div id="analise" style="background-color: #D8D8D8;">
                                <t1>
                                    <font size="5" color="#0d0d0d">
                                        <b>Análise</b>
                                    </font>
                                </t1>
                                <font size="5" color="#343434">
                                    <p align="justify">
                                        <span style="margin-left:2em">
                                            <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:análise/ns:parágrafo[1]"/>
                                        </span>
                                        <br/>
                                        <span style="margin-left:2em">
                                            <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:análise/ns:parágrafo[2]"/>
                                        </span>
                                        <br/>
                                        <span style="margin-left:2em">
                                            <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:análise/ns:parágrafo[3]"/>
                                        </span>
                                        <br/>
                                        <br></br>
                                    </p>
                                </font>
                            </div>
                            <div id="linguagem">
                            <t1>
                                <font size="5" color="#0d0d0d">
                                    <b>Linguagem</b>
                                </font>
                            </t1>
                            <font size="5" color="#343434">
                                <p align="justify">
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:linguagem/ns:parágrafo[1]"/>
                                    </span>
                                    <br/>
                                </p>
                            </font>
                            </div>
                            <div id="transformacoes" style="background-color: #D8D8D8;">
                            <t1>
                                <font size="5" color="#0d0d0d">
                                    <b>Transformações</b>
                                </font>
                            </t1>
                            <font size="5" color="#343434">
                                <p align="justify">
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[1]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[2]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[3]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[4]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[5]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[6]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[7]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[8]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[9]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[10]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[11]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[12]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[13]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[14]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[15]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[16]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:outrasSecções/ns:transformações/ns:parágrafo[17]"/>
                                    </span>
                                    <br></br>
                                </p>
                                <br></br>
                            </font>
                            </div>
                            <div id="conclusao">
                            <t1>
                                <font size="5" color="#0d0d0d">
                                    <b>Conclusão</b>
                                </font>
                            </t1>
                            <font size="5" color="#343434">
                                <p align="justify">
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:conclusão/ns:parágrafo[1]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:conclusão/ns:parágrafo[2]"/>
                                    </span>
                                    <br/>
                                    <span style="margin-left:2em">
                                        <xsl:value-of select="ns:relatório/ns:corpo/ns:conclusão/ns:parágrafo[3]"/>
                                    </span>
                                    <br/>
                                    <br/>
                                     <span style="margin-left:2em">
                                          <xsl:value-of select="ns:relatório/ns:corpo/ns:conclusão/ns:parágrafo[4]"/>
                                          </span>
                                     <br/>
                                 <br/>
                                                                     <span style="margin-left:2em">
                                                                         <xsl:value-of select="ns:relatório/ns:corpo/ns:conclusão/ns:parágrafo[5]"/>
                                                                     </span>
                                                                     <br/>
                                                                     <span style="margin-left:2em">
                                                                         <xsl:value-of select="ns:relatório/ns:corpo/ns:conclusão/ns:parágrafo[6]"/>
                                                                     </span>
                                                                     <br/>
                                                                     <br/>
                                                                      <span style="margin-left:2em">
                                                                           <xsl:value-of select="ns:relatório/ns:corpo/ns:conclusão/ns:parágrafo[7]"/>
                                                                           </span>
                                                                      <br/>

                                </p>
                                <br></br>
                            </font>
                            </div>
                            <div id="referencias" style="background-color: #D8D8D8;">
                            <t1>
                                <font size="5" color="#0d0d0d">
                                    <b>Referências</b>
                                </font>
                            </t1>
                            <font size="3" color="#343434">
                                <p align="center">
                                    <xsl:for-each select="ns:relatório/ns:corpo/ns:referências/ns:refWeb">
                                        <p>
                                            <xsl:value-of select="ns:descrição" />
                                            <br></br>
                                            URL: <a>
                                                <xsl:attribute name="href">
                                                    <xsl:value-of select="ns:URL" />
                                                </xsl:attribute>
                                                <xsl:value-of select="ns:URL" />
                                                <br></br>
                                            </a>
                                            <br></br>
                                            Consultado em: <xsl:value-of select="ns:consultadoEm" />
                                        </p>
                                    </xsl:for-each>
                                </p>
                                <br></br>
                            </font>
                            </div>
                        </font>
                    </font>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>


