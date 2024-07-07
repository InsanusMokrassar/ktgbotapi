package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.files.PhotoFile
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent

data class NewChatPhoto(
    val photo: PhotoFile
): PublicChatEvent
