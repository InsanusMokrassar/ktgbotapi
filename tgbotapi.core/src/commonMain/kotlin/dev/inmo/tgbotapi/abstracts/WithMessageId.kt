package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.MessageId

/**
 * All inheritors of this interface have [messageId] field and related to this [messageId]
 */
interface WithMessageId {
    val messageId: MessageId
}
