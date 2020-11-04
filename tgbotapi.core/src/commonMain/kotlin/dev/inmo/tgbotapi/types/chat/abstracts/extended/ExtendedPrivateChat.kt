package dev.inmo.tgbotapi.types.chat.abstracts.extended

import dev.inmo.tgbotapi.types.chat.abstracts.PrivateChat

interface ExtendedPrivateChat : PrivateChat, ExtendedChat {
    val bio: String
}
