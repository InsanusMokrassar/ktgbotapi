package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

internal const val documentTelegramMediaType = "document"

fun TelegramMediaDocument(
    file: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    thumb: InputFile? = null,
    disableContentTypeDetection: Boolean? = null
) = TelegramMediaDocument(file, text, parseMode, null, thumb, disableContentTypeDetection)

fun TelegramMediaDocument(
    file: InputFile,
    entities: TextSourcesList,
    thumb: InputFile? = null,
    disableContentTypeDetection: Boolean? = null
) = TelegramMediaDocument(
    file,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    thumb,
    disableContentTypeDetection
)

/**
 * Represents a general file to be sent. See https://core.telegram.org/bots/api#inputmediadocument
 *
 * @param disableContentTypeDetection Disables automatic server-side content type detection for files uploaded using
 * multipart/form-data. Always used by Telegram system as true, if the document is sent as part of an album.
 *
 * @see InputFile
 * @see MultipartFile
 * @see FileId
 */
@Serializable
data class TelegramMediaDocument internal constructor(
    override val file: InputFile,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    override val thumb: InputFile? = null,
    @SerialName(disableContentTypeDetectionField)
    val disableContentTypeDetection: Boolean? = null
) : TelegramMedia, DocumentMediaGroupMemberTelegramMedia, ThumbedTelegramMedia {
    override val type: String = documentTelegramMediaType
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed
}

fun DocumentFile.toTelegramMediaDocument(
    text: String? = null,
    parseMode: ParseMode? = null
) = TelegramMediaDocument(
    fileId,
    text,
    parseMode,
    thumbnail ?.fileId
)

fun DocumentFile.toTelegramMediaDocument(
    textSources: TextSourcesList = emptyList()
) = TelegramMediaDocument(
    fileId,
    textSources,
    thumbnail ?.fileId
)
