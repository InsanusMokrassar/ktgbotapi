package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedOutput
import dev.inmo.tgbotapi.CommonAbstracts.TextedOutput
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultPhotoType = "photo"

interface InlineQueryResultPhotoCommon : InlineQueryResult,
    OptionallyTitledInlineQueryResult,
    DescribedInlineQueryResult,
    CaptionedOutput,
    TextedOutput,
    WithInputMessageContentInlineQueryResult {
    @Deprecated("Will be removed in next major release")
    override val caption: String?
        get() = text
}
