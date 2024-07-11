package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.types.media.TelegramFreeMedia

interface EditMediaMessage {
    val media: TelegramFreeMedia
}
