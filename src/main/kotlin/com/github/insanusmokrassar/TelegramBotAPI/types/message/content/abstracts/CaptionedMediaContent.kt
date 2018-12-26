package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity

interface CaptionedMediaContent {
    val caption: String?
    val captionEntities: List<MessageEntity>
}