package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.files.Photo
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonGroupEvent

data class NewChatPhoto(
    val photo: Photo
): CommonGroupEvent
