package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.formatting

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PrivateChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.UsernameChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat
import com.github.insanusmokrassar.TelegramBotAPI.utils.link

private const val internalLinkBeginning = "https://t.me"

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

fun makeLinkToMessage(
    chat: ExtendedChat,
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

private const val stickerSetAddingLinkPrefix = "$internalLinkBeginning/addstickers"

/**
 * Create a link for adding of sticker set with name [stickerSetName]. Was added thanks to user Djaler and based on
 * https://github.com/Djaler/evil-bot/blob/master/src/main/kotlin/com/github/djaler/evilbot/utils/StickerUtils.kt#L6-L8
 *
 * @see [makeLinkToAddStickerSetInMarkdownV2]
 * @see [makeLinkToAddStickerSetInMarkdown]
 * @see [makeLinkToAddStickerSetInHtml]
 */
fun makeLinkToAddStickerSet(
    stickerSetName: StickerSetName,
    parseMode: ParseMode
) = (stickerSetName to "$stickerSetAddingLinkPrefix/$stickerSetName").link(
    parseMode
)

/**
 * @return Link for adding of sticker set with name [stickerSetName] with formatting for [MarkdownV2]
 */
fun makeLinkToAddStickerSetInMarkdownV2(stickerSetName: StickerSetName) =
    makeLinkToAddStickerSet(
        stickerSetName,
        MarkdownV2
    )
/**
 * @return Link for adding of sticker set with name [stickerSetName] with formatting for [Markdown]
 */
fun makeLinkToAddStickerSetInMarkdown(stickerSetName: StickerSetName) =
    makeLinkToAddStickerSet(
        stickerSetName,
        Markdown
    )
/**
 * @return Link for adding of sticker set with name [stickerSetName] with formatting for [HTML]
 */
fun makeLinkToAddStickerSetInHtml(stickerSetName: StickerSetName) =
    makeLinkToAddStickerSet(
        stickerSetName,
        HTML
    )
