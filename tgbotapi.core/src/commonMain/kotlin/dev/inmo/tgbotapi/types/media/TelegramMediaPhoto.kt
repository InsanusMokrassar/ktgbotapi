package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.files.PhotoSize
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

internal const val photoTelegramMediaType = "photo"

fun TelegramMediaPhoto(
    file: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false
) = TelegramMediaPhoto(file, text, parseMode, null, spoilered, showCaptionAboveMedia)

fun TelegramMediaPhoto(
    file: InputFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false
) = TelegramMediaPhoto(file, entities.makeString(), null, entities.toRawMessageEntities(), spoilered, showCaptionAboveMedia)

@Serializable
data class TelegramMediaPhoto internal constructor(
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
) : TelegramFreeMedia, VisualMediaGroupMemberTelegramMedia {
    override val type: String = photoTelegramMediaType
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed
}

fun PhotoSize.toTelegramMediaPhoto(
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false
): TelegramMediaPhoto = TelegramMediaPhoto(
    file = fileId,
    text = text,
    parseMode = parseMode,
    spoilered = spoilered,
    showCaptionAboveMedia = showCaptionAboveMedia
)

fun PhotoSize.toTelegramMediaPhoto(
    textSources: TextSourcesList = emptyList(),
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false
): TelegramMediaPhoto = TelegramMediaPhoto(
    file = fileId,
    entities = textSources,
    spoilered = spoilered,
    showCaptionAboveMedia = showCaptionAboveMedia
)
