package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.chat.User

/**
 * All inheritors of this type have [User] in their data as one of the main data
 *
 * @see FromUser
 */
@ClassCastsIncluded(excludeRegex = ".*Impl")
interface WithUser {
    val user: User
}
