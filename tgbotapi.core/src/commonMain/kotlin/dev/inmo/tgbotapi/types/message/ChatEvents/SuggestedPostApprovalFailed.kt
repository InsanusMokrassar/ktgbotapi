package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelDirectMessagesEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializer
import dev.inmo.tgbotapi.types.payments.SuggestedPostPrice
import dev.inmo.tgbotapi.types.suggestedPostMessageField
import dev.inmo.tgbotapi.types.priceField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuggestedPostApprovalFailed(
    @SerialName(priceField) // TODO::Test that this field will never be null
    val price: SuggestedPostPrice,
    @SerialName(suggestedPostMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    val suggestedPostMessage: Message? = null,
) : ChannelDirectMessagesEvent
