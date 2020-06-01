package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PrivateChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.UsernameChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat

private const val internalLinkBeginning = "https://t.me"

@Deprecated("Replaced into TelegramBotAPI-extensions-utils project")
fun makeLinkToMessage(
    username: String,
    messageId: MessageIdentifier
): String = "$internalLinkBeginning/$username/$messageId"

private val linkIdRedundantPartRegex = Regex("^-100")
private val usernameBeginSymbolRegex = Regex("^@")

@Deprecated("Replaced into TelegramBotAPI-extensions-utils project")
fun makeLinkToMessage(
    chat: ExtendedChat,
    messageId: MessageIdentifier
): String? {
    return when {
        chat is UsernameChat && chat.username != null -> {
            "$internalLinkBeginning/${chat.username ?.username ?.replace(usernameBeginSymbolRegex, "")}/$messageId"
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
