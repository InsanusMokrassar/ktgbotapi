package dev.inmo.tgbotapi.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseSentMessageUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

/**
 * Will map incoming [BaseSentMessageUpdate]s to [ContentMessage] from [BaseSentMessageUpdate.data]
 */
fun <T : BaseSentMessageUpdate> Flow<T>.asContentMessagesFlow() = mapNotNull {
    it.data as? ContentMessage<*>
}

/**
 * Will map incoming [BaseSentMessageUpdate]s to [CommonMessage] from [BaseSentMessageUpdate.data]
 */
fun <T : BaseSentMessageUpdate> Flow<T>.asCommonMessagesFlow() = mapNotNull {
    it.data as? CommonMessage<*>
}

/**
 * Will map incoming [BaseSentMessageUpdate]s to [ChatEventMessage] from [BaseSentMessageUpdate.data]
 */
fun <T : BaseSentMessageUpdate> Flow<T>.asChatEventsFlow() = mapNotNull {
    it.data as? ChatEventMessage
}

/**
 * Will map incoming [BaseSentMessageUpdate]s to [UnknownMessageType] from [BaseSentMessageUpdate.data]
 */
fun <T : BaseSentMessageUpdate> Flow<T>.asUnknownMessagesFlow() = mapNotNull {
    it.data as? UnknownMessageType
}
