package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.serializers.InlineQueryResultSerializer
import dev.inmo.tgbotapi.types.InlineQueryId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.Serializable

@Serializable(InlineQueryResultSerializer::class)
@ClassCastsIncluded(excludeRegex = ".*Impl")
interface InlineQueryResult {
    val type: String
    val id: InlineQueryId
    val replyMarkup: InlineKeyboardMarkup?
}
