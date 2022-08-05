@file:Suppress("UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MessageContent

inline fun <reified T : MessageContent> ContentMessage<*>.withContent() = if (content is T) { this as ContentMessage<T> } else { null }
inline fun <reified T : MessageContent> ContentMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> CommonMessage<*>.withContent() = if (content is T) { this as CommonMessage<T> } else { null }
inline fun <reified T : MessageContent> CommonMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> PossiblySentViaBotCommonMessage<*>.withContent() = if (content is T) { this as PossiblySentViaBotCommonMessage<T>
} else { null }
inline fun <reified T : MessageContent> PossiblySentViaBotCommonMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> ChannelContentMessage<*>.withContent() = if (content is T) { this as ChannelContentMessage<T> } else { null }
inline fun <reified T : MessageContent> ChannelContentMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> PrivateContentMessage<*>.withContent() = if (content is T) { this as PrivateContentMessage<T> } else { null }
inline fun <reified T : MessageContent> PrivateContentMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> PublicContentMessage<*>.withContent() = if (content is T) { this as PublicContentMessage<T> } else { null }
inline fun <reified T : MessageContent> PublicContentMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> GroupContentMessage<*>.withContent() = if (content is T) { this as GroupContentMessage<T> } else { null }
inline fun <reified T : MessageContent> GroupContentMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> FromChannelGroupContentMessage<*>.withContent() = if (content is T) { this as FromChannelGroupContentMessage<T> } else { null }
inline fun <reified T : MessageContent> FromChannelGroupContentMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> AnonymousGroupContentMessage<*>.withContent() = if (content is T) { this as AnonymousGroupContentMessage<T> } else { null }
inline fun <reified T : MessageContent> AnonymousGroupContentMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MessageContent> CommonGroupContentMessage<*>.withContent() = if (content is T) { this as CommonGroupContentMessage<T> } else { null }
inline fun <reified T : MessageContent> CommonGroupContentMessage<*>.requireWithContent() = withContent<T>()!!

inline fun <reified T : MediaGroupContent> MediaGroupMessage<*>.withContent() = if (content is T) { this as MediaGroupMessage<T> } else { null }
inline fun <reified T : MediaGroupContent> MediaGroupMessage<*>.requireWithContent() = withContent<T>()!!
