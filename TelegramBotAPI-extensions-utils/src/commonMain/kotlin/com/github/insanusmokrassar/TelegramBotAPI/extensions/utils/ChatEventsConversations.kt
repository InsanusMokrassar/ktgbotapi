package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.message.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ChatEventMessage
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

fun Flow<ChatEventMessage>.channelEvents() = divideBySource(ChannelEventMessage::class)
fun Flow<ChatEventMessage>.groupEvents() = divideBySource(GroupEventMessage::class)
fun Flow<ChatEventMessage>.supergroupEvents() = divideBySource(SupergroupEventMessage::class)
