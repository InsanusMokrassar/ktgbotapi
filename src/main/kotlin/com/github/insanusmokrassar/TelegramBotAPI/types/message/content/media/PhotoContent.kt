package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.*

data class PhotoContent(
    override val media: List<PhotoSize>,
    override val caption: String? = null,
    override val captionEntities: List<MessageEntity> = emptyList()
) : MediaCollectionContent<PhotoSize>, CaptionedMediaContent, MediaGroupContent
