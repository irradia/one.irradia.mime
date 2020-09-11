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

  fun strict(
    typeA: MIMEType,
    typeB: MIMEType
  ): Boolean =
    typeA == typeB

  /**
   * The two type values are compatible iff the type of [typeA] is equal to the type of [typeB],
   * and the subtype of [typeA] is equal to the subtyle of [typeB].
   */

  fun strictWithoutAttributes(
    typeA: MIMEType,
    typeB: MIMEType
  ): Boolean =
    typeA.type == typeB.type && typeA.subtype == typeB.subtype

  /**
   * If either [typeA] or [typeB] are [strictWithoutAttributes] compatible with
   * [applicationOctetStream], then [typeA] and [typeB] are compatible. Otherwise,
   * [typeA] and [typeB] are compatible if they are compatible according to
   * [strictWithoutAttributes].
   */

  fun lax(
    typeA: MIMEType,
    typeB: MIMEType
  ): Boolean {
    if (this.strictWithoutAttributes(typeA, this.applicationOctetStream)) {
      return true
    }
    if (this.strictWithoutAttributes(typeB, this.applicationOctetStream)) {
      return true
    }
    return this.strictWithoutAttributes(typeA, typeB)
  }

  /**
   * The [strict] function as a compatibility decision.
   */

  val strict: MIMECompatibilityType =
    object : MIMECompatibilityType {
      override fun isCompatibleWith(
        typeA: MIMEType,
        typeB: MIMEType
      ): Boolean =
        this@MIMECompatibility.strict(typeA, typeB)
    }

  /**
   * The [strictWithoutAttributes] function as a compatibility decision.
   */

  val strictWithoutAttributes: MIMECompatibilityType =
    object : MIMECompatibilityType {
      override fun isCompatibleWith(
        typeA: MIMEType,
        typeB: MIMEType
      ): Boolean =
        this@MIMECompatibility.strictWithoutAttributes(typeA, typeB)
    }

  /**
   * The [lax] function as a compatibility decision.
   */

  val lax: MIMECompatibilityType =
    object : MIMECompatibilityType {
      override fun isCompatibleWith(
        typeA: MIMEType,
        typeB: MIMEType
      ): Boolean = this@MIMECompatibility.lax(typeA, typeB)
    }
}