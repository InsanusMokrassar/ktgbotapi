package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.chat.User

/**
 * Inheritors of this interface **may** have some [User] as a source of data. For example, any [dev.inmo.tgbotapi.types.queries.callback.CallbackQuery]
 * have [User] as the source of that query
 */
interface OptionallyFromUser : OptionallyWithUser {
    /**
     * The source [User] of this type
     */
    val from: User?
    override val user: User?
        get() = from
}

/**
 * Inheritors of this interface **must** have some [User] as a source of data. For example, any [dev.inmo.tgbotapi.types.queries.callback.CallbackQuery]
 * have [User] as the source of that query
 */
interface FromUser : OptionallyFromUser, WithUser {
    /**
     * The source [User] of this type
     */
    override val from: User
    override val user: User
        get() = from
}
