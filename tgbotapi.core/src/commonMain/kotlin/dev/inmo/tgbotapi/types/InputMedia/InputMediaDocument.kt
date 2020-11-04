package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedOutput
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.disableContentTypeDetectionField
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.mediaField
import kotlinx.serialization.*

internal const val documentInputMediaType = "document"

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
data class InputMediaDocument(
    override val file: InputFile,
    override val caption: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    override val thumb: InputFile? = null,
    @SerialName(disableContentTypeDetectionField)
    val disableContentTypeDetection: Boolean? = null
) : InputMedia, DocumentMediaGroupMemberInputMedia, ThumbedInputMedia, CaptionedOutput {
    override val type: String = documentInputMediaType

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed
}

fun DocumentFile.toInputMediaDocument(
    caption: String? = null,
    parseMode: ParseMode? = null
) = InputMediaDocument(
    fileId,
    caption,
    parseMode,
    thumb ?.fileId
)
