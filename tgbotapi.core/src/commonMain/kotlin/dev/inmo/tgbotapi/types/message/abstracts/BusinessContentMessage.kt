package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.abstracts.types.WithBusinessConnectionId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.chat.Bot
import dev.inmo.tgbotapi.types.chat.PreviewBot
import dev.inmo.tgbotapi.types.chat.PreviewBusinessChat
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface BusinessContentMessage<T : MessageContent> :
    PossiblySentViaBotCommonMessage<T>,
    FromUserMessage,
    WithBusinessConnectionId {
    override val chat: PreviewBusinessChat
    override val businessConnectionId: BusinessConnectionId
    val senderBusinessBot: PreviewBot?

    /**
     * Currently, there are only 1-1 business chats and any message in the [chat] sent not by [PreviewBusinessChat.original]
     * must be sent by bot or user
     */
    val sentByBusinessConnectionOwner: Boolean
        get() = chat.original.id != from.id && from !is Bot
}
