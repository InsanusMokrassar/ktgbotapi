package com.github.insanusmokrassar.TelegramBotAPI.types.message.content

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.SendMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.polls.Poll
import com.github.insanusmokrassar.TelegramBotAPI.utils.toHtmlTexts
import com.github.insanusmokrassar.TelegramBotAPI.utils.toMarkdownTexts

data class PollContent(
    val poll: Poll
) : MessageContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<RawMessage> = TODO()
}
