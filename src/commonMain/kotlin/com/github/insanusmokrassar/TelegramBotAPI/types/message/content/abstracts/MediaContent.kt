package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.InputMedia
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.TelegramMediaFile

interface MediaContent: MessageContent {
    val media: TelegramMediaFile
    fun asInputMedia(): InputMedia
}
