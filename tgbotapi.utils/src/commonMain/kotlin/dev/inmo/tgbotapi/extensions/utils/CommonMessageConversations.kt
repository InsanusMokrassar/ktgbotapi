package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MessageContent
import kotlinx.coroutines.flow.*

/**
 * Simple factory to convert [ContentMessage] to a [ChatContentMessage]
 */
fun <C: MessageContent, T : ContentMessage<C>> Flow<T>.onlyCommonMessages() = filterIsInstance<ChatContentMessage<C>>()

/**
 * Shortcut for [onlyCommonMessages]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <C: MessageContent, T : ContentMessage<C>> Flow<T>.commonMessages() = onlyCommonMessages()

/**
 * Filter the messages and checking that incoming [ChatContentMessage] is [PossiblySentViaBotCommonMessage] and its
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
 * Filter the messages and checking that incoming [ChatContentMessage] not is [PossiblySentViaBotCommonMessage] or its
 * [PossiblySentViaBotCommonMessage.senderBot] is null
 */
fun <MC : MessageContent, M : ContentMessage<MC>> Flow<M>.withoutSentViaBot() = filter {
    it !is PossiblySentViaBot || it.senderBot == null
}

/**
 * Filter the messages and checking that incoming [ContentMessage.content] is not [MediaGroupContent]
 */
fun <MC : MessageContent, M : ContentMessage<MC>> Flow<M>.withoutMediaGroups() = filter {
    it.content !is MediaGroupContent<*>
}
