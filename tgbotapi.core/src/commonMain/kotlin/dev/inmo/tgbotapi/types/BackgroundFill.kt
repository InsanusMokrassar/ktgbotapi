@file:OptIn(ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.types

import dev.inmo.micro_utils.colors.common.HEXAColor
import dev.inmo.tgbotapi.utils.IntRGB24HEXAColorSerializer
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement

@ClassCastsIncluded
@Serializable(BackgroundFill.Companion::class)
sealed interface BackgroundFill {
    val type: String
    val colors: List<HEXAColor>

    @Serializable
    data class Solid(
        @SerialName(colorField)
        @Serializable(IntRGB24HEXAColorSerializer::class)
        val color: HEXAColor
    ) : BackgroundFill {
        @Transient
        override val colors: List<HEXAColor> = listOf(color)
        @EncodeDefault
        @SerialName(typeField)
        override val type: String = Companion.type

        companion object {
            const val type = "solid"
        }
    }
    @Serializable
    data class Gradient(
        @SerialName(topColorField)
        @Serializable(IntRGB24HEXAColorSerializer::class)
        val topColor: HEXAColor,
        @SerialName(bottomColorField)
        @Serializable(IntRGB24HEXAColorSerializer::class)
        val bottomColor: HEXAColor,
        @SerialName(rotationAngleField)
        val rotationAngle: Short,
    ) : BackgroundFill {
        @Transient
        override val colors: List<HEXAColor> = listOf(topColor, bottomColor)
        @EncodeDefault
        @SerialName(typeField)
        override val type: String = Companion.type
        companion object {
            const val type = "gradient"
        }
    }
    @Serializable
    data class FreeformGradient(
        @SerialName(colorsField)
        override val colors: List<@Serializable(IntRGB24HEXAColorSerializer::class) HEXAColor>
    ) : BackgroundFill {
        @EncodeDefault
        @SerialName(typeField)
        override val type: String = Companion.type

        companion object {
            const val type = "freeform_gradient"
        }
    }
    @Serializable
    data class Unknown(
        override val type: String,
        val raw: JsonElement?
    ) : BackgroundFill {
        @SerialName(colorsField)
        override val colors: List<HEXAColor> = emptyList()
    }

    companion object : KSerializer<BackgroundFill> {
        @Serializable
        class RawBackgroundFill private constructor(
            @SerialName(typeField)
            val type: String,
            @Serializable(IntRGB24HEXAColorSerializer::class)
            val color: HEXAColor? = null,
            @SerialName(topColorField)
            @Serializable(IntRGB24HEXAColorSerializer::class)
            val topColor: HEXAColor? = null,
            @SerialName(bottomColorField)
            @Serializable(IntRGB24HEXAColorSerializer::class)
            val bottomColor: HEXAColor? = null,
            @SerialName(rotationAngleField)
            val rotationAngle: Short? = null,
            @SerialName(colorsField)
            val colors: List<@Serializable(IntRGB24HEXAColorSerializer::class) HEXAColor>? = null
        )

        private val serializer = RawBackgroundFill.serializer()
        override val descriptor: SerialDescriptor
            get() = serializer.descriptor

        override fun deserialize(decoder: Decoder): BackgroundFill {
            val (raw, json) = decoder.decodeDataAndJson(serializer)
            return when (raw.type) {
                Solid.type -> Solid(color = raw.color ?: return Unknown(raw.type, json))
                Gradient.type -> Gradient(
                    topColor = raw.topColor ?: return Unknown(raw.type, json),
                    bottomColor = raw.bottomColor ?: return Unknown(raw.type, json),
                    rotationAngle = raw.rotationAngle ?: return Unknown(raw.type, json)
                )
                FreeformGradient.type -> FreeformGradient(raw.colors ?: return Unknown(raw.type, json))
                else -> Unknown(raw.type, json)
            }
        }

        override fun serialize(encoder: Encoder, value: BackgroundFill) {
            when (value) {
                is FreeformGradient -> FreeformGradient.serializer().serialize(encoder, value)
                is Gradient -> Gradient.serializer().serialize(encoder, value)
                is Solid -> Solid.serializer().serialize(encoder, value)
                is Unknown -> value.raw ?.also {
                    JsonElement.serializer().serialize(encoder, it)
                } ?: Unknown.serializer().serialize(encoder, value)
            }
        }

    }
}