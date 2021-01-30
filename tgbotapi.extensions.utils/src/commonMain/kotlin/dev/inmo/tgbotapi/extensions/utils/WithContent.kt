@file:Suppress("UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*

inline fun <reified T : MessageContent> ContentMessage<*>.withContent() = if (content is T) { this as ContentMessage<T> } else { null }
inline fun <reified T : MessageContent> ContentMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> CommonMessage<*>.withContent() = if (content is T) { this as CommonMessage<T> } else { null }
inline fun <reified T : MessageContent> CommonMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> PossiblySentViaBotCommonMessage<*>.withContent() = if (content is T) { this as PossiblySentViaBotCommonMessage<T> } else { null }
inline fun <reified T : MessageContent> PossiblySentViaBotCommonMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> ChannelMessage<*>.withContent() = if (content is T) { this as ChannelMessage<T> } else { null }
inline fun <reified T : MessageContent> ChannelMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> PrivateMessage<*>.withContent() = if (content is T) { this as PrivateMessage<T> } else { null }
inline fun <reified T : MessageContent> PrivateMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> PublicMessage<*>.withContent() = if (content is T) { this as PublicMessage<T> } else { null }
inline fun <reified T : MessageContent> PublicMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> GroupMessage<*>.withContent() = if (content is T) { this as GroupMessage<T> } else { null }
inline fun <reified T : MessageContent> GroupMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> FromChannelGroupMessage<*>.withContent() = if (content is T) { this as FromChannelGroupMessage<T> } else { null }
inline fun <reified T : MessageContent> FromChannelGroupMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> AnonymousGroupMessage<*>.withContent() = if (content is T) { this as AnonymousGroupMessage<T> } else { null }
inline fun <reified T : MessageContent> AnonymousGroupMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> CommonGroupMessage<*>.withContent() = if (content is T) { this as CommonGroupMessage<T> } else { null }
inline fun <reified T : MessageContent> CommonGroupMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MediaGroupContent> MediaGroupMessage<*>.withContent() = if (content is T) { this as MediaGroupMessage<T> } else { null }
inline fun <reified T : MediaGroupContent> MediaGroupMessage<*>.requireWithContent() = withContent<T>()!!
