package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseSentMessageUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

fun <T : BaseSentMessageUpdate> Flow<T>.asContentMessages() = mapNotNull {
    it.data as? ContentMessage<*>
}

fun <T : BaseSentMessageUpdate> Flow<T>.asChatEvents() = mapNotNull {
    it.data as? ChatEventMessage
}

fun <T : BaseSentMessageUpdate> Flow<T>.asUnknownMessages() = mapNotNull {
    it.data as? UnknownMessageType
}
