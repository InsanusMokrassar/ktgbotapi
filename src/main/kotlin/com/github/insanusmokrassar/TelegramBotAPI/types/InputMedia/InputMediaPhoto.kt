package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize
import com.github.insanusmokrassar.TelegramBotAPI.types.mediaField
import kotlinx.serialization.*

@Serializable
data class InputMediaPhoto(
    @Transient
    override val file: InputFile = throw IllegalStateException("Must be created with file"),
    override val caption: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null
) : InputMedia, CaptionedInputMedia, MediaGroupMemberInputMedia {
    override val type: String = "photo"

    override fun serialize(format: StringFormat): String = format.stringify(serializer(), this)

    @Transient
    override val arguments: Map<String, Any?> = Mapper.mapNullable(serializer(), this)

    @SerialName(mediaField)
    val media: String
        get() = file.let {
            when (it) {
                is FileId -> it.fileId
                is MultipartFile -> inputMediaFileAttachmentNameTemplate.format(it.fileId)
            }
        }
}

fun PhotoSize.toInputMediaPhoto(
    caption: String? = null,
    parseMode: ParseMode? = null
): InputMediaPhoto = InputMediaPhoto(
    fileId,
    caption,
    parseMode
)
