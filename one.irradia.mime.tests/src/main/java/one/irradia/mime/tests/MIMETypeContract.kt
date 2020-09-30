package one.irradia.mime.tests

import one.irradia.mime.vanilla.MIMEParser
import org.junit.Assert
import org.junit.Test

abstract class MIMETypeContract {

  @Test
  fun testEquals() {
    Assert.assertEquals(
      MIMEParser.parseRaisingException("application/atom+xml;profile=opds-catalog"),
      MIMEParser.parseRaisingException("application/atom+xml;profile=opds-catalog")
    )
    Assert.assertEquals(
      MIMEParser.parseRaisingException("application/atom+xml;relation=entry;profile=opds-catalog"),
      MIMEParser.parseRaisingException("application/atom+xml;profile=opds-catalog;relation=entry")
    )
    Assert.assertNotEquals(
      MIMEParser.parseRaisingException("application/atom+xml;profile=opds-catalog"),
      MIMEParser.parseRaisingException("application/atom+xml")
    )
    Assert.assertNotEquals(
      MIMEParser.parseRaisingException("application/atom+xml;profile=opds-catalog"),
      MIMEParser.parseRaisingException("application/atom+xml;profile=something-else")
    )
  }
}