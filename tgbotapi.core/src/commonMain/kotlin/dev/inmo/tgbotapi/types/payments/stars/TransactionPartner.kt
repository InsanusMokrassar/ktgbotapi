@file:Suppress("OPT_IN_USAGE")

package dev.inmo.tgbotapi.types.payments.stars

import dev.inmo.tgbotapi.abstracts.types.SubscriptionPeriodInfo
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewBot
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.PreviewUser
import dev.inmo.tgbotapi.types.gifts.Gift
import dev.inmo.tgbotapi.types.message.payments.PaidMedia
import dev.inmo.tgbotapi.types.payments.AffiliateInfo
import dev.inmo.tgbotapi.utils.TimeSpanAsSecondsSerializer
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import korlibs.time.TimeSpan
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(TransactionPartner.Companion::class)
@ClassCastsIncluded
sealed interface TransactionPartner {
    val type: String

    @Serializable(TransactionPartner.Companion::class)
    data class Fragment(
        @SerialName(withdrawalStateField)
        val withdrawalState: RevenueWithdrawalState,
    ) : TransactionPartner {
        @EncodeDefault
        override val type: String
            get() = Companion.type

        companion object {
            const val type: String = "fragment"
        }
    }

    @Serializable(TransactionPartner.Companion::class)
    data class User(
        @SerialName(userField)
        val user: PreviewUser,
        @SerialName(affiliateField)
        val affiliate: AffiliateInfo? = null,
        @SerialName(invoicePayloadField)
        val invoicePayload: InvoicePayload? = null,
        @SerialName(subscriptionPeriodField)
        @Serializable(TimeSpanAsSecondsSerializer::class)
        override val subscriptionPeriod: TimeSpan? = null,
        @SerialName(paidMediaField)
        val paidMedia: List<PaidMedia>? = null,
        @SerialName(paidMediaPayloadField)
        val paidMediaPayload: PaidMediaPayload? = null,
        @SerialName(giftField)
        val gift: Gift.Regular? = null,
    ) : TransactionPartner, SubscriptionPeriodInfo {
        @EncodeDefault
        override val type: String = Companion.type

        companion object {
            const val type: String = "user"
        }
    }

    @Serializable(TransactionPartner.Companion::class)
    data class Chat(
        @SerialName(chatField)
        val chat: PreviewChat,
        @SerialName(giftField)
        val gift: Gift.Regular? = null,
    ) : TransactionPartner {
        @EncodeDefault
        override val type: String = Companion.type

        companion object {
            const val type: String = "chat"
        }
    }

    /**
     * Represents [TransactionPartnerTelegramApi](https://core.telegram.org/bots/api#transactionpartnertelegramapi)
     */
    @Serializable(TransactionPartner.Companion::class)
    data class TelegramAPI(
        @SerialName(requestCountField)
        val requestCount: Int,
    ) : TransactionPartner {
        @EncodeDefault
        override val type: String = Companion.type

        companion object {
            const val type: String = "telegram_api"
        }
    }

    /**
     * Represents [TransactionPartnerAffiliateProgram](https://core.telegram.org/bots/api#transactionpartneraffiliateprogram)
     */
    @Serializable(TransactionPartner.Companion::class)
    data class AffiliateProgram(
        @SerialName(sponsorUserField)
        val sponsorUser: PreviewBot?,
        @SerialName(commissionPerMilleField)
        val commissionPerMille: Int,
    ) : TransactionPartner {
        @EncodeDefault
        override val type: String = Companion.type

        companion object {
            const val type: String = "affiliate_program"
        }
    }

    @Serializable(TransactionPartner.Companion::class)
    data object Ads : TransactionPartner {
        @EncodeDefault
        override val type: String = "telegram_ads"
    }

    @Serializable(TransactionPartner.Companion::class)
    data object Other : TransactionPartner {
        @EncodeDefault
        override val type: String = "other"
    }

    @Serializable(TransactionPartner.Companion::class)
    data class Unknown(
        override val type: String,
        val raw: JsonElement?,
    ) : TransactionPartner

    companion object : KSerializer<TransactionPartner> {
        @Serializable
        private data class Surrogate(
            val type: String,
            val withdrawal_state: RevenueWithdrawalState? = null,
            val user: PreviewUser? = null,
            val chat: PreviewChat? = null,
            val affiliate: AffiliateInfo? = null,
            val invoice_payload: InvoicePayload? = null,
            @Serializable(TimeSpanAsSecondsSerializer::class)
            val subscription_period: TimeSpan? = null,
            val paid_media: List<PaidMedia>? = null,
            val paid_media_payload: PaidMediaPayload? = null,
            val gift: Gift.Regular? = null,
            val request_count: Int? = null,
            val sponsor_user: PreviewBot? = null,
            val commission_per_mille: Int? = null,
            val invoicePayload: InvoicePayload? = null,
        )

        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): TransactionPartner {
            val (data, json) = decoder.decodeDataAndJson(Surrogate.serializer())

            val unknown by lazy {
                Unknown(data.type, json)
            }
            return with(data) {
                when (data.type) {
                    Other.type -> Other
                    Chat.type ->
                        Chat(
                            chat ?: return unknown,
                            gift,
                        )
                    User.type ->
                        User(
                            user = user ?: return unknown,
                            affiliate = affiliate,
                            invoicePayload = invoice_payload,
                            subscriptionPeriod = subscription_period,
                            paidMedia = paid_media,
                            paidMediaPayload = paid_media_payload,
                            gift = gift,
                        )
                    TelegramAPI.type ->
                        TelegramAPI(
                            data.request_count ?: return unknown,
                        )
                    Ads.type -> Ads
                    Fragment.type ->
                        Fragment(
                            data.withdrawal_state ?: return unknown,
                        )
                    AffiliateProgram.type ->
                        AffiliateProgram(
                            data.sponsor_user,
                            data.commission_per_mille ?: return unknown,
                        )
                    else -> unknown
                }
            }
        }

        override fun serialize(
            encoder: Encoder,
            value: TransactionPartner,
        ) {
            val surrogate = with(value) {
                when (this) {
                    Other -> Surrogate(type = value.type)
                    Ads -> Surrogate(type = value.type)
                    is Chat ->
                        Surrogate(
                            type = value.type,
                            chat = chat,
                            gift = gift,
                        )
                    is User ->
                        Surrogate(
                            type = value.type,
                            user = user,
                            affiliate = affiliate,
                            invoice_payload = invoicePayload,
                            subscription_period = subscriptionPeriod,
                            paid_media = paidMedia,
                            paid_media_payload = paidMediaPayload,
                            gift = gift,
                        )
                    is TelegramAPI -> Surrogate(type = value.type, request_count = requestCount)
                    is Fragment ->
                        Surrogate(
                            type = value.type,
                            withdrawal_state = withdrawalState,
                        )
                    is AffiliateProgram ->
                        Surrogate(
                            type = value.type,
                            sponsor_user = sponsorUser,
                            commission_per_mille = commissionPerMille,
                        )
                    is Unknown ->
                        raw ?.let {
                            return JsonElement.serializer().serialize(encoder, it)
                        } ?: Surrogate(type = value.type)
                }
            }

            Surrogate.serializer().serialize(encoder, surrogate)
        }
    }
}
