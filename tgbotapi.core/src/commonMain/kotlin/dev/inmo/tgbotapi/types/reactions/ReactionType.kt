package dev.inmo.tgbotapi.types.reactions

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlin.jvm.JvmInline

@Serializable(ReactionType.Companion::class)
sealed interface ReactionType {
    val type: Type

    sealed interface Type {
        val name: String
    }

    @Serializable
    data class Emoji(
        @SerialName(emojiField)
        val emoji: String
    ) : ReactionType {
        @EncodeDefault
        override val type: Type = Companion

        companion object : Type {
            override val name: String = "emoji"
        }
    }

    @Serializable
    data class CustomEmoji(
        @SerialName(customEmojiIdField)
        val customEmojiId: String
    ) : ReactionType {
        @EncodeDefault
        override val type: Type = Companion

        companion object : Type {
            override val name: String = "custom_emoji"
        }
    }

    @Serializable
    class Paid : ReactionType {
        @EncodeDefault
        override val type: Type = Companion

        override fun equals(other: Any?): Boolean {
            return other is Paid
        }

        override fun hashCode(): Int {
            return type.name.hashCode()
        }

        companion object : Type {
            override val name: String = "paid"
        }
    }

    @Serializable
    data class Unknown(
        @SerialName(typeField)
        override val type: Custom,
        @SerialName(rawField)
        val raw: JsonElement? = null
    ) : ReactionType {
        @Serializable
        @JvmInline
        value class Custom(override val name: String) : Type
    }

    companion object : KSerializer<ReactionType> {
        @Serializable
        data class Surrogate(
            @SerialName(typeField)
            val type: String,
            @SerialName(emojiField)
            val emoji: String? = null,
            @SerialName(customEmojiIdField)
            val customEmojiId: String? = null
        )

        override val descriptor: SerialDescriptor = Surrogate.serializer().descriptor

        override fun serialize(encoder: Encoder, value: ReactionType) {
            val surrogate = when (value) {
                is Emoji -> Surrogate(
                    type = value.type.name,
                    emoji = value.emoji
                )
                is CustomEmoji -> Surrogate(
                    type = value.type.name,
                    customEmojiId = value.customEmojiId
                )
                is Paid -> Surrogate(
                    type = value.type.name
                )
                is Unknown -> Surrogate(
                    type = value.type.name
                )
            }
            encoder.encodeSerializableValue(Surrogate.serializer(), surrogate)
        }

        override fun deserialize(decoder: Decoder): ReactionType {
            val (surrogate, json) = decoder.decodeDataAndJson(Surrogate.serializer())
            val unknown by lazy { Unknown(Unknown.Custom(surrogate.type), json) }

            return when (surrogate.type) {
                Emoji.name -> Emoji(
                    emoji = surrogate.emoji ?: return unknown
                )
                CustomEmoji.name -> CustomEmoji(
                    customEmojiId = surrogate.customEmojiId ?: return unknown
                )
                Paid.name -> Paid()
                else -> unknown
            }
        }
    }
} 