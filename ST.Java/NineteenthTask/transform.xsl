<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/map">

    <html>
      <body>

        <h2>Points</h2>

        <table border="1">
          <tr>
            <th>x</th>
            <th>y</th>
          </tr>

          <xsl:for-each select="point">
            <tr>
              <td><xsl:value-of select="x" /></td>
              <td><xsl:value-of select="y" /></td>
            </tr>
          </xsl:for-each>
          
        </table>

      </body>
    </html>

  </xsl:template>

</xsl:stylesheet>
