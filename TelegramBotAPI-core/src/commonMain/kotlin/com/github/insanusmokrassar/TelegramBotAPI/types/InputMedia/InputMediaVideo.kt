package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.mediaField
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElement

internal const val videoInputMediaType = "video"

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
) : InputMedia, SizedInputMedia, DuratedInputMedia, ThumbedInputMedia, MediaGroupMemberInputMedia {
    override val type: String = videoInputMediaType

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)


    @Transient
    override val arguments: JsonElement = buildArguments(serializer())

    @SerialName(mediaField)
    val media: String = when (file) {
        is FileId -> file.fileId
        is MultipartFile -> file.fileId.toInputMediaFileAttachmentName()
    }
}
