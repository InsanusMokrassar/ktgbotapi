package dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.location.StaticLocation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RawChosenInlineResult(
    @SerialName(resultIdField)
    val resultId: InlineQueryIdentifier, //chosen temporary, can be changed
    @SerialName(fromField)
    val user: User,
    @SerialName(queryField)
    val query: String,
    @SerialName(locationField)
    val location: StaticLocation? = null,
    @SerialName(inlineMessageIdField)
    val inlineMessageId: InlineMessageIdentifier? = null
) {
    val asChosenInlineResult: ChosenInlineResult by lazy {
        location ?.let {
            LocationChosenInlineResult(resultId, user, location, inlineMessageId, query)
        } ?: BaseChosenInlineResult(resultId, user, inlineMessageId, query)
    }
}
