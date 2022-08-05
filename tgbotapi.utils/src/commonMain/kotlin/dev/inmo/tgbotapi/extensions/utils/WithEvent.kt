@file:Suppress("UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.abstracts.*

inline fun <reified T : ChatEvent> ChatEventMessage<*>.withEvent() = if (chatEvent is T) { this as ChatEventMessage<T> } else { null }
inline fun <reified T : ChatEvent> ChatEventMessage<*>.requireWithEvent() = withEvent<T>()!!

inline fun <reified T : GroupEvent> GroupEventMessage<*>.withEvent() = if (chatEvent is T) { this as GroupEventMessage<T> } else { null }
inline fun <reified T : GroupEvent> GroupEventMessage<*>.requireWithEvent() = withEvent<T>()!!

inline fun <reified T : SupergroupEvent> SupergroupEventMessage<*>.withEvent() = if (chatEvent is T) { this as SupergroupEventMessage<T> } else { null }
inline fun <reified T : SupergroupEvent> SupergroupEventMessage<*>.requireWithEvent() = withEvent<T>()!!

inline fun <reified T : PrivateEvent> PrivateEventMessage<*>.withEvent() = if (chatEvent is T) { this as PrivateEventMessage<T> } else { null }
inline fun <reified T : PrivateEvent> PrivateEventMessage<*>.requireWithEvent() = withEvent<T>()!!

inline fun <reified T : ChannelEvent> ChannelEventMessage<*>.withEvent() = if (chatEvent is T) { this as ChannelEventMessage<T> } else { null }
inline fun <reified T : ChannelEvent> ChannelEventMessage<*>.requireWithEvent() = withEvent<T>()!!

inline fun <reified T : GroupEvent> CommonGroupEventMessage<*>.withEvent() = if (chatEvent is T) { this as CommonGroupEventMessage<T> } else { null }
inline fun <reified T : GroupEvent> CommonGroupEventMessage<*>.requireWithEvent() = withEvent<T>()!!

inline fun <reified T : SupergroupEvent> CommonSupergroupEventMessage<*>.withEvent() = if (chatEvent is T) { this as CommonSupergroupEventMessage<T> } else { null }
inline fun <reified T : SupergroupEvent> CommonSupergroupEventMessage<*>.requireWithEvent() = withEvent<T>()!!
