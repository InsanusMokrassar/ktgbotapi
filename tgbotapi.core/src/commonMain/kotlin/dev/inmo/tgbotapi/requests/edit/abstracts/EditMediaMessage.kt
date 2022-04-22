package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.types.media.TelegramMedia

interface EditMediaMessage {
    val media: TelegramMedia
}
