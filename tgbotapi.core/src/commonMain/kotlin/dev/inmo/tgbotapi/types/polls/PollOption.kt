package dev.inmo.tgbotapi.types.polls

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.textEntitiesField
import dev.inmo.tgbotapi.types.textField
import dev.inmo.tgbotapi.types.votesCountField
import dev.inmo.tgbotapi.utils.EntitiesBuilder
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(PollOptionSerializer::class)
sealed class PollOption : TextedInput {
    abstract val votes: Int

    abstract fun asInput(): InputPollOption

    companion object {
        fun simple(
            text: String,
            textSources: List<TextSource>,
            votes: Int = 0
        ) = SimplePollOption(text, textSources, votes)
        fun simple(
            textSources: List<TextSource>,
            votes: Int = 0
        ) = SimplePollOption(textSources.makeSourceString(), textSources, votes)
        fun simple(
            votes: Int = 0,
            builder: EntitiesBuilderBody
        ) = simple(
            EntitiesBuilder().apply(builder).build(),
            votes
        )
    }
}

@Serializable(PollOptionSerializer::class)
data class SimplePollOption (
    @SerialName(textField)
    override val text: String,
    @SerialName(textEntitiesField)
    override val textSources: List<TextSource>,
    @SerialName(votesCountField)
    override val votes: Int = 0
) : PollOption() {
    override fun asInput(): InputPollOption = InputPollOption(text, null, textSources)
}

@RiskFeature
object PollOptionSerializer : KSerializer<PollOption> {
    @Serializable
    private data class RawPollOption(
        @SerialName(textField)
        val text: String,
        @SerialName(textEntitiesField)
        val textSources: List<RawMessageEntity>,
        @SerialName(votesCountField)
        val votes: Int = 0
    )
    override val descriptor: SerialDescriptor = RawPollOption.serializer().descriptor

    override fun deserialize(decoder: Decoder): PollOption {
        val raw = RawPollOption.serializer().deserialize(
            decoder
        )

        return SimplePollOption(raw.text, raw.textSources.asTextSources(raw.text), raw.votes)
    }

    override fun serialize(encoder: Encoder, value: PollOption) {
        when (value) {
            is SimplePollOption -> RawPollOption.serializer().serialize(
                encoder,
                RawPollOption(
                    value.text,
                    value.textSources.toRawMessageEntities(),
                    value.votes
                )
            )
        }
    }
}
