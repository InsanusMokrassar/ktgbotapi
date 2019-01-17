package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage

interface ResendableContent {
    fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean = false,
        replyToMessageId: MessageIdentifier? = null,
        replyMarkup: KeyboardMarkup? = null
    ): Request<RawMessage>
}