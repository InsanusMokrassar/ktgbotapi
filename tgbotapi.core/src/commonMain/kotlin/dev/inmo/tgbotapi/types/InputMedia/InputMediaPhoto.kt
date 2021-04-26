package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.files.PhotoSize
import kotlinx.serialization.*

internal const val photoInputMediaType = "photo"

fun InputMediaPhoto(
    file: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null
) = InputMediaPhoto(file, text, parseMode, null)

fun InputMediaPhoto(
    file: InputFile,
    entities: List<TextSource>
) = InputMediaPhoto(file, entities.makeString(), null, entities.toRawMessageEntities())

@Serializable
data class InputMediaPhoto internal constructor(
    override val file: InputFile,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null
) : InputMedia, VisualMediaGroupMemberInputMedia {
    override val type: String = photoInputMediaType
    override val entities: List<TextSource>? by lazy {
        rawEntities ?.asTextParts(text ?: return@lazy null) ?.justTextSources()
    }

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed
}

fun PhotoSize.toInputMediaPhoto(
    text: String? = null,
    parseMode: ParseMode? = null
): InputMediaPhoto = InputMediaPhoto(
    fileId,
    text,
    parseMode
)

fun PhotoSize.toInputMediaPhoto(
    textSources: TextSourcesList = emptyList()
): InputMediaPhoto = InputMediaPhoto(
    fileId,
    textSources
)
