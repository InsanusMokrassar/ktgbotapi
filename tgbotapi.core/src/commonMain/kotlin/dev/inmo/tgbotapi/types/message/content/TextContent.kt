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
        textSources,
        false,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    @Deprecated(
        "Useless due to fact that createResend currently use textSource and that will guarantee correct sending of message",
        ReplaceWith("createResend")
    )
    override fun createResends(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): List<Request<ContentMessage<TextContent>>> = listOf(
        createResend(
            chatId,
            disableNotification,
            replyToMessageId,
            allowSendingWithoutReply,
            replyMarkup
        )
    )

    @Deprecated(
        "Useless due to fact that createResend currently use textSource and that will guarantee correct sending of message",
        ReplaceWith("createResend")
    )
    fun createResends(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?,
        parseMode: ParseMode = defaultParseMode
    ): List<Request<ContentMessage<TextContent>>> = createResends(chatId, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)
}
