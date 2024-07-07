package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

internal const val videoTelegramMediaType = "video"

fun TelegramMediaVideo(
    file: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false,
    width: Int? = null,
    height: Int? = null,
    duration: Long? = null,
    thumb: InputFile? = null
) = TelegramMediaVideo(
    file = file,
    text = text,
    parseMode = parseMode,
    rawEntities = null,
    spoilered = spoilered,
    showCaptionAboveMedia = showCaptionAboveMedia,
    width = width,
    height = height,
    duration = duration,
    thumb = thumb
)

fun TelegramMediaVideo(
    file: InputFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false,
    width: Int? = null,
    height: Int? = null,
    duration: Long? = null,
    thumb: InputFile? = null
) = TelegramMediaVideo(
    file = file,
    text = entities.makeString(),
    parseMode = null,
    rawEntities = entities.toRawMessageEntities(),
    spoilered = spoilered,
    showCaptionAboveMedia = showCaptionAboveMedia,
    width = width,
    height = height,
    duration = duration,
    thumb = thumb
)

@Serializable
data class TelegramMediaVideo internal constructor (
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
    override val thumb: InputFile? = null
) : TelegramFreeMedia, SizedTelegramMedia, DuratedTelegramMedia, ThumbedTelegramMedia, VisualMediaGroupMemberTelegramFreeMedia {
    override val type: String = videoTelegramMediaType
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed
}
