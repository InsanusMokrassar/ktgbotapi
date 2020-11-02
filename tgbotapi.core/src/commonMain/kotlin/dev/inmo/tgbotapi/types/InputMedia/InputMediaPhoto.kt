package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.files.PhotoSize
import dev.inmo.tgbotapi.types.mediaField
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

internal const val photoInputMediaType = "photo"

@Serializable
data class InputMediaPhoto(
    override val file: InputFile,
    override val caption: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null
) : InputMedia, VisualMediaGroupMemberInputMedia {
    override val type: String = photoInputMediaType

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed
}

fun PhotoSize.toInputMediaPhoto(
    caption: String? = null,
    parseMode: ParseMode? = null
): InputMediaPhoto = InputMediaPhoto(
    fileId,
    caption,
    parseMode
)
