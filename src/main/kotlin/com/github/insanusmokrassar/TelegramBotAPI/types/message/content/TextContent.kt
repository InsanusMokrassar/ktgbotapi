package com.github.insanusmokrassar.TelegramBotAPI.types.message.content

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.SendMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.MarkdownParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.toMarkdownText
import com.github.insanusmokrassar.TelegramBotAPI.utils.toMarkdownTexts

data class TextContent(
    val text: String,
    val entities: List<MessageEntity> = emptyList()
) : MessageContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<RawMessage> = SendMessage(
        chatId,
        toMarkdownTexts().first(),
        MarkdownParseMode,
        false,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    override fun createResends(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): List<Request<RawMessage>> = toMarkdownTexts().map {
        SendMessage(
            chatId,
            it,
            MarkdownParseMode,
            false,
            disableNotification,
            replyToMessageId,
            replyMarkup
        )
    }
}
