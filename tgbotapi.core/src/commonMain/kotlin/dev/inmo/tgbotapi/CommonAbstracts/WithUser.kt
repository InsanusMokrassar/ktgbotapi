package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.User

/**
 * All inheritors of this type have [User] in their data as one of the main data
 *
 * @see FromUser
 */
interface WithUser {
    val user: User
}
