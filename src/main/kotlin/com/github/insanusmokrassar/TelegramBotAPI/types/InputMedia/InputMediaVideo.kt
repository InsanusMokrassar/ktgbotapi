package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.mediaField
import kotlinx.serialization.*

@Serializable
data class InputMediaVideo(
    override val file: InputFile,
    override val caption: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    override val width: Int? = null,
    override val height: Int? = null,
    override val duration: Long? = null,
    override val thumb: InputFile? = null
) : InputMedia, SizedInputMedia, DuratedInputMedia, ThumbedInputMedia, CaptionedInputMedia, MediaGroupMemberInputMedia {
    override val type: String = "video"

    override fun serialize(format: StringFormat): String = format.stringify(serializer(), this)

    @SerialName(mediaField)
    val media: String = when (file) {
        is FileId -> file.fileId
        is MultipartFile -> inputMediaFileAttachmentNameTemplate.format(file.fileId)
    }

    @Transient
    override val arguments: Map<String, Any?> = Mapper.mapNullable(serializer(), this)
}
