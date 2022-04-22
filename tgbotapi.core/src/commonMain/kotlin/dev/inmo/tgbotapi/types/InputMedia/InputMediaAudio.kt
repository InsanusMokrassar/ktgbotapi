package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageEntity.toRawMessageEntities
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.types.media.TelegramMediaAudio
import dev.inmo.tgbotapi.utils.extensions.makeString

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaAudio", "dev.inmo.tgbotapi.types.media.TelegramMediaAudio"))
fun InputMediaAudio(
    file: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    thumb: InputFile? = null
) = TelegramMediaAudio(
    file, entities.makeString(), null, entities.toRawMessageEntities(), duration, performer, title, thumb
)

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaAudio", "dev.inmo.tgbotapi.types.media.TelegramMediaAudio"))
fun InputMediaAudio(
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

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaAudio", "dev.inmo.tgbotapi.types.media.TelegramMediaAudio"))
fun AudioFile.toInputMediaAudio(
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = this.title
): InputMediaAudio = TelegramMediaAudio(
    fileId,
    text,
    parseMode,
    duration,
    performer,
    title,
    thumb ?.fileId
)

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaAudio", "dev.inmo.tgbotapi.types.media.TelegramMediaAudio"))
fun AudioFile.toInputMediaAudio(
    textSources: TextSourcesList = emptyList(),
    title: String? = this.title
): InputMediaAudio = TelegramMediaAudio(
    fileId,
    textSources,
    duration,
    performer,
    title,
    thumb ?.fileId
)
