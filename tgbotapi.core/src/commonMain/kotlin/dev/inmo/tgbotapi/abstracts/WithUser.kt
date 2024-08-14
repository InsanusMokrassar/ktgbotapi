package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.chat.User

/**
 * All inheritors of this type **may** have [User] in their data as one of the main data
 *
 * @see OptionallyFromUser
 */
@ClassCastsIncluded(excludeRegex = ".*Impl")
interface OptionallyWithUser {
    val user: User?
}
/**
 * All inheritors of this type **must** have [User] in their data as one of the main data
 *
 * @see FromUser
 */
interface WithUser : OptionallyWithUser {
    override val user: User
}
