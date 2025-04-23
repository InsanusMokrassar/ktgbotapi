package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.payments.abstracts.Amounted
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AffiliateInfo(
    @SerialName(commissionPerMilleField)
    val commissionPerMille: Int,
    @SerialName(amountField)
    override val amount: Long,
    @SerialName(nanostarAmountField)
    val nanostarAmount: Long,
    @SerialName(affiliateUserField)
    val affiliateUser: User? = null,
    @SerialName(affiliateChatField)
    val affiliateChat: PreviewChat? = null,
) : Amounted
