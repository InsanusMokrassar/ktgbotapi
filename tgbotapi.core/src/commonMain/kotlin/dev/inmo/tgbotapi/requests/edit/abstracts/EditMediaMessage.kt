package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.types.InputMedia.InputMedia

interface EditMediaMessage {
    val media: InputMedia
}