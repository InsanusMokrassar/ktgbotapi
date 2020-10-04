package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.files.Photo
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent

data class NewChatPhoto(
    val photo: Photo
): CommonEvent
