package dev.inmo.tgbotapi.types.message.ChatEvents.suggested

import dev.inmo.tgbotapi.types.payments.stars.StarAmount
import dev.inmo.tgbotapi.types.suggestedPostMessageField
import dev.inmo.tgbotapi.types.currencyField
import dev.inmo.tgbotapi.types.amountField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelDirectMessagesEvent
import dev.inmo.tgbotapi.types.message.abstracts.ChannelDirectMessagesContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializer
import dev.inmo.tgbotapi.types.payments.abstracts.Currencied
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import dev.inmo.tgbotapi.types.payments.abstracts.TON
import dev.inmo.tgbotapi.types.payments.abstracts.XTR
import dev.inmo.tgbotapi.types.starAmountField
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(SuggestedPostPaid.Companion::class)
sealed interface SuggestedPostPaid : Currencied, ChannelDirectMessagesEvent {
    val amount: Long?
    val starAmount: StarAmount?
    val suggestedPostMessage: ChannelDirectMessagesContentMessage<*>?

    @Serializable
    data class XTR(
        @SerialName(starAmountField)
        override val starAmount: StarAmount,
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @SerialName(suggestedPostMessageField)
        @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
        override val suggestedPostMessage: ChannelDirectMessagesContentMessage<*>? = null,
    ) : SuggestedPostPaid {
        override val amount: Long?
            get() = null
        @Suppress("OPT_IN_USAGE")
        @EncodeDefault
        override val currency: Currency = Currency.XTR
    }
    @Serializable
    data class TON(
        @SerialName(amountField)
        override val amount: Long,
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @SerialName(suggestedPostMessageField)
        @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
        override val suggestedPostMessage: ChannelDirectMessagesContentMessage<*>? = null
    ) : SuggestedPostPaid {
        override val starAmount: StarAmount?
            get() = null
        @Suppress("OPT_IN_USAGE")
        @EncodeDefault
        override val currency: Currency = Currency.TON
    }
    @Serializable
    data class Other(
        @SerialName(currencyField)
        override val currency: Currency,
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @SerialName(suggestedPostMessageField)
        @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
        override val suggestedPostMessage: ChannelDirectMessagesContentMessage<*>? = null,
        @SerialName(amountField)
        override val amount: Long? = null,
        @SerialName(starAmountField)
        override val starAmount: StarAmount? = null
    ) : SuggestedPostPaid

    companion object : KSerializer<SuggestedPostPaid> {
        override val descriptor: SerialDescriptor
            get() = Other.serializer().descriptor

        override fun serialize(
            encoder: Encoder,
            value: SuggestedPostPaid
        ) {
            val asOther = value as? Other ?: Other(
                currency = value.currency,
                amount = value.amount,
                starAmount = value.starAmount,
                suggestedPostMessage = value.suggestedPostMessage
            )
            return Other.serializer().serialize(encoder, asOther)
        }

        override fun deserialize(decoder: Decoder): SuggestedPostPaid {
            val asOther = Other.serializer().deserialize(decoder)
            return when (asOther.currency) {
                Currency.TON -> TON(
                    amount = asOther.amount ?: return asOther,
                    suggestedPostMessage = asOther.suggestedPostMessage
                )
                Currency.XTR -> XTR(
                    starAmount = asOther.starAmount ?: return asOther,
                    suggestedPostMessage = asOther.suggestedPostMessage
                )
                else -> asOther
            }
        }
    }
}
