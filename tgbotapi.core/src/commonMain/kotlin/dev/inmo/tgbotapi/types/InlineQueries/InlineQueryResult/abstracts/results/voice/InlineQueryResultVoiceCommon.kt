package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedOutput
import dev.inmo.tgbotapi.CommonAbstracts.TextedOutput
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultVoiceType = "voice"

interface InlineQueryResultVoiceCommon : InlineQueryResult,
    CaptionedOutput,
    TextedOutput,
    WithInputMessageContentInlineQueryResult,
    TitledInlineQueryResult {
    @Deprecated("Will be removed in next major release")
    override val caption: String?
        get() = text
}
