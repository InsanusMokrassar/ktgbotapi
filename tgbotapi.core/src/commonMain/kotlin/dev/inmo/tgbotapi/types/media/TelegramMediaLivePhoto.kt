package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

fun TelegramMediaLivePhoto(
    file: InputFile,
    photo: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false
) = TelegramMediaLivePhoto(
    file = file,
    photo = photo,
    text = text,
    parseMode = parseMode,
    rawEntities = null,
    spoilered = spoilered,
    showCaptionAboveMedia = showCaptionAboveMedia
)

fun TelegramMediaLivePhoto(
    file: InputFile,
    photo: InputFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false
) = TelegramMediaLivePhoto(
    file = file,
    photo = photo,
    text = entities.makeString(),
    parseMode = null,
    rawEntities = entities.toRawMessageEntities(),
    spoilered = spoilered,
    showCaptionAboveMedia = showCaptionAboveMedia
)

@ConsistentCopyVisibility
@Serializable
data class TelegramMediaLivePhoto internal constructor(
    override val file: InputFile,
    @SerialName(photoField)
    val photo: InputFile,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(hasSpoilerField)
    override val spoilered: Boolean = false,
    @SerialName(showCaptionAboveMediaField)
    override val showCaptionAboveMedia: Boolean = false,
) : TelegramFreeMedia,
    VisualMediaGroupMemberTelegramMedia,
    InputPollMedia,
    InputPollOptionMedia {
    @EncodeDefault
    override val type: String = TYPE
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed

    companion object {
        const val TYPE = "live_photo"
    }
}
