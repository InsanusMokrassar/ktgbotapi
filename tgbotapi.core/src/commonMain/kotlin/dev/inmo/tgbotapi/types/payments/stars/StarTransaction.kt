package dev.inmo.tgbotapi.types.payments.stars

import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.UserId
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
@Serializable(StarTransaction.Companion::class)
sealed interface StarTransaction {
    val amount: Int
    val date: TelegramDate
    val partner: TransactionPartner
    val source: TransactionPartner?
    val receiver: TransactionPartner?

    @Serializable(StarTransaction.Companion::class)
    data class Incoming(

    ) : StarTransaction {
        override val type: String
            get() = Companion.type

        companion object {
            const val type: String = "fragment"
        }
    }

    @Serializable(StarTransaction.Companion::class)
    data class User(
        @SerialName(userField)
        val user: PreviewUser
    ) : StarTransaction {
        override val type: String
            get() = Companion.type

        companion object {
            const val type: String = "user"
        }
    }

    @Serializable(StarTransaction.Companion::class)
    data object Other : StarTransaction {
        override val type: String = "other"
    }

    @Serializable(StarTransaction.Companion::class)
    data class Unknown(
        override val type: String,
        val raw: JsonElement?
    ) : StarTransaction

    companion object : KSerializer<StarTransaction> {
        @Serializable
        private data class Surrogate(
            val type: String,
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

        override fun serialize(encoder: Encoder, value: StarTransaction) {
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