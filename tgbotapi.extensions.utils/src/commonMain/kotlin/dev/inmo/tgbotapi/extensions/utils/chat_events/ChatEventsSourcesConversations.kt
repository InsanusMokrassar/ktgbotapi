package dev.inmo.tgbotapi.extensions.utils.chat_events

import dev.inmo.tgbotapi.extensions.utils.shortcuts.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlin.reflect.KClass

@Deprecated("Refactored, replaced and renamed", ReplaceWith("filterByChatEvent", "dev.inmo.tgbotapi.extensions.utils.shortcuts.filterByChatEvent"))
fun <T : ChatEventMessage<*>> Flow<ChatEventMessage<*>>.divideBySource(contentType: KClass<T>) = mapNotNull {
    if (contentType.isInstance(it)) {
        @Suppress("UNCHECKED_CAST")
        it as T
    } else {
        null
    }
}

@Deprecated("Replaced and renamed", ReplaceWith("channelEvents", "dev.inmo.tgbotapi.extensions.utils.shortcuts.channelEvents"))
fun Flow<ChatEventMessage<*>>.onlyChannelEvents() = channelEvents()
@Deprecated("Replaced and renamed", ReplaceWith("groupEvents", "dev.inmo.tgbotapi.extensions.utils.shortcuts.groupEvents"))
fun Flow<ChatEventMessage<*>>.onlyGroupEvents() = groupEvents()
@Deprecated("Replaced and renamed", ReplaceWith("supergroupEvents", "dev.inmo.tgbotapi.extensions.utils.shortcuts.supergroupEvents"))
fun Flow<ChatEventMessage<*>>.onlySupergroupEvents() = supergroupEvents()
