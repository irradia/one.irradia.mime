package one.irradia.mime.tests.local

import one.irradia.mime.api.MIMELexerType
import one.irradia.mime.tests.MIMELexerContract
import one.irradia.mime.vanilla.MIMELexer

class MIMELexerTest : MIMELexerContract() {

  override fun lexer(text: String): MIMELexerType {
    return MIMELexer.create(text)
  }

}
