package com.github.insanusmokrassar.TelegramBotAPI.types.message.content

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.FullTextSourcesList
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextPart
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.SendTextMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.*

data class TextContent(
    val text: String,
    /**
     * Not full list of entities. This list WILL NOT contain [TextPart]s with [com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.RegularTextSource]
     * @see [TextContent.fullEntitiesList]
     */
    val entities: List<TextPart> = emptyList()
) : MessageContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<TextContent>> = SendTextMessage(
        chatId,
        toHtmlTexts().first(),
        HTMLParseMode,
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
    ): List<Request<ContentMessage<TextContent>>> = createResends(
        chatId,
        disableNotification,
        replyToMessageId,
        replyMarkup,
        HTMLParseMode
    )

    fun createResends(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?,
        parseMode: ParseMode = HTMLParseMode
    ): List<Request<ContentMessage<TextContent>>> = when (parseMode) {
        is MarkdownParseMode -> toMarkdownTexts()
        is MarkdownV2ParseMode -> toMarkdownV2Texts()
        is HTMLParseMode -> toHtmlTexts()
    }.map {
        SendTextMessage(
            chatId,
            it,
            parseMode,
            false,
            disableNotification,
            replyToMessageId,
            replyMarkup
        )
    }
}

/**
 * Convert its [TextContent.entities] to list of [com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource]
 * with [com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.RegularTextSource]
 */
fun TextContent.fullEntitiesList(): FullTextSourcesList = text.fullListOfSubSource(entities).map { it.source }
