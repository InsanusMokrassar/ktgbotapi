package dev.inmo.tgbotapi.types.InlineQueries.query

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.location.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RawInlineQuery(
    @SerialName(idField)
    val id: InlineQueryIdentifier,
    @SerialName(fromField)
    val from: User,
    @SerialName(queryField)
    val query: String,
    @SerialName(offsetField)
    val offset: String,
    @SerialName(chatTypeField)
    @Serializable(ChatTypeSerializer::class)
    val chatType: ChatType? = null,
    @SerialName(locationField)
    val location: Location? = null
) {
    val asInlineQuery by lazy {
        location ?.let {
            LocationInlineQuery(id, from, query, offset, chatType, location)
        } ?: BaseInlineQuery(id, from, query, offset, chatType)
    }
}
