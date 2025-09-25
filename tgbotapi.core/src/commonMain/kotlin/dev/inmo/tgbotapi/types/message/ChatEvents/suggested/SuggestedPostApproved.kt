package dev.inmo.tgbotapi.types.message.ChatEvents.suggested

import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelDirectMessagesEvent
import dev.inmo.tgbotapi.types.message.abstracts.ChannelDirectMessagesContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializer
import dev.inmo.tgbotapi.types.payments.SuggestedPostPrice
import dev.inmo.tgbotapi.types.suggestedPostMessageField
import dev.inmo.tgbotapi.types.priceField
import dev.inmo.tgbotapi.types.sendDateField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuggestedPostApproved(
    @SerialName(sendDateField)
    val sendDate: TelegramDate,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(suggestedPostMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    val suggestedPostMessage: ChannelDirectMessagesContentMessage<*>? = null,
    @SerialName(priceField)
    val price: SuggestedPostPrice? = null,
) : ChannelDirectMessagesEvent
