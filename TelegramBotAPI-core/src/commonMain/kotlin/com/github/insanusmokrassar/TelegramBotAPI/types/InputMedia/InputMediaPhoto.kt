package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize
import com.github.insanusmokrassar.TelegramBotAPI.types.mediaField
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
) : InputMedia, MediaGroupMemberInputMedia {
    override val type: String = photoInputMediaType

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    val media: String = when (file) {
        is FileId -> file.fileId
        is MultipartFile -> file.fileId.toInputMediaFileAttachmentName()
    }

    @Transient
    override val arguments: JsonElement = buildArguments(serializer())
}

fun PhotoSize.toInputMediaPhoto(
    caption: String? = null,
    parseMode: ParseMode? = null
): InputMediaPhoto = InputMediaPhoto(
    fileId,
    caption,
    parseMode
)
