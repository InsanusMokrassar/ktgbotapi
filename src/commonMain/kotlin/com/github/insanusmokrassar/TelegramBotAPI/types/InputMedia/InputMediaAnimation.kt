package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedOutput
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.mediaField
import com.github.insanusmokrassar.TelegramBotAPI.utils.format
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InputMediaAnimation(
    override val file: InputFile,
    override val caption: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    override val width: Int? = null,
    override val height: Int? = null,
    override val duration: Long? = null,
    override val thumb: InputFile? = null
) : InputMedia, SizedInputMedia, DuratedInputMedia, ThumbedInputMedia, CaptionedOutput {
    override val type: String = "animation"

    @SerialName(mediaField)
    val media: String
        get() = file.let {
            when (it) {
                is FileId -> it.fileId
                is MultipartFile -> inputMediaFileAttachmentNameTemplate.format(it.fileId)
            }
        }
}
