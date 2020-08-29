package com.github.insanusmokrassar.TelegramBotAPI.types.payments

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts.Priced
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

@Serializable
data class ShippingOption(
    @SerialName(idField)
    val id: ShippingOptionIdentifier,
    @SerialName(titleField)
    val title: String,
    @Serializable(LabeledPricesSerializer::class)
    @SerialName(pricesField)
    override val prices: List<LabeledPrice>
) : Priced

internal object LabeledPricesSerializer : KSerializer<List<LabeledPrice>> by ListSerializer(
    LabeledPrice.serializer()
)
