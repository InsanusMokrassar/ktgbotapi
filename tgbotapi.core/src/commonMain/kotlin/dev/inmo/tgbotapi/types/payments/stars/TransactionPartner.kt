package dev.inmo.tgbotapi.types.payments.stars

import dev.inmo.tgbotapi.types.chat.PreviewUser
import dev.inmo.tgbotapi.types.userField
import dev.inmo.tgbotapi.types.withdrawalStateField
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(TransactionPartner.Companion::class)
sealed interface TransactionPartner {
    val type: String

    @Serializable(TransactionPartner.Companion::class)
    data class Fragment(
        @SerialName(withdrawalStateField)
        val withdrawalState: RevenueWithdrawalState
    ) : TransactionPartner {
        override val type: String
            get() = Companion.type

        companion object {
            const val type: String = "fragment"
        }
    }

    @Serializable(TransactionPartner.Companion::class)
    data class User(
        @SerialName(userField)
        val user: PreviewUser
    ) : TransactionPartner {
        override val type: String
            get() = Companion.type

        companion object {
            const val type: String = "user"
        }
    }

    @Serializable(TransactionPartner.Companion::class)
    data object Other : TransactionPartner {
        override val type: String = "other"
    }

    @Serializable(TransactionPartner.Companion::class)
    data class Unknown(
        override val type: String,
        val raw: JsonElement?
    ) : TransactionPartner

    companion object : KSerializer<TransactionPartner> {
        @Serializable
        private data class Surrogate(
            val type: String,
            val withdrawal_state: RevenueWithdrawalState? = null,
            val user: PreviewUser? = null
        )

        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): TransactionPartner {
            val (data, json) = decoder.decodeDataAndJson(Surrogate.serializer())

            val unknown by lazy {
                Unknown(data.type, json)
            }
            return when (data.type) {
                Other.type -> Other
                User.type -> User(
                    data.user ?: return unknown,
                )
                Fragment.type -> Fragment(
                    data.withdrawal_state ?: return unknown,
                )
                else -> unknown
            }
        }

        override fun serialize(encoder: Encoder, value: TransactionPartner) {
            val surrogate = when (value) {
                Other -> Surrogate(value.type)
                is User -> Surrogate(value.type, user = value.user)
                is Fragment -> Surrogate(
                    value.type,
                    value.withdrawalState
                )
                is Unknown -> value.raw ?.let {
                    return JsonElement.serializer().serialize(encoder, it)
                } ?: Surrogate(value.type)
            }

            Surrogate.serializer().serialize(encoder, surrogate)
        }
    }
}