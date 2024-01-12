package dev.inmo.tgbotapi.types.giveaway

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewChat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface GiveawayInfo {
    val selectionDate: TelegramDate
    val onlyNewMembers: Boolean
    val premiumMonths: Int?
    val additionalPrizeDescription: String?
}