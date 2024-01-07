package dev.inmo.tgbotapi.types.abstracts

import dev.inmo.tgbotapi.types.TextQuote

interface WithOptionalQuoteInfo {
    val quote: TextQuote?
}
