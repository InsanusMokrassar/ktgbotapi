package dev.inmo.tgbotapi.types.message.ChatEvents.suggested

import dev.inmo.tgbotapi.types.suggestedPostMessageField
import dev.inmo.tgbotapi.types.commentField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelDirectMessagesEvent
import dev.inmo.tgbotapi.types.message.abstracts.ChannelDirectMessagesContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuggestedPostDeclined(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(suggestedPostMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    val suggestedPostMessage: ChannelDirectMessagesContentMessage<*>? = null,
    @SerialName(commentField)
    val comment: String? = null
) : ChannelDirectMessagesEvent
