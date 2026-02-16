package dev.inmo.tgbotapi.utils

import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.toChatId

/**
 * Trying to convert current [PrivateChat] to [User]
 *
 * * If [this] actually is [User] or some [Bot], will return this as is
 * * If [this] is some [PreviewPrivateChat], will create new [CommonUser]
 *
 * !!!WARNING!!! The returned [User] CAN BE NOT EQUAL to user from some
 * [dev.inmo.tgbotapi.types.message.abstracts.ContentMessage] due to absence of some fields (like premium flag or
 * language)
 */
fun PrivateChat.toUser(): User = when (this) {
    is CommonUser -> this
    is CommonBot -> this
    is ExtendedBot -> this
    is ExtendedPrivateChatImpl -> CommonUser(id, firstName, lastName, username)
    is PrivateChatImpl -> CommonUser(id, firstName, lastName, username)
    is ExtendedPrivateForumChatImpl -> CommonUser(id.toChatId(), firstName, lastName, username)
    is PrivateForumChatImpl -> CommonUser(id.toChatId(), firstName, lastName, username)
}
