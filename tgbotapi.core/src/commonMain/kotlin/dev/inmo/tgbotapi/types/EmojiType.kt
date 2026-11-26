@file:OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.types

import dev.inmo.micro_utils.serialization.mapper.MapperSerializer
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/** A regular emoji or a Telegram custom emoji used on the chat sharing screen. */
@Serializable(EmojiType.Serializer::class)
sealed interface EmojiType {
    val type: String

    @Serializable(Serializer.ForEmoji::class)
    data class Emoji(val emoji: String) : EmojiType {
        override val type: String get() = emojiField
    }

    @Serializable(Serializer.ForCustomEmoji::class)
    data class CustomEmoji(val customEmojiId: CustomEmojiId) : EmojiType {
        override val type: String get() = "custom_emoji"
    }

    object Serializer : KSerializer<EmojiType> {
        object ForEmoji : MapperSerializer<EmojiType, Emoji>(
            Serializer, { value -> value }, { value -> value as? Emoji ?: throw SerializationException("Expected EmojiType.Emoji") }
        )

        object ForCustomEmoji : MapperSerializer<EmojiType, CustomEmoji>(
            Serializer, { value -> value }, { value -> value as? CustomEmoji ?: throw SerializationException("Expected EmojiType.CustomEmoji") }
        )

        @Serializable
        private data class Surrogate(
            @SerialName(typeField) val type: String,
            @EncodeDefault(EncodeDefault.Mode.NEVER)
            @SerialName(emojiField) val emoji: String? = null,
            @EncodeDefault(EncodeDefault.Mode.NEVER)
            @SerialName(customEmojiIdField) val customEmojiId: CustomEmojiId? = null,
        )

        override val descriptor: SerialDescriptor = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): EmojiType {
            val data = decoder.decodeSerializableValue(Surrogate.serializer())
            return when (data.type) {
                "emoji" -> Emoji(data.emoji ?: throw SerializationException("Missing emoji"))
                "custom_emoji" -> CustomEmoji(data.customEmojiId ?: throw SerializationException("Missing custom_emoji_id"))
                else -> throw SerializationException("Unknown emoji type: ${data.type}")
            }
        }

        override fun serialize(encoder: Encoder, value: EmojiType) {
            val data = when (value) {
                is Emoji -> Surrogate(value.type, emoji = value.emoji)
                is CustomEmoji -> Surrogate(value.type, customEmojiId = value.customEmojiId)
            }
            encoder.encodeSerializableValue(Surrogate.serializer(), data)
        }
    }
}
