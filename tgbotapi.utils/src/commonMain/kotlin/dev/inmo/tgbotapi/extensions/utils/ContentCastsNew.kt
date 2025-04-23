@file:Suppress("UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.MessageContent

inline fun <reified T : MessageContent> ContentMessage<*>.withContentOrNull() = if (content is T) {
    this as ContentMessage<T>
} else {
    null
}

inline fun <reified T : MessageContent> ContentMessage<*>.withContentOrThrow() = withContentOrNull<T>()!!

inline fun <reified T : MessageContent> CommonMessage<*>.withContentOrNull() = if (content is T) {
    this as CommonMessage<T>
} else {
    null
}

inline fun <reified T : MessageContent> CommonMessage<*>.withContentOrThrow() = withContentOrNull<T>()!!

inline fun <reified T : MessageContent> PossiblySentViaBotCommonMessage<*>.withContentOrNull() = if (content is T) {
    this as PossiblySentViaBotCommonMessage<T>
} else {
    null
}

inline fun <reified T : MessageContent> PossiblySentViaBotCommonMessage<*>.withContentOrThrow() = withContentOrNull<T>()!!

inline fun <reified T : MessageContent> ChannelContentMessage<*>.withContentOrNull() = if (content is T) {
    this as ChannelContentMessage<T>
} else {
    null
}

inline fun <reified T : MessageContent> ChannelContentMessage<*>.withContentOrThrow() = withContentOrNull<T>()!!

inline fun <reified T : MessageContent> PrivateContentMessage<*>.withContentOrNull() = if (content is T) {
    this as PrivateContentMessage<T>
} else {
    null
}

inline fun <reified T : MessageContent> PrivateContentMessage<*>.withContentOrThrow() = withContentOrNull<T>()!!

inline fun <reified T : MessageContent> PublicContentMessage<*>.withContentOrNull() = if (content is T) {
    this as PublicContentMessage<T>
} else {
    null
}

inline fun <reified T : MessageContent> PublicContentMessage<*>.withContentOrThrow() = withContentOrNull<T>()!!

inline fun <reified T : MessageContent> GroupContentMessage<*>.withContentOrNull() = if (content is T) {
    this as GroupContentMessage<T>
} else {
    null
}

inline fun <reified T : MessageContent> GroupContentMessage<*>.withContentOrThrow() = withContentOrNull<T>()!!

inline fun <reified T : MessageContent> FromChannelGroupContentMessage<*>.withContentOrNull() = if (content is T) {
    this as FromChannelGroupContentMessage<T>
} else {
    null
}

inline fun <reified T : MessageContent> FromChannelGroupContentMessage<*>.withContentOrThrow() = withContentOrNull<T>()!!

inline fun <reified T : MessageContent> AnonymousGroupContentMessage<*>.withContentOrNull() = if (content is T) {
    this as AnonymousGroupContentMessage<T>
} else {
    null
}

inline fun <reified T : MessageContent> AnonymousGroupContentMessage<*>.withContentOrThrow() = withContentOrNull<T>()!!

inline fun <reified T : MessageContent> CommonGroupContentMessage<*>.withContentOrNull() = if (content is T) {
    this as CommonGroupContentMessage<T>
} else {
    null
}

inline fun <reified T : MessageContent> CommonGroupContentMessage<*>.withContentOrThrow() = withContentOrNull<T>()!!
