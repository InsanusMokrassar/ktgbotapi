@file:Suppress("NOTHING_TO_INLINE", "unused", "UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.abstracts.WithUser
import dev.inmo.tgbotapi.types.abstracts.WithOptionalLanguageCode
import dev.inmo.tgbotapi.utils.PreviewFeature

@PreviewFeature
inline fun <T> Any.ifFromUser(block: (FromUser) -> T) = fromUserOrNull()?.let(block)

@PreviewFeature
inline fun Any.fromUserOrNull(): FromUser? = this as? FromUser

@PreviewFeature
inline fun Any.fromUserOrThrow(): FromUser = this as FromUser

@PreviewFeature
inline fun <T> Any.ifWithUser(block: (WithUser) -> T) = withUserOrNull()?.let(block)

@PreviewFeature
inline fun Any.withUserOrNull(): WithUser? = this as? WithUser

@PreviewFeature
inline fun Any.withUserOrThrow(): WithUser = this as WithUser

@PreviewFeature
inline fun <T> Any.ifWithOptionalLanguageCode(block: (WithOptionalLanguageCode) -> T) =
    withOptionalLanguageCodeOrNull()?.let(block)

@PreviewFeature
inline fun Any.withOptionalLanguageCodeOrNull(): WithOptionalLanguageCode? = this as? WithOptionalLanguageCode

@PreviewFeature
inline fun Any.withOptionalLanguageCodeOrThrow(): WithOptionalLanguageCode = this as WithOptionalLanguageCode
