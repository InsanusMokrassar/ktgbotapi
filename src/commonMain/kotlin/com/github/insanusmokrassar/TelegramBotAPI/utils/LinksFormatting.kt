package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PrivateChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.UsernameChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.*

private const val internalLinkBeginning = "https://t.me"

@PreviewFeature
fun makeLinkToMessage(
    username: String,
    messageId: MessageIdentifier
): String = "$internalLinkBeginning/$username/$messageId"

private val linkIdRedundantPartRegex = Regex("^-100")

@PreviewFeature
fun makeLinkToMessage(
    chat: ExtendedChat,
    messageId: MessageIdentifier
): String? {
    return when {
        chat is UsernameChat && chat.username != null -> "$internalLinkBeginning/${chat.username}/$messageId"
        chat !is PrivateChat -> chat.id.chatId.toString().replace(
            linkIdRedundantPartRegex,
            ""
        ).let { bareId ->
            "$internalLinkBeginning/c/$bareId/$messageId"
        }
        else -> return null
    }
}

@PreviewFeature
fun makeFileLink(
    botToken: String,
    filePath: String
) = "https://api.telegram.org/file/bot$botToken/$filePath"
