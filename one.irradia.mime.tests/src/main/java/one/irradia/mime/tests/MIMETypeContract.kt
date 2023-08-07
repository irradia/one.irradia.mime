package one.irradia.mime.tests

import one.irradia.mime.vanilla.MIMEParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

abstract class MIMETypeContract {

  @Test
  fun testEquals() {
    assertEquals(
      MIMEParser.parseRaisingException("application/atom+xml;profile=opds-catalog"),
      MIMEParser.parseRaisingException("application/atom+xml;profile=opds-catalog")
    )
    assertEquals(
      MIMEParser.parseRaisingException("application/atom+xml;relation=entry;profile=opds-catalog"),
      MIMEParser.parseRaisingException("application/atom+xml;profile=opds-catalog;relation=entry")
    )
    assertNotEquals(
      MIMEParser.parseRaisingException("application/atom+xml;profile=opds-catalog"),
      MIMEParser.parseRaisingException("application/atom+xml")
    )
    assertNotEquals(
      MIMEParser.parseRaisingException("application/atom+xml;profile=opds-catalog"),
      MIMEParser.parseRaisingException("application/atom+xml;profile=something-else")
    )
  }
}
