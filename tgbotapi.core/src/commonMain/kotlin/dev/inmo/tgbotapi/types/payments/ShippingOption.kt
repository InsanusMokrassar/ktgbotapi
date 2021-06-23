package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.payments.abstracts.Priced
import dev.inmo.tgbotapi.utils.RiskFeature
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

@RiskFeature
object LabeledPricesSerializer : KSerializer<List<LabeledPrice>> by ListSerializer(
    LabeledPrice.serializer()
)
