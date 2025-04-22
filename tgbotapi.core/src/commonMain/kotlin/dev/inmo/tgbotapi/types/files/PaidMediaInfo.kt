package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.types.ReplyInfo
import dev.inmo.tgbotapi.types.message.payments.PaidMedia
import dev.inmo.tgbotapi.types.paidMediaField
import dev.inmo.tgbotapi.types.starCountField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaidMediaInfo(
    @SerialName(starCountField)
    val stars: Int,
    @SerialName(paidMediaField)
    val media: List<PaidMedia>,
) : ReplyInfo.External.ContentVariant
