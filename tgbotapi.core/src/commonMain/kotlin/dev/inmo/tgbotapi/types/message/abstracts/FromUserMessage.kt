package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.User

interface FromUserMessage {
    val user: User
}