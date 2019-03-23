package one.irradia.mime.tests.device

import android.support.test.filters.MediumTest
import android.support.test.runner.AndroidJUnit4
import one.irradia.mime.api.MIMEParserType
import one.irradia.mime.tests.MIMEParserContract
import one.irradia.mime.vanilla.MIMEParser
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class MIMEParserTest : MIMEParserContract() {

  override fun parser(text: String): MIMEParserType {
    return MIMEParser.create(text)
  }

}
