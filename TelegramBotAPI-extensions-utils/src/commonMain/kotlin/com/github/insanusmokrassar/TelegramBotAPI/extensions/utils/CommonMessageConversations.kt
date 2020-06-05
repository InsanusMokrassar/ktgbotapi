package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.CommonMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.PossiblySentViaBotCommonMessage
import kotlinx.coroutines.flow.*

/**
 * Simple factory to convert [ContentMessage] to a [CommonMessage]
 */
fun <C: MessageContent, T : ContentMessage<C>> Flow<T>.onlyCommonMessages() = filterIsInstance<CommonMessage<C>>()

/**
 * Filter the messages and checking that incoming [CommonMessage] is [PossiblySentViaBotCommonMessage] and its
 * [PossiblySentViaBotCommonMessage.senderBot] is not null
 */
fun <T : MessageContent> Flow<CommonMessage<T>>.onlySentViaBot() = mapNotNull {
    (it as? PossiblySentViaBotCommonMessage) ?.let { possiblySentViaBot ->
        if (possiblySentViaBot.senderBot != null) {
            possiblySentViaBot
        } else {
            null
        }
    }
}

/**
 * Filter the messages and checking that incoming [CommonMessage] not is [PossiblySentViaBotCommonMessage] or its
 * [PossiblySentViaBotCommonMessage.senderBot] is null
 */
fun <T : MessageContent> Flow<CommonMessage<T>>.withoutSentViaBot() = filter {
    it !is PossiblySentViaBotCommonMessage || it.senderBot == null
}
