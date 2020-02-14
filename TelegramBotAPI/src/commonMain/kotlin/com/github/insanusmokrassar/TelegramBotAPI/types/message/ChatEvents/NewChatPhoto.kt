package com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents

import com.github.insanusmokrassar.TelegramBotAPI.types.files.Photo
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.CommonEvent

data class NewChatPhoto(
    val photo: Photo
): CommonEvent
