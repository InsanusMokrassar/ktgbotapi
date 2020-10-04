package dev.inmo.tgbotapi.types.chat.abstracts

import dev.inmo.tgbotapi.types.Username

interface UsernameChat : Chat {
    val username: Username?
}
