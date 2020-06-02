package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.MediaGroupIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ForwardInfo
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate

@Deprecated("Replaced and updated inside of TelegramBotAPI-extensions-utils")
val List<BaseMessageUpdate>.forwarded: ForwardInfo?
    get() = first().let {
        (it as? PossiblyForwardedMessage) ?.forwardInfo
    }

@Deprecated("Replaced and updated inside of TelegramBotAPI-extensions-utils")
val List<BaseMessageUpdate>.replyTo: Message?
    get() = first().let {
        (it as? PossiblyReplyMessage) ?.replyTo
    }

@Deprecated("Replaced and updated inside of TelegramBotAPI-extensions-utils")
val List<BaseMessageUpdate>.chat: Chat?
    get() = first().data.chat

@Deprecated("Replaced and updated inside of TelegramBotAPI-extensions-utils")
val List<BaseMessageUpdate>.mediaGroupId: MediaGroupIdentifier?
    get() = (first().data as? MediaGroupMessage) ?.mediaGroupId
