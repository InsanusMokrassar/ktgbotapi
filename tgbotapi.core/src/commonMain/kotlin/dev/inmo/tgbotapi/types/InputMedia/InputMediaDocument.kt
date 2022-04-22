package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageEntity.toRawMessageEntities
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.media.TelegramMediaDocument
import dev.inmo.tgbotapi.utils.extensions.makeString

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaDocument", "dev.inmo.tgbotapi.types.media.TelegramMediaDocument"))
fun InputMediaDocument(
    file: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    thumb: InputFile? = null,
    disableContentTypeDetection: Boolean? = null
) = TelegramMediaDocument(file, text, parseMode, null, thumb, disableContentTypeDetection)

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaDocument", "dev.inmo.tgbotapi.types.media.TelegramMediaDocument"))
fun InputMediaDocument(
    file: InputFile,
    entities: TextSourcesList,
    thumb: InputFile? = null,
    disableContentTypeDetection: Boolean? = null
) = TelegramMediaDocument(
    file,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    thumb,
    disableContentTypeDetection
)

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaDocument", "dev.inmo.tgbotapi.types.media.TelegramMediaDocument"))
fun DocumentFile.toInputMediaDocument(
    text: String? = null,
    parseMode: ParseMode? = null
) = TelegramMediaDocument(
    fileId,
    text,
    parseMode,
    thumb ?.fileId
)

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaDocument", "dev.inmo.tgbotapi.types.media.TelegramMediaDocument"))
fun DocumentFile.toInputMediaDocument(
    textSources: TextSourcesList = emptyList()
) = TelegramMediaDocument(
    fileId,
    textSources,
    thumb ?.fileId
)
