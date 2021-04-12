package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.CommonAbstracts.justTextSources
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.MessageEntity.RawMessageEntities
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

private val baseSerializers: Map<String, KSerializer<out TextSource>> = mapOf(
    "regular" to RegularTextSource.serializer(),
    "text_link" to TextLinkTextSource.serializer(),
    "code" to CodeTextSource.serializer(),
    "url" to URLTextSource.serializer(),
    "pre" to PreTextSource.serializer(),
    "bot_command" to BotCommandTextSource.serializer(),
    "strikethrough" to StrikethroughTextSource.serializer(),
    "italic" to ItalicTextSource.serializer(),
    "bold" to BoldTextSource.serializer(),
    "email" to EMailTextSource.serializer(),
    "underline" to UnderlineTextSource.serializer(),
    "mention" to MentionTextSource.serializer(),
    "phone_number" to PhoneNumberTextSource.serializer(),
    "text_mention" to TextMentionTextSource.serializer(),
    "hashtag" to HashTagTextSource.serializer(),
    "cashtag" to CashTagTextSource.serializer(),
)

@Serializer(TextSource::class)
object TextSourceSerializer : KSerializer<TextSource> {
    private val serializers = baseSerializers.toMutableMap()
    @InternalSerializationApi
    override val descriptor: SerialDescriptor = buildSerialDescriptor(
        "TextSourceSerializer",
        SerialKind.CONTEXTUAL
    ) {
        element("type", String.serializer().descriptor)
        element("value", ContextualSerializer(TextSource::class).descriptor)
    }

    @InternalSerializationApi
    override fun deserialize(decoder: Decoder): TextSource {
        return decoder.decodeStructure(descriptor) {
            var type: String? = null
            lateinit var result: TextSource
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> type = decodeStringElement(descriptor, 0)
                    1 -> {
                        require(type != null) { "Type is null, but it is expected that was inited already" }
                        result = decodeSerializableElement(
                            descriptor,
                            1,
                            serializers.getValue(type)
                        )
                    }
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }
            result
        }
    }

    @InternalSerializationApi
    private fun <T : TextSource> CompositeEncoder.encode(value: T) {
        encodeSerializableElement(descriptor, 1, value::class.serializer() as KSerializer<T>, value)
    }

    @InternalSerializationApi
    override fun serialize(encoder: Encoder, value: TextSource) {
        encoder.encodeStructure(descriptor) {
            val valueSerializer = value::class.serializer()
            val type = serializers.keys.first { serializers[it] == valueSerializer }
            encodeStringElement(descriptor, 0, type)
            encode(value)
        }
    }

    fun <T: TextSource> include(type: String, serializer: KSerializer<T>) {
        require(type !in baseSerializers.keys)
        serializers[type] = serializer
    }

    fun exclude(type: String) {
        require(type !in baseSerializers.keys)
        serializers.remove(type)
    }
}
