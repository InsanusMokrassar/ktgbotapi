package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun TelegramMediaAnimation(
    file: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false,
    width: Int? = null,
    height: Int? = null,
    duration: Long? = null,
    thumb: InputFile? = null,
) = TelegramMediaAnimation(file, text, parseMode, null, spoilered, showCaptionAboveMedia, width, height, duration, thumb)

fun TelegramMediaAnimation(
    file: InputFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false,
    width: Int? = null,
    height: Int? = null,
    duration: Long? = null,
    thumb: InputFile? = null,
) = TelegramMediaAnimation(
    file,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    spoilered,
    showCaptionAboveMedia,
    width,
    height,
    duration,
    thumb,
)

@Serializable
data class TelegramMediaAnimation internal constructor(
    override val file: InputFile,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(hasSpoilerField)
    override val spoilered: Boolean = false,
    @SerialName(showCaptionAboveMediaField)
    override val showCaptionAboveMedia: Boolean = false,
    override val width: Int? = null,
    override val height: Int? = null,
    override val duration: Long? = null,
    override val thumb: InputFile? = null,
) : TelegramFreeMedia, SizedTelegramMedia, DuratedTelegramMedia, ThumbedTelegramMedia, TextedOutput, SpoilerableTelegramMedia, WithCustomizableCaptionTelegramMedia {
    override val type: String = "animation"
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    @SerialName(mediaField)
    override val media: String

    init {
        media = file.fileIdToSend
    } // crutch until js compiling will be fixed
}
