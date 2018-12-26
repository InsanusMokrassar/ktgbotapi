package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaGroupContent

interface MediaGroupMessage<T: MediaGroupContent> {
    val mediaGroupId: String
    val content: T
}
