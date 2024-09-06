package dev.inmo.tgbotapi.types.giveaway

import dev.inmo.tgbotapi.abstracts.WithPreviewChatAndMessageId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.PreviewUser
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(GiveawayPublicResults.Companion::class)
sealed interface GiveawayPublicResults: GiveawayInfo, GiveawayResults, WithPreviewChatAndMessageId,
    ReplyInfo.External.ContentVariant, GiveawayInfo.OptionallyStars, GiveawayInfo.OptionallyPremium {
    val count: Int
    val winners: List<PreviewUser>
    val additionalChats: Int
    val publicWinners: Boolean
    val refunded: Boolean

    @Serializable
    data class Refunded(
        @SerialName(chatsField)
        override val chat: PreviewChat,
        @SerialName(giveawayMessageIdField)
        override val messageId: MessageId,
        @SerialName(winnersSelectionDateField)
        override val selectionDate: TelegramDate,
    ) : GiveawayPublicResults {
        @SerialName(wasRefundedField)
        @Required
        @EncodeDefault
        override val refunded: Boolean = true
        @SerialName(winnersCountField)
        override val count: Int = 0
        @SerialName(winnersField)
        override val winners: List<PreviewUser> = emptyList()
        @SerialName(additionalChatCountField)
        override val additionalChats: Int = 0
        @SerialName(unclaimedPrizeCountField)
        override val unclaimedCount: Int = 0
        @SerialName(onlyNewMembersField)
        override val onlyNewMembers: Boolean = false
        @SerialName(hasPublicWinnersField)
        override val publicWinners: Boolean = false
        @SerialName(prizeDescriptionField)
        override val additionalPrizeDescription: String? = null
        @SerialName(premiumSubscriptionMonthCountField)
        override val premiumMonths: Int?
            get() = null
        @SerialName(prizeStarCountField)
        override val prizeStarCount: Int?
            get() = null
    }

    sealed interface Winners : GiveawayPublicResults {
        override val prizeStarCount: Int?
            get() = null
        override val premiumMonths: Int?
            get() = null
        @Serializable
        data class Premium (
            @SerialName(chatsField)
            override val chat: PreviewChat,
            @SerialName(giveawayMessageIdField)
            override val messageId: MessageId,
            @SerialName(winnersSelectionDateField)
            override val selectionDate: TelegramDate,
            @SerialName(winnersCountField)
            override val count: Int,
            @SerialName(winnersField)
            override val winners: List<PreviewUser>,
            @SerialName(premiumSubscriptionMonthCountField)
            override val premiumMonths: Int,
            @SerialName(additionalChatCountField)
            override val additionalChats: Int = 0,
            @SerialName(unclaimedPrizeCountField)
            override val unclaimedCount: Int = 0,
            @SerialName(onlyNewMembersField)
            override val onlyNewMembers: Boolean = false,
            @SerialName(hasPublicWinnersField)
            override val publicWinners: Boolean = false,
            @SerialName(prizeDescriptionField)
            override val additionalPrizeDescription: String? = null,
        ) : Winners, GiveawayInfo.Premium {
            @SerialName(wasRefundedField)
            @Required
            @EncodeDefault
            override val refunded: Boolean = false
        }
        @Serializable
        data class Stars (
            @SerialName(chatsField)
            override val chat: PreviewChat,
            @SerialName(giveawayMessageIdField)
            override val messageId: MessageId,
            @SerialName(winnersSelectionDateField)
            override val selectionDate: TelegramDate,
            @SerialName(winnersCountField)
            override val count: Int,
            @SerialName(winnersField)
            override val winners: List<PreviewUser>,
            @SerialName(prizeStarCountField)
            override val prizeStarCount: Int,
            @SerialName(additionalChatCountField)
            override val additionalChats: Int = 0,
            @SerialName(unclaimedPrizeCountField)
            override val unclaimedCount: Int = 0,
            @SerialName(onlyNewMembersField)
            override val onlyNewMembers: Boolean = false,
            @SerialName(hasPublicWinnersField)
            override val publicWinners: Boolean = false,
            @SerialName(prizeDescriptionField)
            override val additionalPrizeDescription: String? = null,
        ) : Winners, GiveawayInfo.Stars {
            @SerialName(wasRefundedField)
            @Required
            @EncodeDefault
            override val refunded: Boolean = false
        }
    }
    @Serializable
    data class Unknown (
        @SerialName(chatsField)
        override val chat: PreviewChat,
        @SerialName(giveawayMessageIdField)
        override val messageId: MessageId,
        @SerialName(winnersSelectionDateField)
        override val selectionDate: TelegramDate,
        @SerialName(winnersCountField)
        override val count: Int,
        @SerialName(winnersField)
        override val winners: List<PreviewUser>,
        @SerialName(prizeStarCountField)
        override val prizeStarCount: Int?,
        @SerialName(premiumSubscriptionMonthCountField)
        override val premiumMonths: Int?,
        @SerialName(additionalChatCountField)
        override val additionalChats: Int = 0,
        @SerialName(unclaimedPrizeCountField)
        override val unclaimedCount: Int = 0,
        @SerialName(onlyNewMembersField)
        override val onlyNewMembers: Boolean = false,
        @SerialName(hasPublicWinnersField)
        override val publicWinners: Boolean = false,
        @SerialName(prizeDescriptionField)
        override val additionalPrizeDescription: String? = null,
    ) : GiveawayPublicResults {
        @SerialName(wasRefundedField)
        @Required
        @EncodeDefault
        override val refunded: Boolean = false
    }

    @Serializable
    private data class Surrogate(
        @SerialName(chatsField)
        val chat: PreviewChat,
        @SerialName(giveawayMessageIdField)
        val messageId: MessageId,
        @SerialName(winnersSelectionDateField)
        val selectionDate: TelegramDate,
        @SerialName(winnersCountField)
        val count: Int,
        @SerialName(winnersField)
        val winners: List<PreviewUser>,
        @SerialName(additionalChatCountField)
        val additionalChats: Int = 0,
        @SerialName(unclaimedPrizeCountField)
        val unclaimedCount: Int = 0,
        @SerialName(onlyNewMembersField)
        val onlyNewMembers: Boolean = false,
        @SerialName(hasPublicWinnersField)
        val publicWinners: Boolean = false,
        @SerialName(wasRefundedField)
        val refunded: Boolean = false,
        @SerialName(prizeDescriptionField)
        val additionalPrizeDescription: String? = null,
        @SerialName(premiumSubscriptionMonthCountField)
        val premiumMonths: Int? = null,
        @SerialName(prizeStarCountField)
        val starsCount: Int? = null
    )

    companion object : KSerializer<GiveawayPublicResults> {
        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): GiveawayPublicResults {
            val surrogate = Surrogate.serializer().deserialize(decoder)

            return when {
                surrogate.refunded -> Refunded(
                    chat = surrogate.chat,
                    messageId = surrogate.messageId,
                    selectionDate = surrogate.selectionDate
                )
                surrogate.premiumMonths != null -> Winners.Premium(
                    chat = surrogate.chat,
                    messageId = surrogate.messageId,
                    selectionDate = surrogate.selectionDate,
                    count = surrogate.count,
                    winners = surrogate.winners,
                    additionalChats = surrogate.additionalChats,
                    unclaimedCount = surrogate.unclaimedCount,
                    onlyNewMembers = surrogate.onlyNewMembers,
                    publicWinners = surrogate.publicWinners,
                    additionalPrizeDescription = surrogate.additionalPrizeDescription,
                    premiumMonths = surrogate.premiumMonths,
                )
                surrogate.starsCount != null -> Winners.Stars(
                    chat = surrogate.chat,
                    messageId = surrogate.messageId,
                    selectionDate = surrogate.selectionDate,
                    count = surrogate.count,
                    winners = surrogate.winners,
                    additionalChats = surrogate.additionalChats,
                    unclaimedCount = surrogate.unclaimedCount,
                    onlyNewMembers = surrogate.onlyNewMembers,
                    publicWinners = surrogate.publicWinners,
                    additionalPrizeDescription = surrogate.additionalPrizeDescription,
                    prizeStarCount = surrogate.starsCount,
                )
                else -> Unknown(
                    chat = surrogate.chat,
                    messageId = surrogate.messageId,
                    selectionDate = surrogate.selectionDate,
                    count = surrogate.count,
                    winners = surrogate.winners,
                    additionalChats = surrogate.additionalChats,
                    unclaimedCount = surrogate.unclaimedCount,
                    onlyNewMembers = surrogate.onlyNewMembers,
                    publicWinners = surrogate.publicWinners,
                    additionalPrizeDescription = surrogate.additionalPrizeDescription,
                    premiumMonths = surrogate.premiumMonths,
                    prizeStarCount = surrogate.starsCount,
                )
            }
        }

        override fun serialize(encoder: Encoder, value: GiveawayPublicResults) {
            val surrogate = Surrogate(
                chat = value.chat,
                messageId = value.messageId,
                selectionDate = value.selectionDate,
                count = value.count,
                winners = value.winners,
                additionalChats = value.additionalChats,
                unclaimedCount = value.unclaimedCount,
                onlyNewMembers = value.onlyNewMembers,
                publicWinners = value.publicWinners,
                additionalPrizeDescription = value.additionalPrizeDescription,
                premiumMonths = value.premiumMonths,
                starsCount = value.prizeStarCount,
                refunded = value.refunded
            )

            Surrogate.serializer().serialize(encoder, surrogate)
        }

        fun Winners(
            chat: PreviewChat,
            messageId: MessageId,
            selectionDate: TelegramDate,
            count: Int,
            winners: List<PreviewUser>,
            additionalChats: Int = 0,
            unclaimedCount: Int = 0,
            onlyNewMembers: Boolean = false,
            publicWinners: Boolean = false,
            additionalPrizeDescription: String? = null,
            premiumMonths: Int? = null
        ) = premiumMonths ?.let {
            Winners.Premium(
                chat = chat,
                messageId = messageId,
                selectionDate = selectionDate,
                count = count,
                winners = winners,
                premiumMonths = premiumMonths,
                additionalChats = additionalChats,
                unclaimedCount = unclaimedCount,
                onlyNewMembers = onlyNewMembers,
                publicWinners = publicWinners,
                additionalPrizeDescription = additionalPrizeDescription
            )
        } ?: Unknown(
            chat = chat,
            messageId = messageId,
            selectionDate = selectionDate,
            count = count,
            winners = winners,
            prizeStarCount = null,
            premiumMonths = premiumMonths,
            additionalChats = additionalChats,
            unclaimedCount = unclaimedCount,
            onlyNewMembers = onlyNewMembers,
            publicWinners = publicWinners,
            additionalPrizeDescription = additionalPrizeDescription
        )
    }
}