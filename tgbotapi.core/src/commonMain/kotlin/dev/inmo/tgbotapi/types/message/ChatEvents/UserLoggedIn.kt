package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent

data class UserLoggedIn(
    val domain: String,
) : PrivateEvent
