package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.chat.Chat

/**
 * All inheritors of this interface have [chat] field and related to this [chat]
 */
interface WithChat {
    val chat: Chat
}
