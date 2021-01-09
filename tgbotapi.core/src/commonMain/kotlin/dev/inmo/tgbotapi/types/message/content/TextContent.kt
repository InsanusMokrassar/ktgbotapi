package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.SendTextMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.fullListOfSubSource
import dev.inmo.tgbotapi.utils.internal.toMarkdownTexts

data class TextContent(
    override val text: String,
    override val textEntities: List<TextPart> = emptyList()
) : MessageContent, TextedInput {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<TextContent>> = SendTextMessage(
        chatId,
        toHtmlTexts().first(),
        HTMLParseMode,
        false,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    override fun createResends(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): List<Request<ContentMessage<TextContent>>> = createResends(
        chatId,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup,
        HTMLParseMode
    )

    fun createResends(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
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
            allowSendingWithoutReply,
            replyMarkup
        )
    }
}
