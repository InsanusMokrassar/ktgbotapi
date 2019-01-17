package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.TelegramMediaFile

interface MediaContent<T: TelegramMediaFile>: MessageContent, ResendableContent {
    val media: T
}
