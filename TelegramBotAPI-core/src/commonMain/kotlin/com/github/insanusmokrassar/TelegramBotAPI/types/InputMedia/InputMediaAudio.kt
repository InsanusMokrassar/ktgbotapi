package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedOutput
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Performerable
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.mediaField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InputMediaAudio(
    override val file: InputFile,
    override val caption: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    override val duration: Long? = null,
    override val performer: String? = null,
    override val title: String? = null,
    override val thumb: InputFile? = null
) : InputMedia, DuratedInputMedia, ThumbedInputMedia, TitledInputMedia, CaptionedOutput, Performerable {
    override val type: String = "audio"

    @SerialName(mediaField)
    val media: String = when (file) {
        is FileId -> file.fileId
        is MultipartFile -> file.fileId.toInputMediaFileAttachmentName()
    }
}
