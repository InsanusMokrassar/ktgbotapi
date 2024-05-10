package dev.inmo.tgbotapi.types

import dev.inmo.micro_utils.common.Progress
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.utils.IntProgress100Serializer
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement

@ClassCastsIncluded
@Serializable(BackgroundType.Companion::class)
sealed interface BackgroundType {
    val type: String

    sealed interface Movable : BackgroundType {
        val isMoving: Boolean
    }
    sealed interface Dimmable : BackgroundType {
        val darkThemeDimming: Progress
    }
    sealed interface Fillable : BackgroundType {
        val fill: BackgroundFill
    }
    sealed interface WithDocument : BackgroundType {
        val document: DocumentFile
    }

    @Serializable
    data class Fill(
        @SerialName(fillField)
        override val fill: BackgroundFill,
        @SerialName(darkThemeDimmingField)
        @Serializable(IntProgress100Serializer::class)
        override val darkThemeDimming: Progress
    ) : Fillable, Dimmable {
        @EncodeDefault
        @SerialName(typeField)
        override val type: String = Companion.type
        companion object {
            const val type: String = "fill"
        }
    }

    @Serializable
    data class Wallpaper(
        @SerialName(documentField)
        override val document: DocumentFile,
        @SerialName(darkThemeDimmingField)
        @Serializable(IntProgress100Serializer::class)
        override val darkThemeDimming: Progress,
        @SerialName(isBlurredField)
        val isBlurred: Boolean = false,
        @SerialName(isMovingField)
        override val isMoving: Boolean = false
    ) : WithDocument, Dimmable, Movable {
        @EncodeDefault
        @SerialName(typeField)
        override val type: String = Companion.type
        companion object {
            const val type: String = "wallpaper"
        }
    }

    @Serializable
    data class Pattern(
        @SerialName(documentField)
        override val document: DocumentFile,
        @SerialName(fillField)
        override val fill: BackgroundFill,
        @SerialName(intensityField)
        @Serializable(IntProgress100Serializer::class)
        val intensity: Progress,
        @SerialName(isInvertedField)
        val isInverted: Boolean = false,
        @SerialName(isMovingField)
        override val isMoving: Boolean = false
    ) : WithDocument, Fillable, Movable {
        @EncodeDefault
        @SerialName(typeField)
        override val type: String = Companion.type
        companion object {
            const val type: String = "pattern"
        }
    }

    @Serializable
    data class ChatTheme(
        @SerialName(themeNameField)
        val themeName: String
    ): BackgroundType {
        @EncodeDefault
        @SerialName(typeField)
        override val type: String = Companion.type
        companion object {
            const val type: String = "chat_theme"
        }
    }

    @Serializable
    data class Unknown(
        @SerialName(typeField)
        override val type: String,
        val raw: JsonElement?
    ): BackgroundType

    companion object : KSerializer<BackgroundType> {
        @Serializable
        data class RawBackgroundType(
            val type: String,
            @SerialName(fillField)
            val fill: BackgroundFill? = null,
            @SerialName(darkThemeDimmingField)
            @Serializable(IntProgress100Serializer::class)
            val darkThemeDimming: Progress? = null,
            @SerialName(documentField)
            val document: DocumentFile? = null,
            @SerialName(isBlurredField)
            val isBlurred: Boolean = false,
            @SerialName(isMovingField)
            val isMoving: Boolean = false,
            @SerialName(intensityField)
            @Serializable(IntProgress100Serializer::class)
            val intensity: Progress? = null,
            @SerialName(isInvertedField)
            val isInverted: Boolean = false,
            @SerialName(themeNameField)
            val themeName: String? = null
        )

        override val descriptor: SerialDescriptor
            get() = RawBackgroundType.serializer().descriptor

        override fun deserialize(decoder: Decoder): BackgroundType {
            val (raw, json) = decoder.decodeDataAndJson(RawBackgroundType.serializer())
            val unknown by lazy { Unknown(raw.type, json) }
            return when (raw.type) {
                Fill.type -> Fill(
                    raw.fill ?: return unknown,
                    raw.darkThemeDimming ?: return unknown
                )
                Wallpaper.type -> Wallpaper(
                    document = raw.document ?: return unknown,
                    darkThemeDimming = raw.darkThemeDimming ?: return unknown,
                    isBlurred = raw.isBlurred,
                    isMoving = raw.isMoving
                )
                Pattern.type -> Pattern(
                    document = raw.document ?: return unknown,
                    fill = raw.fill ?: return unknown,
                    intensity = raw.intensity ?: return unknown,
                    isInverted = raw.isInverted,
                    isMoving = raw.isMoving
                )
                ChatTheme.type -> ChatTheme(
                    raw.themeName ?: return unknown
                )
                else -> unknown
            }
        }

        override fun serialize(encoder: Encoder, value: BackgroundType) {
            when (value) {
                is ChatTheme -> ChatTheme.serializer().serialize(encoder, value)
                is Fill -> Fill.serializer().serialize(encoder, value)
                is Wallpaper -> Wallpaper.serializer().serialize(encoder, value)
                is Pattern -> Pattern.serializer().serialize(encoder, value)
                is Unknown -> value.raw ?.also {
                    JsonElement.serializer().serialize(encoder, it)
                } ?: Unknown.serializer().serialize(encoder, value)
            }
        }
    }
}