package dev.inmo.tgbotapi.types.payments.stars

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewUser
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(StarTransaction.Companion::class)
@ClassCastsIncluded
sealed interface StarTransaction {
    val id: StarTransactionId
    val amount: Int
    val date: TelegramDate
    val partner: TransactionPartner
    val source: TransactionPartner?
    val receiver: TransactionPartner?

    @Serializable(StarTransaction.Companion::class)
    data class Incoming(
        @SerialName(idField)
        override val id: StarTransactionId,
        @SerialName(amountField)
        override val amount: Int,
        @SerialName(dateField)
        override val date: TelegramDate,
        @SerialName(sourceField)
        override val partner: TransactionPartner
    ) : StarTransaction {
        @Transient
        override val source: TransactionPartner
            get() = partner
        override val receiver: TransactionPartner?
            get() = null
    }

    @Serializable(StarTransaction.Companion::class)
    data class Outgoing(
        @SerialName(idField)
        override val id: StarTransactionId,
        @SerialName(amountField)
        override val amount: Int,
        @SerialName(dateField)
        override val date: TelegramDate,
        @SerialName(receiverField)
        override val partner: TransactionPartner
    ) : StarTransaction {
        @Transient
        override val source: TransactionPartner?
            get() = null
        override val receiver: TransactionPartner
            get() = partner
    }

    @Serializable(StarTransaction.Companion::class)
    data class Unknown(
        @SerialName(idField)
        override val id: StarTransactionId,
        override val amount: Int,
        override val date: TelegramDate,
        override val source: TransactionPartner?,
        override val receiver: TransactionPartner?,
        val raw: JsonElement?
    ) : StarTransaction {
        override val partner: TransactionPartner
            get() = source ?: receiver ?: error("Unable to take partner from source or receiver. Raw value: $raw")
    }

    companion object : KSerializer<StarTransaction> {
        @Serializable
        private data class Surrogate(
            val id: StarTransactionId,
            val amount: Int,
            val date: TelegramDate,
            val source: TransactionPartner?,
            val receiver: TransactionPartner?,
        )

        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): StarTransaction {
            val (data, json) = decoder.decodeDataAndJson(Surrogate.serializer())

            val unknown by lazy {
                Unknown(
                    id = data.id,
                    amount = data.amount,
                    date = data.date,
                    source = data.source,
                    receiver = data.receiver,
                    raw = json
                )
            }
            return when {
                data.source != null -> Incoming(
                    id = data.id,
                    amount = data.amount,
                    date = data.date,
                    partner = data.source
                )
                data.receiver != null -> Outgoing(
                    id = data.id,
                    amount = data.amount,
                    date = data.date,
                    partner = data.receiver
                )
                else -> unknown
            }
        }

        override fun serialize(encoder: Encoder, value: StarTransaction) {
            if (value is Unknown && value.raw != null) {
                JsonElement.serializer().serialize(encoder, value.raw)
                return
            }

            val surrogate = Surrogate(
                id = value.id,
                amount = value.amount,
                date = value.date,
                source = value.source,
                receiver = value.receiver,
            )

            Surrogate.serializer().serialize(encoder, surrogate)
        }
    }
}