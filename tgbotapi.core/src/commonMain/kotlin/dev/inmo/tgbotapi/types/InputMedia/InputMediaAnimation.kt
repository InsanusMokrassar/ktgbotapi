package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageEntity.toRawMessageEntities
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.media.TelegramMediaAnimation
import dev.inmo.tgbotapi.utils.extensions.makeString

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaAnimation", "dev.inmo.tgbotapi.types.media.TelegramMediaAnimation"))
fun InputMediaAnimation(
    file: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Long? = null,
    thumb: InputFile? = null
) = TelegramMediaAnimation(file, text, parseMode, null, width, height, duration, thumb)

@Deprecated("Replaced and renamed", ReplaceWith("TelegramMediaAnimation", "dev.inmo.tgbotapi.types.media.TelegramMediaAnimation"))
fun InputMediaAnimation(
    file: InputFile,
    entities: TextSourcesList,
    width: Int? = null,
    height: Int? = null,
    duration: Long? = null,
    thumb: InputFile? = null
) = TelegramMediaAnimation(
    file,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    width,
    height,
    duration,
    thumb
)

