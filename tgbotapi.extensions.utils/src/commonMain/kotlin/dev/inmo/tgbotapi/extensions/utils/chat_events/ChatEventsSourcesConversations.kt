package dev.inmo.tgbotapi.extensions.utils.chat_events

import dev.inmo.tgbotapi.extensions.utils.shortcuts.*
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent
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

@Deprecated("Replaced and renamed", ReplaceWith("allChannelEvents", "dev.inmo.tgbotapi.extensions.utils.shortcuts.allChannelEvents"))
fun Flow<ChatEventMessage<*>>.onlyChannelEvents() = allChannelEvents()
@Deprecated("Replaced and renamed", ReplaceWith("allGroupEvents", "dev.inmo.tgbotapi.extensions.utils.shortcuts.allGroupEvents"))
fun Flow<ChatEventMessage<*>>.onlyGroupEvents() = allGroupEvents()
@Deprecated("Replaced and renamed", ReplaceWith("allSupergroupEvents", "dev.inmo.tgbotapi.extensions.utils.shortcuts.allSupergroupEvents"))
fun Flow<ChatEventMessage<*>>.onlySupergroupEvents() = allSupergroupEvents()
