package one.irradia.mime.tests.device

import android.support.test.filters.MediumTest
import android.support.test.runner.AndroidJUnit4
import one.irradia.mime.api.MIMELexerType
import one.irradia.mime.tests.MIMELexerContract
import one.irradia.mime.vanilla.MIMELexer
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class MIMELexerTest : MIMELexerContract() {

  override fun lexer(text: String): MIMELexerType {
    return MIMELexer.create(text)
  }

}
