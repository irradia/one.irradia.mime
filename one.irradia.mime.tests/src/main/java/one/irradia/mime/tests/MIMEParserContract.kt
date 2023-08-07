package one.irradia.mime.tests

import one.irradia.mime.api.MIMEParserType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

abstract class MIMEParserContract {

  abstract fun parser(text: String): MIMEParserType

  @Test
  fun textEmpty() {
    expectFailure("End-of-stream", parser(""))
  }

  @Test
  fun testTextPlain0() {
    val result = parser("text/plain").parseOrException()
    assertEquals("text", result.type)
    assertEquals("plain", result.subtype)
    assertEquals("text/plain", result.fullType)
    assertTrue(result.parameters.isEmpty())
  }

  @Test
  fun testTextPlain1() {
    val result = parser("text/plain;").parseOrException()
    assertEquals("text", result.type)
    assertEquals("plain", result.subtype)
    assertEquals("text/plain", result.fullType)
    assertTrue(result.parameters.isEmpty())
  }

  @Test
  fun testOPDS() {
    val result = parser("application/atom+xml;type=entry;profile=opds-catalog").parseOrException()
    assertEquals("application", result.type)
    assertEquals("atom+xml", result.subtype)
    assertEquals("application/atom+xml", result.fullType)
    assertTrue(result.parameters["type"] == "entry")
    assertTrue(result.parameters["profile"] == "opds-catalog")
    assertEquals(2, result.parameters.size)
  }

  @Test
  fun testTextProfile() {
    val result = parser("text/html;profile=http://librarysimplified.org/terms/profiles/streaming-media").parseOrException()
    assertEquals("text", result.type)
    assertEquals("html", result.subtype)
    assertEquals("text/html", result.fullType)
    assertTrue(result.parameters["profile"] == "http://librarysimplified.org/terms/profiles/streaming-media")
    assertEquals(1, result.parameters.size)
  }

  @Test
  fun testTextProfileQuoted() {
    val result = parser("text/html;profile=\"http://librarysimplified.org/terms/profiles/streaming-media\"").parseOrException()
    assertEquals("text", result.type)
    assertEquals("html", result.subtype)
    assertEquals("text/html", result.fullType)
    assertTrue(result.parameters["profile"] == "http://librarysimplified.org/terms/profiles/streaming-media")
    assertEquals(1, result.parameters.size)
  }

  @Test
  fun testError0() {
    expectFailure("semicolon", parser(";;;"))
  }

  @Test
  fun testError1() {
    expectFailure("semicolon", parser("text/plain;;"))
  }

  @Test
  fun testError2() {
    expectFailure("End-of-stream", parser("text/plain;x="))
  }

  @Test
  fun testError3() {
    expectFailure("semicolon", parser("text/plain;x=;"))
  }

  private fun expectFailure(message: String, parser: MIMEParserType) {
    val ex = assertThrows(Exception::class.java) {
      parser.parseOrException()
    }
    assertTrue(ex.message!!.contains(message))
  }
}
