package dev.inmo.tgbotapi.types.giveaway

import dev.inmo.tgbotapi.abstracts.WithPreviewChatAndMessageId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.PreviewUser
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(GiveawayPublicResults.Companion::class)
sealed interface GiveawayPublicResults: GiveawayInfo, GiveawayResults, WithPreviewChatAndMessageId {
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
        override val premiumMonths: Int? = null
    }

    @Serializable
    data class Winners (
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
        @SerialName(premiumSubscriptionMonthCountField)
        override val premiumMonths: Int? = null
    ) : GiveawayPublicResults {
        @SerialName(wasRefundedField)
        @Required
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
        val premiumMonths: Int? = null
    )

    companion object : KSerializer<GiveawayPublicResults> {
        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): GiveawayPublicResults {
            val surrogate = Surrogate.serializer().deserialize(decoder)

            return when (surrogate.refunded) {
                true -> Refunded(
                    chat = surrogate.chat,
                    messageId = surrogate.messageId,
                    selectionDate = surrogate.selectionDate
                )
                false -> {
                    Winners(
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
                }
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
                refunded = value.refunded
            )

            Surrogate.serializer().serialize(encoder, surrogate)
        }
    }
}