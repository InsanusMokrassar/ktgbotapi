package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.files.AudioFile
import kotlinx.serialization.*

internal const val audioInputMediaType = "audio"

fun InputMediaAudio(
    file: InputFile,
    entities: List<TextSource>,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    thumb: InputFile? = null
) = InputMediaAudio(
    file, entities.makeString(), null, entities.toRawMessageEntities(), duration, performer, title, thumb
)

fun InputMediaAudio(
    file: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    thumb: InputFile? = null
) = InputMediaAudio(
    file, text, parseMode, null, duration, performer, title, thumb
)

@Serializable
data class InputMediaAudio internal constructor(
    override val file: InputFile,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    override val duration: Long? = null,
    override val performer: String? = null,
    override val title: String? = null,
    override val thumb: InputFile? = null
) : InputMedia, AudioMediaGroupMemberInputMedia, DuratedInputMedia, ThumbedInputMedia, TitledInputMedia, Performerable {
    override val type: String = audioInputMediaType
    override val entities: List<TextSource>? by lazy {
        rawEntities ?.asTextParts(text ?: return@lazy null) ?.justTextSources()
    }

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed
}

fun AudioFile.toInputMediaAudio(
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = this.title
): InputMediaAudio = InputMediaAudio(
    fileId,
    text,
    parseMode,
    duration,
    performer,
    title,
    thumb ?.fileId
)

fun AudioFile.toInputMediaAudio(
    textSources: TextSourcesList = emptyList(),
    title: String? = this.title
): InputMediaAudio = InputMediaAudio(
    fileId,
    textSources,
    duration,
    performer,
    title,
    thumb ?.fileId
)
