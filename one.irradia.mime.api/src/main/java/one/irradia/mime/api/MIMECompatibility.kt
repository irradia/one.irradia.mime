package one.irradia.mime.api

/**
 * Functions for deciding if MIME types are compatible with each other.
 */

object MIMECompatibility {

  /**
   * The utterly generic "stream of bytes" MIME type. Typically considered to be compatible
   * with everything.
   */

  val applicationOctetStream =
    MIMEType("application", "octet-stream", mapOf())

  /**
   * The two type values are compatible iff their values are exactly equal, including all
   * attributes.
   */

  fun isCompatibleStrict(
    typeA: MIMEType,
    typeB: MIMEType
  ): Boolean =
    typeA == typeB

  /**
   * The two type values are compatible iff the type of [typeA] is equal to the type of [typeB],
   * and the subtype of [typeA] is equal to the subtyle of [typeB].
   */

  fun isCompatibleStrictWithoutAttributes(
    typeA: MIMEType,
    typeB: MIMEType
  ): Boolean =
    typeA.type == typeB.type && typeA.subtype == typeB.subtype

  /**
   * If either [typeA] or [typeB] are [isCompatibleStrictWithoutAttributes] compatible with
   * [applicationOctetStream], then [typeA] and [typeB] are compatible. Otherwise,
   * [typeA] and [typeB] are compatible if they are compatible according to
   * [isCompatibleStrictWithoutAttributes].
   */

  fun isCompatibleLax(
    typeA: MIMEType,
    typeB: MIMEType
  ): Boolean {
    if (this.isCompatibleStrictWithoutAttributes(typeA, this.applicationOctetStream)) {
      return true
    }
    if (this.isCompatibleStrictWithoutAttributes(typeB, this.applicationOctetStream)) {
      return true
    }
    return this.isCompatibleStrictWithoutAttributes(typeA, typeB)
  }

  /**
   * The [isCompatibleStrict] function as a compatibility decision.
   */

  val strict: MIMECompatibilityType =
    object : MIMECompatibilityType {
      override fun isCompatibleWith(
        typeA: MIMEType,
        typeB: MIMEType
      ): Boolean =
        this@MIMECompatibility.isCompatibleStrict(typeA, typeB)
    }

  /**
   * The [isCompatibleStrictWithoutAttributes] function as a compatibility decision.
   */

  val strictWithoutAttributes: MIMECompatibilityType =
    object : MIMECompatibilityType {
      override fun isCompatibleWith(
        typeA: MIMEType,
        typeB: MIMEType
      ): Boolean =
        this@MIMECompatibility.isCompatibleStrictWithoutAttributes(typeA, typeB)
    }

  /**
   * The [isCompatibleLax] function as a compatibility decision.
   */

  val lax: MIMECompatibilityType =
    object : MIMECompatibilityType {
      override fun isCompatibleWith(
        typeA: MIMEType,
        typeB: MIMEType
      ): Boolean = this@MIMECompatibility.isCompatibleLax(typeA, typeB)
    }
}