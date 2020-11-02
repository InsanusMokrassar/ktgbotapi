package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedOutput
import dev.inmo.tgbotapi.CommonAbstracts.Performerable
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.types.files.PhotoSize
import dev.inmo.tgbotapi.types.mediaField
import dev.inmo.tgbotapi.types.message.content.media.AudioContent
import kotlinx.serialization.*

internal const val audioInputMediaType = "audio"

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
) : InputMedia, AudioMediaGroupMemberInputMedia, DuratedInputMedia, ThumbedInputMedia, TitledInputMedia, CaptionedOutput, Performerable {
    override val type: String = audioInputMediaType

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileId } // crutch until js compiling will be fixed
}

fun AudioFile.toInputMediaAudio(
    caption: String? = null,
    parseMode: ParseMode? = null,
    title: String? = this.title
): InputMediaAudio = InputMediaAudio(
    fileId,
    caption,
    parseMode,
    duration,
    performer,
    title,
    thumb ?.fileId
)
