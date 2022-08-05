package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

import dev.inmo.tgbotapi.ksp.lib.ClassCastsIncluded
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.serializers.InlineQueryResultSerializer
import dev.inmo.tgbotapi.types.InlineQueryIdentifier
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.Serializable

@Serializable(InlineQueryResultSerializer::class)
@ClassCastsIncluded
interface InlineQueryResult {
    val type: String
    val id: InlineQueryIdentifier
    val replyMarkup: InlineKeyboardMarkup?
}
