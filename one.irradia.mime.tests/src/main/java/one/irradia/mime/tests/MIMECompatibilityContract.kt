package one.irradia.mime.tests

import one.irradia.mime.api.MIMECompatibility
import one.irradia.mime.api.MIMEType
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

abstract class MIMECompatibilityContract {

  @Test
  fun testStrictObvious() {
    assertTrue(
      MIMECompatibility.isCompatibleStrict(
        MIMECompatibility.applicationOctetStream,
        MIMECompatibility.applicationOctetStream))
    assertTrue(
      MIMECompatibility.strict.isCompatibleWith(
        MIMECompatibility.applicationOctetStream,
        MIMECompatibility.applicationOctetStream))
  }

  @Test
  fun testStrictWithoutAttributesObvious() {
    assertTrue(
      MIMECompatibility.isCompatibleStrictWithoutAttributes(
        MIMECompatibility.applicationOctetStream,
        MIMECompatibility.applicationOctetStream))
    assertTrue(
      MIMECompatibility.strictWithoutAttributes.isCompatibleWith(
        MIMECompatibility.applicationOctetStream,
        MIMECompatibility.applicationOctetStream))
  }

  @Test
  fun testLaxObvious() {
    assertTrue(
      MIMECompatibility.isCompatibleLax(
        MIMECompatibility.applicationOctetStream,
        MIMECompatibility.applicationOctetStream))
    assertTrue(
      MIMECompatibility.lax.isCompatibleWith(
        MIMECompatibility.applicationOctetStream,
        MIMECompatibility.applicationOctetStream))
  }

  @Test
  fun testStrictIncompatible0() {
    assertTrue(
      MIMECompatibility.isCompatibleStrict(
        MIMEType("text", "plain", mapOf()),
        MIMEType("text", "plain", mapOf())))
    assertFalse(
      MIMECompatibility.isCompatibleStrict(
        MIMEType("text", "plain", mapOf()),
        MIMEType("text", "plain", mapOf(Pair("profile","other")))))
    assertFalse(
      MIMECompatibility.isCompatibleStrict(
        MIMEType("text", "plain", mapOf()),
        MIMECompatibility.applicationOctetStream))
  }

  @Test
  fun testStrictWithoutAttributesIncompatible0() {
    assertTrue(
      MIMECompatibility.isCompatibleStrictWithoutAttributes(
        MIMEType("text", "plain", mapOf(Pair("a","b"))),
        MIMEType("text", "plain", mapOf(Pair("a","b")))))
    assertFalse(
      MIMECompatibility.isCompatibleStrictWithoutAttributes(
        MIMEType("text", "plain", mapOf(Pair("a","b"))),
        MIMEType("text", "json", mapOf(Pair("a","b")))))
    assertFalse(
      MIMECompatibility.isCompatibleStrictWithoutAttributes(
        MIMEType("text", "plain", mapOf()),
        MIMECompatibility.applicationOctetStream))
  }

  @Test
  fun testLaxIncompatible0() {
    assertTrue(
      MIMECompatibility.isCompatibleLax(
        MIMEType("text", "plain", mapOf()),
        MIMEType("text", "plain", mapOf())))
    assertTrue(
      MIMECompatibility.isCompatibleLax(
        MIMEType("text", "plain", mapOf()),
        MIMEType("text", "plain", mapOf(Pair("profile","other")))))
    assertTrue(
      MIMECompatibility.isCompatibleLax(
        MIMEType("text", "plain", mapOf()),
        MIMECompatibility.applicationOctetStream))
    assertTrue(
      MIMECompatibility.isCompatibleLax(
        MIMEType(
          MIMECompatibility.applicationOctetStream.type,
          MIMECompatibility.applicationOctetStream.subtype,
          mapOf()),
        MIMEType(
          MIMECompatibility.applicationOctetStream.type,
          MIMECompatibility.applicationOctetStream.subtype,
          mapOf(Pair("profile","other")))))
  }
}