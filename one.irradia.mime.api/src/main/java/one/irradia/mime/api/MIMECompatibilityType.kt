package one.irradia.mime.api

/**
 * A decision procedure for determining if one MIME type is structurally compatible with
 * another.
 */

interface MIMECompatibilityType {

  /**
   * Determine if [typeA] is compatible with [typeB]. That is, anywhere that requires data
   * of type [typeA] should be able to accept data of type [typeB] and vice versa.
   *
   * @return `true` if [typeA] is compatible with [typeB]
   */

  fun isCompatibleWith(
    typeA: MIMEType,
    typeB: MIMEType
  ): Boolean
}
