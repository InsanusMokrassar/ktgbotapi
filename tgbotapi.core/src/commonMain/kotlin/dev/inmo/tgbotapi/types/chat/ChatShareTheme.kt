@file:OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.types.chat

import dev.inmo.micro_utils.serialization.mapper.MapperSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.gifts.Gift
import dev.inmo.tgbotapi.types.gifts.unique.UniqueGiftBackdropColors
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmInline

/** Appearance of the screen for sharing a chat link. */
@Serializable(ChatShareTheme.Serializer::class)
sealed interface ChatShareTheme {
    val type: String

    @Serializable(Serializer.ForEmoji::class)
    data class Emoji(
        val emojis: List<EmojiType>,
        val backgroundBackdropId: ChatShareThemeBackdropId? = null,
    ) : ChatShareTheme {
        override val type: String get() = emojiField
    }

    @Serializable(Serializer.ForUniqueGift::class)
    data class UniqueGift(val gift: Gift.Unique) : ChatShareTheme {
        override val type: String get() = "unique_gift"
    }

    object Serializer : KSerializer<ChatShareTheme> {
        object ForEmoji : MapperSerializer<ChatShareTheme, Emoji>(
            Serializer, { value -> value }, { value -> value as? Emoji ?: throw SerializationException("Expected ChatShareTheme.Emoji") }
        )

        object ForUniqueGift : MapperSerializer<ChatShareTheme, UniqueGift>(
            Serializer, { value -> value }, { value -> value as? UniqueGift ?: throw SerializationException("Expected ChatShareTheme.UniqueGift") }
        )

        @Serializable
        private data class Surrogate(
            @SerialName(typeField) val type: String,
            @EncodeDefault(EncodeDefault.Mode.NEVER)
            @SerialName(emojisField) val emojis: List<EmojiType>? = null,
            @EncodeDefault(EncodeDefault.Mode.NEVER)
            @SerialName(backgroundBackdropIdField) val backgroundBackdropId: ChatShareThemeBackdropId? = null,
            @EncodeDefault(EncodeDefault.Mode.NEVER)
            @SerialName(giftField) val gift: Gift.Unique? = null,
        )

        override val descriptor: SerialDescriptor = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): ChatShareTheme {
            val data = decoder.decodeSerializableValue(Surrogate.serializer())
            return when (data.type) {
                "emoji" -> Emoji(data.emojis ?: throw SerializationException("Missing emojis"), data.backgroundBackdropId)
                "unique_gift" -> UniqueGift(data.gift ?: throw SerializationException("Missing gift"))
                else -> throw SerializationException("Unknown chat share theme type: ${data.type}")
            }
        }

        override fun serialize(encoder: Encoder, value: ChatShareTheme) {
            val data = when (value) {
                is Emoji -> Surrogate(value.type, emojis = value.emojis, backgroundBackdropId = value.backgroundBackdropId)
                is UniqueGift -> Surrogate(value.type, gift = value.gift)
            }
            encoder.encodeSerializableValue(Surrogate.serializer(), data)
        }
    }
}

@Serializable
@JvmInline
value class ChatShareThemeBackdropId(val int: Int)

@Serializable
data class ChatShareThemeBackdrop(
    @SerialName(idField) val id: ChatShareThemeBackdropId,
    @SerialName(nameField) val name: String,
    @SerialName(colorsField) val colors: UniqueGiftBackdropColors,
)

@Serializable
data class ChatShareThemeBackdrops(
    @SerialName(backdropsField) val backdrops: List<ChatShareThemeBackdrop>,
)
