<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"> <!--indica que es una archivo de transformacion xsl-->
    <xsl:template match="/"><!--match para indicar desde donde empieza a pintar datos-->
        <html>
            <head>
                <title>Películas Cartelera</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                      crossorigin="anonymous"/>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                        crossorigin="anonymous"/>
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
            </head>
            <body>
                <main class="container"></main>
                <H1>Transformación de Películas</H1>
                <div>
                    <h2>Transormaciones via XSL -FOREACH</h2>
                    <ul class="list-group">
                        <xsl:for-each select="peliculas/pelicula">
                            <li class="list-group-item animate__animated animate__bounce">
                            <xsl:attribute name="style">
                                <xsl:choose>
                                    <xsl:when test="@genero='Acción'">
                                        background-color:#3cb371;
                                        font-size:15px
                                    </xsl:when>
                                    <xsl:when test="@genero='Crimen'">
                                        background-color:#ee82ee;
                                        font-size:20px
                                    </xsl:when>
                                    <xsl:when test="@genero='Drama'">
                                        background-color:#ffa500;
                                        font-size:25px
                                    </xsl:when>
                                    <xsl:when test="@genero='Western'">
                                        background-color:#6a5acd;
                                        font-size:30px
                                    </xsl:when>
                                    <xsl:otherwise>
                                        background-color:#ff0000;
                                        font-size:10px
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:attribute>
                                <xsl:value-of select="@titulo"/>
                            </li>
                        </xsl:for-each>
                    </ul>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>