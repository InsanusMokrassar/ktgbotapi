package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.chat.User

/**
 * Inheritors of this interface have some [User] as a source of data. For example, any [dev.inmo.tgbotapi.types.queries.callback.CallbackQuery]
 * have [User] as the source of that query
 */
interface FromUser : WithUser {
    /**
     * The source [User] of this type
     */
    val from: User
    override val user: User
        get() = from
}
