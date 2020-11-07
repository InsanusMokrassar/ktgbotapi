package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedOutput
import dev.inmo.tgbotapi.CommonAbstracts.TextedOutput
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultVideoType = "video"

interface InlineQueryResultVideoCommon : InlineQueryResult,
    TitledInlineQueryResult,
    DescribedInlineQueryResult,
    CaptionedOutput,
    TextedOutput,
    WithInputMessageContentInlineQueryResult {
    @Deprecated("Will be removed in next major release")
    override val caption: String?
        get() = text
}
