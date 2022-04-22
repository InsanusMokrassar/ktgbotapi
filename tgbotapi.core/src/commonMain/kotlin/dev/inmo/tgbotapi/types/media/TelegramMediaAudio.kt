package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.CommonAbstracts.Performerable
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

internal const val audioTelegramMediaType = "audio"

fun TelegramMediaAudio(
    file: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    thumb: InputFile? = null
) = TelegramMediaAudio(
    file, entities.makeString(), null, entities.toRawMessageEntities(), duration, performer, title, thumb
)

fun TelegramMediaAudio(
    file: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    thumb: InputFile? = null
) = TelegramMediaAudio(
    file, text, parseMode, null, duration, performer, title, thumb
)

@Serializable
data class TelegramMediaAudio internal constructor(
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
) : TelegramMedia, AudioMediaGroupMemberTelegramMedia, DuratedTelegramMedia, ThumbedTelegramMedia, TitledTelegramMedia, Performerable {
    override val type: String = audioTelegramMediaType
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed
}

fun AudioFile.toTelegramMediaAudio(
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = this.title
): TelegramMediaAudio = TelegramMediaAudio(
    fileId,
    text,
    parseMode,
    duration,
    performer,
    title,
    thumb ?.fileId
)

fun AudioFile.toTelegramMediaAudio(
    textSources: TextSourcesList = emptyList(),
    title: String? = this.title
): TelegramMediaAudio = TelegramMediaAudio(
    fileId,
    textSources,
    duration,
    performer,
    title,
    thumb ?.fileId
)
