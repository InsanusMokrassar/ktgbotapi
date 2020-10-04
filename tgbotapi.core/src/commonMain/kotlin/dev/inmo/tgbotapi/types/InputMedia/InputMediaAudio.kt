package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedOutput
import dev.inmo.tgbotapi.CommonAbstracts.Performerable
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.mediaField
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
    override val media: String
    init { media = file.fileId } // crutch until js compiling will be fixed
}
