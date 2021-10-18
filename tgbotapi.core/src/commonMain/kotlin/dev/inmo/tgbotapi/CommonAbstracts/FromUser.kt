package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.User

/**
 * Inheritors of this interface have some [User] as a source of data. For example, any [dev.inmo.tgbotapi.types.CallbackQuery.CallbackQuery]
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
