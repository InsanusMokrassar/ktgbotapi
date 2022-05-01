package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.files.PhotoSize
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import dev.inmo.tgbotapi.utils.extensions.makeString

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaPhoto", "dev.inmo.tgbotapi.types.media.TelegramMediaPhoto"))
fun InputMediaPhoto(
    file: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null
) = TelegramMediaPhoto(file, text, parseMode, null)

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaPhoto", "dev.inmo.tgbotapi.types.media.TelegramMediaPhoto"))
fun InputMediaPhoto(
    file: InputFile,
    entities: TextSourcesList
) = TelegramMediaPhoto(file, entities.makeString(), null, entities.toRawMessageEntities())

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaPhoto", "dev.inmo.tgbotapi.types.media.TelegramMediaPhoto"))
fun PhotoSize.toInputMediaPhoto(
    text: String? = null,
    parseMode: ParseMode? = null
): InputMediaPhoto = TelegramMediaPhoto(
    fileId,
    text,
    parseMode
)

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaPhoto", "dev.inmo.tgbotapi.types.media.TelegramMediaPhoto"))
fun PhotoSize.toInputMediaPhoto(
    textSources: TextSourcesList = emptyList()
): InputMediaPhoto = TelegramMediaPhoto(
    fileId,
    textSources
)
