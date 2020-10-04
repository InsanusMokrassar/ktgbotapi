package dev.inmo.tgbotapi.types.chat.abstracts

interface PrivateChat : Chat, UsernameChat {
    val firstName: String
    val lastName: String
}
