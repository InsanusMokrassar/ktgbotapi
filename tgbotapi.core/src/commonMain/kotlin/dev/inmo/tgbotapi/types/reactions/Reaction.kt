package dev.inmo.tgbotapi.types.reactions

import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.customEmojiField
import dev.inmo.tgbotapi.types.emojiField
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement

@Serializable(Reaction.Companion::class)
@ClassCastsIncluded
sealed interface Reaction {
    val type: String

    @Serializable(Reaction.Companion::class)
    data class Emoji(
        val emoji: String
    ) : Reaction {
        override val type: String
            get() = Companion.type
        companion object {
            const val type: String = "emoji"
        }
    }

    @Serializable(Reaction.Companion::class)
    data class CustomEmoji(
        val customEmoji: CustomEmojiId
    ) : Reaction {
        override val type: String
            get() = Companion.type
        companion object {
            const val type: String = "custom_emoji"
        }
    }

    @Serializable(Reaction.Companion::class)
    data class Unknown(
        override val type: String,
        val sourceJson: JsonElement?
    ) : Reaction

    @Serializable
    private data class Surrogate(
        val type: String,
        @SerialName(emojiField)
        val emoji: String? = null,
        @SerialName(customEmojiField)
        val customEmoji: CustomEmojiId? = null
    )

    companion object : KSerializer<Reaction> {
        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): Reaction {
            val (surrogate, json) = if (decoder is JsonDecoder) {
                val json = decoder.decodeJsonElement()
                decoder.json.decodeFromJsonElement(Surrogate.serializer(), json) to json
            } else {
                Surrogate.serializer().deserialize(decoder) to null
            }

            return when {
                surrogate.emoji != null -> Emoji(surrogate.emoji)
                surrogate.customEmoji != null -> CustomEmoji(surrogate.customEmoji)
                else -> Unknown(surrogate.type, json)
            }
        }

        override fun serialize(encoder: Encoder, value: Reaction) {
            if (value is Unknown && value.sourceJson != null) {
                JsonElement.serializer().serialize(encoder, value.sourceJson)
            } else {
                Surrogate.serializer().serialize(
                    encoder,
                    Surrogate(
                        type = value.type,
                        emoji = (value as? Emoji) ?.emoji,
                        customEmoji = (value as? CustomEmoji) ?.customEmoji,
                    )
                )
            }
        }

    }
}
