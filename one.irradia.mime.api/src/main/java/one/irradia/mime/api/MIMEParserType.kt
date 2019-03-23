package one.irradia.mime.api

/**
 * The type of parsers for RFC2045 MIME type strings.
 */

interface MIMEParserType {

  /**
   * Execute the parser, returning a status result.
   */

  fun parse(): MIMEParserResult

  /**
   * Execute the parser, returning either the parsed value or raising an exception on errors.
   */

  @Throws(Exception::class)
  fun parseOrException(): MIMEType {
    val result = this.parse()
    return when (result) {
      is MIMEParserResult.Success -> result.type
      is MIMEParserResult.Failure -> throw result.exception
    }
  }
}
