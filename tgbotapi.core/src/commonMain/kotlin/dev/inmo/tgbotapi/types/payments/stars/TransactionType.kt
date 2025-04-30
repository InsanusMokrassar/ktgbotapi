package dev.inmo.tgbotapi.types.payments.stars

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = TransactionTypeSerializer::class)
enum class TransactionType {
    INVOICE_PAYMENT,
    PAID_MEDIA_PAYMENT,
    GIFT_PURCHASE,
    PREMIUM_PURCHASE,
    BUSINESS_ACCOUNT_TRANSFER,
    UNKNOWN
}

private object TransactionTypeSerializer : KSerializer<TransactionType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "dev.inmo.tgbotapi.types.payments.stars.TransactionType",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): TransactionType {
        return try {
            TransactionType.valueOf(decoder.decodeString())
        } catch (e: IllegalArgumentException) {
            TransactionType.UNKNOWN
        }
    }

    override fun serialize(encoder: Encoder, value: TransactionType) {
        encoder.encodeString(value.name)
    }
}
