package dev.inmo.tgbotapi.types.payments.stars

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmInline

@Serializable(TransactionTypeSerializer::class)
sealed interface TransactionType {
    val name: String

    @Serializable
    data object InvoicePayment : TransactionType {
        override val name = "invoice_payment"
    }

    @Serializable
    data object PaidMediaPayment : TransactionType {
        override val name = "paid_media_payment"
    }

    @Serializable
    data object GiftPurchase : TransactionType {
        override val name = "gift_purchase"
    }

    @Serializable
    data object PremiumPurchase : TransactionType {
        override val name = "premium_purchase"
    }

    @Serializable
    data object BusinessAccountTransfer : TransactionType {
        override val name = "business_account_transfer"
    }

    @Serializable
    @JvmInline
    value class Unknown(override val name: String) : TransactionType
}

private object TransactionTypeSerializer : KSerializer<TransactionType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "dev.inmo.tgbotapi.types.payments.stars.TransactionType",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): TransactionType {
        val value = decoder.decodeString()
        return when (value) {
            TransactionType.InvoicePayment.name -> TransactionType.InvoicePayment
            TransactionType.PaidMediaPayment.name -> TransactionType.PaidMediaPayment
            TransactionType.GiftPurchase.name -> TransactionType.GiftPurchase
            TransactionType.PremiumPurchase.name -> TransactionType.PremiumPurchase
            TransactionType.BusinessAccountTransfer.name -> TransactionType.BusinessAccountTransfer
            else -> TransactionType.Unknown(value)
        }
    }

    override fun serialize(encoder: Encoder, value: TransactionType) {
        encoder.encodeString(value.name)
    }
}
