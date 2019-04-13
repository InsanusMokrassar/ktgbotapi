package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.query

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*

@Serializable
data class RawInlineQuery(
    @SerialName(idField)
    val id: InlineQueryIdentifier,
    @SerialName(fromField)
    val from: User,
    @SerialName(queryField)
    val query: String,
    @SerialName(offsetField)
    val offset: String,
    @SerialName(locationField)
    val location: Location? = null
) {
    @Transient
    val asInlineQuery by lazy {
        location ?.let {
            LocationInlineQuery(id, from, query, offset, location)
        } ?: BaseInlineQuery(id, from, query, offset)
    }
}
