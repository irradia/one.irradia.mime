package one.irradia.mime.api

/**
 * The result of parsing a MIME type from a stream.
 */

sealed class MIMEParserResult {

  /**
   * The position of the result.
   */

  abstract val position: Int

  /**
   * Parsing succeeded.
   */

  data class Success(
    override val position: Int,
    val type: MIMEType)
    : MIMEParserResult()

  /**
   * Parsing failed. The given exception describes the error.
   */

  data class Failure(
    override val position: Int,
    val exception: Exception)
    : MIMEParserResult()

}
