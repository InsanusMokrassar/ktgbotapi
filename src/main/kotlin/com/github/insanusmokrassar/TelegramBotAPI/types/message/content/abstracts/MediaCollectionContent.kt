package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.TelegramMediaFile

interface MediaCollectionContent<T: TelegramMediaFile>: MessageContent {
    val media: List<T>
}
