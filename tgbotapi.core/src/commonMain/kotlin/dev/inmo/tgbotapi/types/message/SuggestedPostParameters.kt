package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.payments.SuggestedPostPrice
import dev.inmo.tgbotapi.types.priceField
import dev.inmo.tgbotapi.types.sendDateField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuggestedPostParameters(
    @SerialName(priceField)
    val price: SuggestedPostPrice? = null,
    @SerialName(sendDateField)
    val sendDate: TelegramDate? = null
)
