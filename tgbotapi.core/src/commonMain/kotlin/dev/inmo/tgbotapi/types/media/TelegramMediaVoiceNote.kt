package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.files.VoiceFile
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun TelegramMediaVoiceNote(
    file: InputFile,
    entities: TextSourcesList,
    duration: Long? = null
) = TelegramMediaVoiceNote(
    file, entities.makeString(), null, entities.toRawMessageEntities(), duration
)

fun TelegramMediaVoiceNote(
    file: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null
) = TelegramMediaVoiceNote(
    file, text, parseMode, null, duration
)

@ConsistentCopyVisibility
@Serializable
data class TelegramMediaVoiceNote internal constructor(
    override val file: InputFile,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    override val duration: Long? = null
) : RichMessageMemberTelegramMedia, DuratedTelegramMedia, TextedOutput {
    @EncodeDefault
    override val type: String = TYPE
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed

    companion object {
        const val TYPE = "voice_note"
    }
}

fun VoiceFile.toTelegramMediaVoiceNote(
    text: String? = null,
    parseMode: ParseMode? = null
): TelegramMediaVoiceNote = TelegramMediaVoiceNote(
    fileId,
    text,
    parseMode,
    duration
)

fun VoiceFile.toTelegramMediaVoiceNote(
    textSources: TextSourcesList = emptyList()
): TelegramMediaVoiceNote = TelegramMediaVoiceNote(
    fileId,
    textSources,
    duration
)
