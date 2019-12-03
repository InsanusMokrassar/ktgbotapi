package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.InputMedia

interface EditMediaMessage {
    val media: InputMedia
}