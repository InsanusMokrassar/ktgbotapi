package dev.inmo.tgbotapi.types.message.payments

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.PaidMediaPayload
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.fromField
import dev.inmo.tgbotapi.types.paidMediaPayloadField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaidMediaPurchased(
    @SerialName(fromField)
    override val from: User,
    @SerialName(paidMediaPayloadField)
    val payload: PaidMediaPayload,
) : FromUser
