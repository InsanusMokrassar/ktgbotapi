package dev.inmo.tgbotapi.extensions.utils.formatting

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.textsources.link
import io.ktor.http.encodeURLQueryComponent


fun makeUsernameLink(username: String) = "$internalLinkBeginning/$username"
fun makeUsernameDeepLinkPrefix(username: String) = "${makeUsernameLink(username)}?start="
fun makeUsernameStartattachPrefix(username: String) = "$internalLinkBeginning/$username?startattach"
fun makeUsernameStartattachLink(username: String, data: String? = null) = "${makeUsernameStartattachPrefix(username)}${data?.let { "=$it" } ?: ""}"
inline val Username.link
    get() = makeUsernameLink(usernameWithoutAt)
inline val Username.deepLinkPrefix
    get() = makeUsernameDeepLinkPrefix(usernameWithoutAt)
inline val Username.startattachPrefix
    get() = makeUsernameStartattachPrefix(usernameWithoutAt)
inline fun makeLink(username: Username) = username.link
inline fun makeTelegramDeepLink(username: String, startParameter: String) = "${makeUsernameDeepLinkPrefix(username)}$startParameter".encodeURLQueryComponent()
inline fun makeTelegramStartattach(username: String, data: String? = null) = makeUsernameStartattachLink(username, data)
inline fun makeDeepLink(username: Username, startParameter: String) = makeTelegramDeepLink(username.usernameWithoutAt, startParameter)
inline fun makeTelegramDeepLink(username: Username, startParameter: String) = makeDeepLink(username, startParameter)
inline fun makeTelegramStartattach(username: Username, data: String? = null) = makeTelegramStartattach(username.usernameWithoutAt, data)

private val linkIdRedundantPartRegex = Regex("^-100")
private val usernameBeginSymbolRegex = Regex("^@")

fun makeLinkToMessage(
    username: String,
    messageId: MessageId,
    threadId: MessageThreadId? = null
): String = "$internalLinkBeginning/${username.replace(usernameBeginSymbolRegex, "")}/${threadId ?.let { "$it/" } ?: ""}$messageId"
fun makeLinkToMessage(
    username: Username,
    messageId: MessageId,
    threadId: MessageThreadId? = null
): String = makeLinkToMessage(username.username, messageId, threadId)
fun makeLinkToMessage(
    chatId: Identifier,
    messageId: MessageId,
    threadId: MessageThreadId? = null
): String = chatId.toString().replace(
    linkIdRedundantPartRegex,
    ""
).let { bareId ->
    "$internalLinkBeginning/c/$bareId//${threadId ?.let { "$it/" } ?: ""}$messageId"
}
fun makeLinkToMessage(
    chatId: IdChatIdentifier,
    messageId: MessageId,
): String = makeLinkToMessage(chatId.chatId, messageId, chatId.threadId)

/**
 * Link which can be used as by any user to get access to [Message]. Returns null in case when there are no
 * known way to build link (for [PrivateChat]s, for example)
 */
fun makeLinkToMessage(
    chat: Chat,
    messageId: MessageId
): String? {
    return when {
        chat is UsernameChat && chat.username != null -> chat.username ?.let {
            makeLinkToMessage(it, messageId, chat.id.threadId)
        } ?: makeLinkToMessage(chat.id, messageId)
        chat !is PrivateChat -> makeLinkToMessage(chat.id, messageId)
        else -> return null
    }
}

/**
 * @see makeLinkToMessage
 */
val Message.link: String?
    get() = makeLinkToMessage(
        chat,
        messageId
    )

/**
 * Link which can be used as by any user to get access to [Chat]. Returns null in case when there are no
 * known way to build link
 */
val Chat.link: String?
    get() {
        if (this is UsernameChat) {
            username ?.link
        }
        if (this is ExtendedPublicChat) {
            inviteLink ?.let { return it }
        }
        if (this is PrivateChat) {
            return id.userLink
        }
        return null
    }

private const val stickerSetAddingLinkPrefix = "$internalLinkBeginning/addstickers"

val StickerSetName.stickerSetLink
    get() = link(this, "$stickerSetAddingLinkPrefix/$this")
