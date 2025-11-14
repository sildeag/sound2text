package com.sildeag.sound2text.service.pdf

import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.test.KoinTestHarness

class PdfScenarioTest : KoinTestHarness(
    environment = Environment.TEST,
    moduleOverrides = {
        single<PDFWriter> { MockPdfWriter() }
    }
) {
    // Your test logic here
}