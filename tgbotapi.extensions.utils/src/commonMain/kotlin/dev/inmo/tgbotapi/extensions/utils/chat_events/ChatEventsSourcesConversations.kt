package dev.inmo.tgbotapi.extensions.utils.chat_events

import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlin.reflect.KClass


fun <T : ChatEventMessage> Flow<ChatEventMessage>.divideBySource(contentType: KClass<T>) = mapNotNull {
    if (contentType.isInstance(it)) {
        @Suppress("UNCHECKED_CAST")
        it as T
    } else {
        null
    }
}

fun Flow<ChatEventMessage>.onlyChannelEvents() = divideBySource(ChannelEventMessage::class)
fun Flow<ChatEventMessage>.onlyGroupEvents() = divideBySource(CommonGroupEventMessage::class)
fun Flow<ChatEventMessage>.onlySupergroupEvents() = divideBySource(CommonSupergroupEventMessage::class)
