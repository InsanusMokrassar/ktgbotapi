package dev.inmo.tgbotapi.types.message.content.abstracts

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.Message

interface ResendableContent {
    fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean = false,
        replyToMessageId: MessageIdentifier? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: KeyboardMarkup? = null
    ): Request<out Message>
}