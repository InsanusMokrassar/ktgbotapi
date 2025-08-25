package dev.inmo.tgbotapi.types.message.ChatEvents.suggested

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelDirectMessagesEvent
import dev.inmo.tgbotapi.types.message.abstracts.ChannelDirectMessagesContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializer
import dev.inmo.tgbotapi.types.suggestedPostMessageField
import dev.inmo.tgbotapi.types.reasonField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuggestedPostRefunded(
    @SerialName(reasonField)
    val reason: String,
    @SerialName(suggestedPostMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    val suggestedPostMessage: ChannelDirectMessagesContentMessage<*>? = null
) : ChannelDirectMessagesEvent
