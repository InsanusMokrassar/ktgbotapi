package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.suggestedPostMessageField
import dev.inmo.tgbotapi.types.commentField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelDirectMessagesEvent
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuggestedPostDeclined(
    @SerialName(suggestedPostMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    val suggestedPostMessage: Message? = null,
    @SerialName(commentField)
    val comment: String? = null
) : ChannelDirectMessagesEvent
