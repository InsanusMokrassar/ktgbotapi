package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.LiveLocation

import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.*

@Serializable
data class EditInlineMessageLiveLocation(
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageIdentifier,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditInlineMessage, EditReplyMessage, EditLocationMessage {
    override fun method(): String = "editMessageLiveLocation"
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
