@file:Suppress("NOTHING_TO_INLINE", "unused", "UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.abstracts.OptionallyFromUser
import dev.inmo.tgbotapi.abstracts.OptionallyWithUser
import dev.inmo.tgbotapi.abstracts.WithUser
import dev.inmo.tgbotapi.types.abstracts.WithOptionalLanguageCode
import dev.inmo.tgbotapi.types.message.abstracts.WithSenderChatMessage
import dev.inmo.tgbotapi.utils.PreviewFeature

@PreviewFeature
inline fun <T> Any.ifOptionallyFromUser(block: (OptionallyFromUser) -> T) = optionallyFromUserOrNull()?.let(block)

@PreviewFeature
inline fun Any.optionallyFromUserOrNull(): OptionallyFromUser? = this as? OptionallyFromUser

@PreviewFeature
inline fun Any.optionallyFromUserOrThrow(): OptionallyFromUser = this as OptionallyFromUser

@PreviewFeature
inline fun <T> Any.ifFromUser(block: (FromUser) -> T) = fromUserOrNull()?.let(block)

@PreviewFeature
inline fun Any.fromUserOrNull(): FromUser? = this as? FromUser

@PreviewFeature
inline fun Any.fromUserOrThrow(): FromUser = this as FromUser

@PreviewFeature
inline fun <T> Any.ifOptionallyWithUser(block: (OptionallyWithUser) -> T) = optionallyWithUserOrNull()?.let(block)

@PreviewFeature
inline fun Any.optionallyWithUserOrNull(): OptionallyWithUser? = this as? OptionallyWithUser

@PreviewFeature
inline fun Any.optionallyWithUserOrThrow(): OptionallyWithUser = this as OptionallyWithUser

@PreviewFeature
inline fun <T> Any.ifWithUser(block: (WithUser) -> T) = withUserOrNull()?.let(block)

@PreviewFeature
inline fun Any.withUserOrNull(): WithUser? = this as? WithUser

@PreviewFeature
inline fun Any.withUserOrThrow(): WithUser = this as WithUser

@PreviewFeature
inline fun <T> Any.ifWithOptionalLanguageCode(block: (WithOptionalLanguageCode) -> T) = withOptionalLanguageCodeOrNull()?.let(block)

@PreviewFeature
inline fun Any.withOptionalLanguageCodeOrNull(): WithOptionalLanguageCode? = this as? WithOptionalLanguageCode

@PreviewFeature
inline fun Any.withOptionalLanguageCodeOrThrow(): WithOptionalLanguageCode = this as WithOptionalLanguageCode

@PreviewFeature
inline fun <T> Any.ifWithSenderChatMessage(block: (WithSenderChatMessage) -> T) = withSenderChatMessageOrNull()?.let(block)

@PreviewFeature
inline fun Any.withSenderChatMessageOrNull(): WithSenderChatMessage? = this as? WithSenderChatMessage

@PreviewFeature
inline fun Any.withSenderChatMessageOrThrow(): WithSenderChatMessage = this as WithSenderChatMessage
