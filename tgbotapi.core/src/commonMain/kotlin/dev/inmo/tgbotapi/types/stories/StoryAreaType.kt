@file:OptIn(ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.types.stories

import dev.inmo.micro_utils.colors.common.HEXAColor
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.location.LocationAddress
import dev.inmo.tgbotapi.types.reactions.ReactionType
import dev.inmo.tgbotapi.utils.IntRGB24HEXAColorSerializer
import dev.inmo.tgbotapi.utils.LongRGBAFromHEXAColorSerializer
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlin.jvm.JvmInline

@Serializable(StoryAreaType.Companion::class)
sealed interface StoryAreaType {
    val type: Type

    @Serializable(TypeSerializer::class)
    sealed interface Type {
        val name: String
    }

    @Serializable
    data class Location(
        @SerialName(latitudeField)
        val latitude: Double,
        @SerialName(longitudeField)
        val longitude: Double,
        @SerialName(addressField)
        val address: LocationAddress? = null
    ) : StoryAreaType {
        @EncodeDefault
        override val type: Type = Companion

        companion object : Type {
            override val name: String = "location"
        }
    }

    @Serializable
    data class SuggestedReaction(
        @SerialName(reactionTypeField)
        val reactionType: ReactionType,
        @SerialName(isDarkField)
        val isDark: Boolean = false,
        @SerialName(isFlippedField)
        val isFlipped: Boolean = false
    ) : StoryAreaType {
        @EncodeDefault
        override val type: Type = Companion

        companion object : Type {
            override val name: String = "suggested_reaction"
        }
    }

    @Serializable
    data class Link(
        @SerialName(urlField)
        val url: String
    ) : StoryAreaType {
        @EncodeDefault
        override val type: Type = Companion

        companion object : Type {
            override val name: String = "link"
        }
    }

    @Serializable
    data class Weather(
        @SerialName(temperatureField)
        val temperature: Double,
        @SerialName(emojiField)
        val emoji: String,
        @SerialName(backgroundColorField)
        @Serializable(LongRGBAFromHEXAColorSerializer::class)
        val backgroundColor: HEXAColor
    ) : StoryAreaType {
        @EncodeDefault
        override val type: Type = Companion

        companion object : Type {
            override val name: String = "weather"
        }
    }

    @Serializable
    data class UniqueGift(
        @SerialName(nameField)
        val name: String
    ) : StoryAreaType {
        @EncodeDefault
        override val type: Type = Companion

        companion object : Type {
            override val name: String = "unique_gift"
        }
    }

    @Serializable
    data class Unknown(
        @SerialName(typeField)
        override val type: Custom,
        @SerialName(rawField)
        val raw: JsonElement? = null
    ) : StoryAreaType {
        @Serializable
        @JvmInline
        value class Custom(override val name: String) : Type
    }

    companion object : KSerializer<StoryAreaType> {
        private object TypeSerializer : KSerializer<Type> {
            override val descriptor: SerialDescriptor
                get() = String.serializer().descriptor

            override fun deserialize(decoder: Decoder): Type {
                val raw = decoder.decodeString()
                return when (raw) {
                    Location.name -> Location
                    SuggestedReaction.name -> SuggestedReaction
                    Link.name -> Link
                    Weather.name -> Weather
                    UniqueGift.name -> UniqueGift
                    else -> Unknown.Custom(raw)
                }
            }

            override fun serialize(encoder: Encoder, value: Type) {
                encoder.encodeString(value.name)
            }
        }
        @Serializable
        data class Surrogate(
            @SerialName(typeField)
            val type: String,
            @SerialName(latitudeField)
            val latitude: Double? = null,
            @SerialName(longitudeField)
            val longitude: Double? = null,
            @SerialName(addressField)
            val address: LocationAddress? = null,
            @SerialName(reactionTypeField)
            val reactionType: ReactionType? = null,
            @SerialName(isDarkField)
            val isDark: Boolean? = null,
            @SerialName(isFlippedField)
            val isFlipped: Boolean? = null,
            @SerialName(urlField)
            val url: String? = null,
            @SerialName(temperatureField)
            val temperature: Double? = null,
            @SerialName(emojiField)
            val emoji: String? = null,
            @SerialName(backgroundColorField)
            @Serializable(LongRGBAFromHEXAColorSerializer::class)
            val backgroundColor: HEXAColor? = null,
            @SerialName(nameField)
            val name: String? = null
        )

        override val descriptor: SerialDescriptor = Surrogate.serializer().descriptor

        override fun serialize(encoder: Encoder, value: StoryAreaType) {
            val surrogate = when (value) {
                is Location -> Surrogate(
                    type = value.type.name,
                    latitude = value.latitude,
                    longitude = value.longitude,
                    address = value.address
                )
                is SuggestedReaction -> Surrogate(
                    type = value.type.name,
                    reactionType = value.reactionType,
                    isDark = value.isDark,
                    isFlipped = value.isFlipped
                )
                is Link -> Surrogate(
                    type = value.type.name,
                    url = value.url
                )
                is Weather -> Surrogate(
                    type = value.type.name,
                    temperature = value.temperature,
                    emoji = value.emoji,
                    backgroundColor = value.backgroundColor
                )
                is UniqueGift -> Surrogate(
                    type = value.type.name,
                    name = value.name
                )
                is Unknown -> Surrogate(
                    type = value.type.name
                )
            }
            encoder.encodeSerializableValue(Surrogate.serializer(), surrogate)
        }

        override fun deserialize(decoder: Decoder): StoryAreaType {
            val (surrogate, json) = decoder.decodeDataAndJson(Surrogate.serializer())
            val unknown by lazy { Unknown(Unknown.Custom(surrogate.type), json) }

            return when (surrogate.type) {
                Location.name -> Location(
                    latitude = surrogate.latitude ?: return unknown,
                    longitude = surrogate.longitude ?: return unknown,
                    address = surrogate.address
                )
                SuggestedReaction.name -> SuggestedReaction(
                    reactionType = surrogate.reactionType ?: return unknown,
                    isDark = surrogate.isDark ?: false,
                    isFlipped = surrogate.isFlipped ?: false
                )
                Link.name -> Link(
                    url = surrogate.url ?: return unknown
                )
                Weather.name -> Weather(
                    temperature = surrogate.temperature ?: return unknown,
                    emoji = surrogate.emoji ?: return unknown,
                    backgroundColor = surrogate.backgroundColor ?: return unknown
                )
                UniqueGift.name -> UniqueGift(
                    name = surrogate.name ?: return unknown
                )
                else -> unknown
            }
        }
    }
}
