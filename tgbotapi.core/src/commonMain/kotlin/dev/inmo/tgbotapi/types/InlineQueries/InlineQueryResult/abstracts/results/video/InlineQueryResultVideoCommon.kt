package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultVideoType = "video"

interface InlineQueryResultVideoCommon : InlineQueryResult,
    TitledInlineQueryResult,
    DescribedInlineQueryResult,
    TextedOutput,
    WithInputMessageContentInlineQueryResult
