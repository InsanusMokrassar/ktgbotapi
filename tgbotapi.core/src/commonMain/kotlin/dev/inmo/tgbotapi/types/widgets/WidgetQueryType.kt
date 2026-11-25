@file:OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.types.widgets

import dev.inmo.micro_utils.serialization.mapper.MapperSerializer
import dev.inmo.tgbotapi.types.addressField
import dev.inmo.tgbotapi.types.amountField
import dev.inmo.tgbotapi.types.payments.ton.TonAddress
import dev.inmo.tgbotapi.types.payments.ton.TonWalletCard
import dev.inmo.tgbotapi.types.typeField
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/** Wallet details supplied with a widget query. The balance uses decimal text in the currency's smallest unit. */
@Serializable
data class WidgetQueryWallet(
    @SerialName(addressField) val address: TonAddress,
    @SerialName(amountField) val amount: String,
    @SerialName("public_key") val publicKey: String? = null,
    @SerialName("state_init") val stateInit: String? = null
)

@Serializable(WidgetQueryType.Serializer::class)
sealed interface WidgetQueryType {
    @Serializable(Serializer.ForData::class)
    data class Data(val walletCard: TonWalletCard? = null) : WidgetQueryType

    @Serializable(Serializer.ForSignData::class)
    data class SignData(val wallet: WidgetQueryWallet, val signData: String) : WidgetQueryType

    @Serializable(Serializer.ForSentTransaction::class)
    data class SentTransaction(val wallet: WidgetQueryWallet, val msgHash: String) : WidgetQueryType

    object Serializer : KSerializer<WidgetQueryType> {
        object ForData : MapperSerializer<WidgetQueryType, Data>(
            Serializer, { value -> value }, { value -> value as? Data ?: throw SerializationException("Expected WidgetQueryType.Data") }
        )

        object ForSignData : MapperSerializer<WidgetQueryType, SignData>(
            Serializer, { value -> value }, { value -> value as? SignData ?: throw SerializationException("Expected WidgetQueryType.SignData") }
        )

        object ForSentTransaction : MapperSerializer<WidgetQueryType, SentTransaction>(
            Serializer, { value -> value }, { value -> value as? SentTransaction ?: throw SerializationException("Expected WidgetQueryType.SentTransaction") }
        )

        @Serializable
        private data class Surrogate(
            @SerialName(typeField) val type: String,
            @EncodeDefault(EncodeDefault.Mode.NEVER)
            @SerialName("wallet_card") val walletCard: TonWalletCard? = null,
            @EncodeDefault(EncodeDefault.Mode.NEVER)
            val wallet: WidgetQueryWallet? = null,
            @EncodeDefault(EncodeDefault.Mode.NEVER)
            @SerialName("sign_data") val signData: String? = null,
            @EncodeDefault(EncodeDefault.Mode.NEVER)
            @SerialName("msg_hash") val msgHash: String? = null
        )

        override val descriptor: SerialDescriptor = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): WidgetQueryType {
            val value = decoder.decodeSerializableValue(Surrogate.serializer())
            return when (value.type) {
                "data" -> Data(value.walletCard)
                "sign_data" -> SignData(
                    value.wallet ?: throw SerializationException("Missing sign_data wallet"),
                    value.signData ?: throw SerializationException("Missing sign_data")
                )
                "sent_transaction" -> SentTransaction(
                    value.wallet ?: throw SerializationException("Missing sent_transaction wallet"),
                    value.msgHash ?: throw SerializationException("Missing msg_hash")
                )
                else -> throw SerializationException("Unknown widget query type: ${value.type}")
            }
        }

        override fun serialize(encoder: Encoder, value: WidgetQueryType) {
            val surrogate = when (value) {
                is Data -> Surrogate("data", walletCard = value.walletCard)
                is SignData -> Surrogate("sign_data", wallet = value.wallet, signData = value.signData)
                is SentTransaction -> Surrogate("sent_transaction", wallet = value.wallet, msgHash = value.msgHash)
            }
            encoder.encodeSerializableValue(Surrogate.serializer(), surrogate)
        }
    }
}
