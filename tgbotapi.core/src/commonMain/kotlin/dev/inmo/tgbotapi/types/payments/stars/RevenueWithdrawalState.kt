package dev.inmo.tgbotapi.types.payments.stars

import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(RevenueWithdrawalState.Companion::class)
@ClassCastsIncluded
sealed interface RevenueWithdrawalState {
    val type: String

    @Serializable(RevenueWithdrawalState.Companion::class)
    data object Pending : RevenueWithdrawalState {
        override val type: String
            get() = "pending"
    }

    @Serializable(RevenueWithdrawalState.Companion::class)
    data class Succeeded(
        val date: TelegramDate,
        val url: String,
    ) : RevenueWithdrawalState {
        override val type: String
            get() = Companion.type

        companion object {
            const val type: String = "succeeded"
        }
    }

    @Serializable(RevenueWithdrawalState.Companion::class)
    data object Failed : RevenueWithdrawalState {
        override val type: String
            get() = "failed"
    }

    @Serializable(RevenueWithdrawalState.Companion::class)
    data class Unknown(
        override val type: String,
        val raw: JsonElement?,
    ) : RevenueWithdrawalState

    companion object : KSerializer<RevenueWithdrawalState> {
        @Serializable
        private data class Surrogate(
            val type: String,
            val date: TelegramDate? = null,
            val url: String? = null,
        )

        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): RevenueWithdrawalState {
            val (data, json) = decoder.decodeDataAndJson(Surrogate.serializer())

            val unknown by lazy {
                Unknown(data.type, json)
            }
            return when (data.type) {
                Pending.type -> Pending
                Failed.type -> Failed
                Succeeded.type ->
                    Succeeded(
                        data.date ?: return unknown,
                        data.url ?: return unknown,
                    )
                else -> unknown
            }
        }

        override fun serialize(
            encoder: Encoder,
            value: RevenueWithdrawalState,
        ) {
            val surrogate = when (value) {
                Failed -> Surrogate(value.type)
                Pending -> Surrogate(value.type)
                is Succeeded ->
                    Surrogate(
                        value.type,
                        value.date,
                        value.url,
                    )
                is Unknown ->
                    value.raw ?.let {
                        return JsonElement.serializer().serialize(encoder, it)
                    } ?: Surrogate(value.type)
            }

            Surrogate.serializer().serialize(encoder, surrogate)
        }
    }
}
