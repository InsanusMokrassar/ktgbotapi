package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.files.VoiceFile
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.CaptionedMediaContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaContent

data class VoiceContent(
    override val media: VoiceFile,
    override val caption: String? = null,
    override val captionEntities: List<MessageEntity> = emptyList()
) : MediaContent<VoiceFile>, CaptionedMediaContent
