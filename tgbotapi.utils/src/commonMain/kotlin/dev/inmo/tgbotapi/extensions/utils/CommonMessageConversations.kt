package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.MessageContent
import dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBotCommonMessage
import kotlinx.coroutines.flow.*

/**
 * Simple factory to convert [ContentMessage] to a [CommonMessage]
 */
fun <C: MessageContent, T : ContentMessage<C>> Flow<T>.onlyCommonMessages() = filterIsInstance<CommonMessage<C>>()

/**
 * Shortcut for [onlyCommonMessages]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <C: MessageContent, T : ContentMessage<C>> Flow<T>.commonMessages() = onlyCommonMessages()

/**
 * Filter the messages and checking that incoming [CommonMessage] is [PossiblySentViaBotCommonMessage] and its
 * [PossiblySentViaBotCommonMessage.senderBot] is not null
 */
fun <MC : MessageContent, M : ContentMessage<MC>> Flow<M>.onlySentViaBot() = mapNotNull {
    if (it is PossiblySentViaBot && it.senderBot != null) {
        it
    } else {
        null
    }
}

/**
 * Filter the messages and checking that incoming [CommonMessage] not is [PossiblySentViaBotCommonMessage] or its
 * [PossiblySentViaBotCommonMessage.senderBot] is null
 */
fun <MC : MessageContent, M : ContentMessage<MC>> Flow<M>.withoutSentViaBot() = filter {
    it !is PossiblySentViaBot || it.senderBot == null
}
