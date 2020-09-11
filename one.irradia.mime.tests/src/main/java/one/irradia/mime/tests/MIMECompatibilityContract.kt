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
      MIMECompatibility.strict(
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
      MIMECompatibility.strictWithoutAttributes(
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
      MIMECompatibility.lax(
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
      MIMECompatibility.strict(
        MIMEType("text", "plain", mapOf()),
        MIMEType("text", "plain", mapOf())))
    assertFalse(
      MIMECompatibility.strict(
        MIMEType("text", "plain", mapOf()),
        MIMEType("text", "plain", mapOf(Pair("profile","other")))))
    assertFalse(
      MIMECompatibility.strict(
        MIMEType("text", "plain", mapOf()),
        MIMECompatibility.applicationOctetStream))
  }

  @Test
  fun testStrictWithoutAttributesIncompatible0() {
    assertTrue(
      MIMECompatibility.strictWithoutAttributes(
        MIMEType("text", "plain", mapOf(Pair("a","b"))),
        MIMEType("text", "plain", mapOf(Pair("a","b")))))
    assertFalse(
      MIMECompatibility.strictWithoutAttributes(
        MIMEType("text", "plain", mapOf(Pair("a","b"))),
        MIMEType("text", "json", mapOf(Pair("a","b")))))
    assertFalse(
      MIMECompatibility.strictWithoutAttributes(
        MIMEType("text", "plain", mapOf()),
        MIMECompatibility.applicationOctetStream))
  }

  @Test
  fun testLaxIncompatible0() {
    assertTrue(
      MIMECompatibility.lax(
        MIMEType("text", "plain", mapOf()),
        MIMEType("text", "plain", mapOf())))
    assertTrue(
      MIMECompatibility.lax(
        MIMEType("text", "plain", mapOf()),
        MIMEType("text", "plain", mapOf(Pair("profile","other")))))
    assertTrue(
      MIMECompatibility.lax(
        MIMEType("text", "plain", mapOf()),
        MIMECompatibility.applicationOctetStream))
    assertTrue(
      MIMECompatibility.lax(
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