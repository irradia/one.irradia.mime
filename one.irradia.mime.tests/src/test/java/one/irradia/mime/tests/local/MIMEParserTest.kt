package one.irradia.mime.tests.local

import one.irradia.mime.api.MIMEParserType
import one.irradia.mime.tests.MIMEParserContract
import one.irradia.mime.vanilla.MIMEParser

class MIMEParserTest : MIMEParserContract() {

  override fun parser(text: String): MIMEParserType {
    return MIMEParser.create(text)
  }

}
