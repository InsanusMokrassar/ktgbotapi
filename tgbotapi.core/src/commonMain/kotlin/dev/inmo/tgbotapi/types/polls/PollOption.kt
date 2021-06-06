package dev.inmo.tgbotapi.types.polls

import dev.inmo.tgbotapi.types.textField
import dev.inmo.tgbotapi.types.votesCountField
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(PollOptionSerializer::class)
sealed class PollOption {
    abstract val text: String
    abstract val votes: Int
}

@Serializable
data class SimplePollOption (
    @SerialName(textField)
    override val text: String,
    @SerialName(votesCountField)
    override val votes: Int
) : PollOption()

@RiskFeature
object PollOptionSerializer : KSerializer<PollOption> {
    override val descriptor: SerialDescriptor = SimplePollOption.serializer().descriptor

    override fun deserialize(decoder: Decoder): PollOption = SimplePollOption.serializer().deserialize(
        decoder
    )

    override fun serialize(encoder: Encoder, value: PollOption) {
        when (value) {
            is SimplePollOption -> SimplePollOption.serializer().serialize(
                encoder,
                value
            )
        }
    }
}
