package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.media.TelegramMediaVideo
import dev.inmo.tgbotapi.utils.extensions.makeString

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaVideo", "dev.inmo.tgbotapi.types.media.TelegramMediaVideo"))
fun InputMediaVideo(
    file: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Long? = null,
    thumb: InputFile? = null
) = TelegramMediaVideo(file, text, parseMode, null, width, height, duration, thumb)

fun InputMediaVideo(
    file: InputFile,
    entities: TextSourcesList,
    width: Int? = null,
    height: Int? = null,
    duration: Long? = null,
    thumb: InputFile? = null
) = TelegramMediaVideo(file, entities.makeString(), null, entities.toRawMessageEntities(), width, height, duration, thumb)
