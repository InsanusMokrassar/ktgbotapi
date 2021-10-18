package dev.inmo.tgbotapi.extensions.utils.formatting

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.link
import dev.inmo.tgbotapi.types.chat.abstracts.*
import dev.inmo.tgbotapi.types.chat.abstracts.extended.ExtendedPublicChat
import dev.inmo.tgbotapi.types.message.abstracts.Message

private const val internalLinkBeginning = "https://t.me"

fun makeUsernameLink(username: String) = "$internalLinkBeginning/$username"
inline val Username.link
    get() = makeUsernameLink(username)
inline fun makeLink(username: Username) = username.link

fun makeLinkToMessage(
    username: String,
    messageId: MessageIdentifier
): String = "$internalLinkBeginning/$username/$messageId"
fun makeLinkToMessage(
    username: Username,
    messageId: MessageIdentifier
): String = makeLinkToMessage(username.username, messageId)
fun makeLinkToMessage(
    chat: UsernameChat,
    messageId: MessageIdentifier
): String? = chat.username ?.let { makeLinkToMessage(it, messageId) }

private val linkIdRedundantPartRegex = Regex("^-100")
private val usernameBeginSymbolRegex = Regex("^@")

/**
 * Link which can be used as by any user to get access to [Message]. Returns null in case when there are no
 * known way to build link (for [PrivateChat]s, for example)
 */
fun makeLinkToMessage(
    chat: Chat,
    messageId: MessageIdentifier
): String? {
    return when {
        chat is UsernameChat && chat.username != null -> {
            "$internalLinkBeginning/${chat.username ?.username ?.replace(
                usernameBeginSymbolRegex, "")}/$messageId"
        }
        chat !is PrivateChat -> chat.id.chatId.toString().replace(
            linkIdRedundantPartRegex,
            ""
        ).let { bareId ->
            "$internalLinkBeginning/c/$bareId/$messageId"
        }
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
            username ?.username ?.let { return it }
        }
        if (this is ExtendedPublicChat) {
            inviteLink ?.let { return it }
        }
        if (this is PrivateChat) {
            return id.link
        }
        return null
    }

private const val stickerSetAddingLinkPrefix = "$internalLinkBeginning/addstickers"

val StickerSetName.stickerSetLink
    get() = link(this, "$stickerSetAddingLinkPrefix/$this")
