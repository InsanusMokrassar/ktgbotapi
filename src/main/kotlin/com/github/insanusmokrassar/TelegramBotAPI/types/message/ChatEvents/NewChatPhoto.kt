package com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents

import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.CommonEvent

data class NewChatPhoto(
    val photo: List<PhotoSize>
): CommonEvent
