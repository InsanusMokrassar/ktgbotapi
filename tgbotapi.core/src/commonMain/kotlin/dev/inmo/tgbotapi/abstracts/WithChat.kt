package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.PreviewChat

/**
 * All inheritors of this interface have [chat] field and related to this [chat]
 */
interface WithPreviewChat {
    val chat: PreviewChat
}

/**
 * All inheritors of this interface have [chat] field and related to this [chat]
 */
@Deprecated("Renamed", ReplaceWith("WithPreviewChat", "dev.inmo.tgbotapi.abstracts.WithPreviewChat"))
typealias WithChat = WithPreviewChat
