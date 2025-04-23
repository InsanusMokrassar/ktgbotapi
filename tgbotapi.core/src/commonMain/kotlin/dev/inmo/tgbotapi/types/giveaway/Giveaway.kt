package dev.inmo.tgbotapi.types.giveaway

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewChat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Giveaway(
    @SerialName(chatsField)
    val chats: List<PreviewChat>,
    @SerialName(winnersSelectionDateField)
    override val selectionDate: TelegramDate,
    @SerialName(winnersCountField)
    val count: Int,
    @SerialName(onlyNewMembersField)
    override val onlyNewMembers: Boolean = false,
    @SerialName(hasPublicWinnersField)
    val publicWinners: Boolean = false,
    @SerialName(prizeDescriptionField)
    override val additionalPrizeDescription: String? = null,
    @SerialName(countryCodesField)
    val countries: List<IetfLang>? = null,
    @SerialName(premiumSubscriptionMonthCountField)
    override val premiumMonths: Int? = null,
    @SerialName(prizeStarCountField)
    override val prizeStarCount: Int? = null,
) : GiveawayInfo.OptionallyStars, GiveawayInfo.OptionallyPremium, ReplyInfo.External.ContentVariant
