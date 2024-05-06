package dev.inmo.tgbotapi.types.polls

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.EntitiesBuilder
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(InputPollOption.Companion::class)
data class InputPollOption @Warning("This constructor is not recommended to use") constructor(
    override val text: String,
    val parseMode: ParseMode?,
    override val textSources: List<TextSource>,
) : TextedInput {
    constructor(text: String, parseMode: ParseMode? = null) : this(text, parseMode, emptyList())
    constructor(textSources: List<TextSource>) : this(textSources.makeSourceString(), null, textSources)
    constructor(builderBody: EntitiesBuilderBody) : this(EntitiesBuilder().apply(builderBody).build())

    companion object : KSerializer<InputPollOption> {
        @Serializable
        private data class RawPollInputOption(
            val text: String,
            val parseMode: ParseMode? = null,
            val textSources: List<RawMessageEntity> = emptyList(),
        )
        override val descriptor: SerialDescriptor
            get() = RawPollInputOption.serializer().descriptor

        override fun deserialize(decoder: Decoder): InputPollOption {
            val raw = RawPollInputOption.serializer().deserialize(decoder)
            return InputPollOption(
                raw.text,
                raw.parseMode,
                raw.textSources.asTextSources(raw.text)
            )
        }

        override fun serialize(encoder: Encoder, value: InputPollOption) {
            RawPollInputOption.serializer().serialize(
                encoder,
                RawPollInputOption(
                    value.text,
                    value.parseMode,
                    value.textSources.toRawMessageEntities()
                )
            )
        }
    }
}
