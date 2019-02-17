package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.chat.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ForwardedMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate

val List<BaseMessageUpdate>.forwarded: ForwardedMessage?
    get() = first().let {
        (it as? AbleToBeForwardedMessage) ?.forwarded
    }

val List<BaseMessageUpdate>.replyTo: Message?
    get() = first().let {
        (it as? AbleToReplyMessage) ?.replyTo
    }

val List<BaseMessageUpdate>.chat: Chat?
    get() = first().data.chat
