@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.utils.updates

import dev.inmo.tgbotapi.types.message.PassportMessage
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

/**
 * Will map incoming [BaseSentMessageUpdate]s to [ContentMessage] from [BaseSentMessageUpdate.data]
 */
fun <T : BaseSentMessageUpdate> Flow<T>.asContentMessagesFlow() =
    mapNotNull {
        it.data as? ContentMessage<*>
    }

/**
 * Will map incoming [BaseSentMessageUpdate]s to [CommonMessage] from [BaseSentMessageUpdate.data]
 */
fun <T : BaseSentMessageUpdate> Flow<T>.asCommonMessagesFlow() =
    mapNotNull {
        it.data as? CommonMessage<*>
    }

@Suppress("NOTHING_TO_INLINE")
inline fun <T : BaseSentMessageUpdate> Flow<T>.chatEvents() =
    mapNotNull {
        it.data as? ChatEventMessage<*>
    }

@Suppress("NOTHING_TO_INLINE")
inline fun <T : BaseSentMessageUpdate> Flow<T>.passportMessages() =
    mapNotNull {
        it.data as? PassportMessage
    }

/**
 * Will map incoming [BaseSentMessageUpdate]s to [UnknownMessageType] from [BaseSentMessageUpdate.data]
 */
fun <T : BaseSentMessageUpdate> Flow<T>.asUnknownMessagesFlow() =
    mapNotNull {
        it.data as? UnknownMessageType
    }
