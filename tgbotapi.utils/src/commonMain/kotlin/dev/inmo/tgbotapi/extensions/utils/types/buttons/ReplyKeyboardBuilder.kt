package dev.inmo.tgbotapi.extensions.utils.types.buttons

import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.buttons.reply.requestChatReplyButton
import dev.inmo.tgbotapi.types.buttons.reply.requestUserReplyButton
import dev.inmo.tgbotapi.types.chat.member.ChatAdministratorRights
import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.types.webapps.WebAppInfo
import dev.inmo.tgbotapi.utils.*

/**
 * Core DSL part of Keyboard DSL. Can accept only [KeyboardButton] and returns ready to use
 * [ReplyKeyboardMarkup] via [build] method
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 * @see ReplyKeyboardRowBuilder
 */
typealias ReplyKeyboardBuilder = MatrixBuilder<KeyboardButton>

/**
 * Creates [InlineKeyboardMarkup] using internal [matrix]
 */
fun ReplyKeyboardBuilder.build(
    resizeKeyboard: Boolean? = null,
    oneTimeKeyboard: Boolean? = null,
    inputFieldPlaceholder: String? = null,
    selective: Boolean? = null,
    persistent: Boolean? = null,
) = ReplyKeyboardMarkup(matrix, resizeKeyboard, oneTimeKeyboard, inputFieldPlaceholder, selective, persistent)

/**
 * Row builder of [KeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
typealias ReplyKeyboardRowBuilder = RowBuilder<KeyboardButton>

/**
 * Factory-function for [ReplyKeyboardBuilder]. It will [apply] [block] to internally created [ReplyKeyboardMarkup]
 * and [ReplyKeyboardBuilder.build] [ReplyKeyboardMarkup] then
 *
 * @see ReplyKeyboardBuilder.row
 */
inline fun replyKeyboard(
    resizeKeyboard: Boolean? = null,
    oneTimeKeyboard: Boolean? = null,
    inputFieldPlaceholder: String? = null,
    selective: Boolean? = null,
    persistent: Boolean? = null,
    block: ReplyKeyboardBuilder.() -> Unit
) = ReplyKeyboardBuilder().apply(block).build(resizeKeyboard, oneTimeKeyboard, inputFieldPlaceholder, selective, persistent)

/**
 * Factory-function for [ReplyKeyboardBuilder], but in difference with [replyKeyboard] this method will create single-row
 * keyboard
 */
inline fun flatReplyKeyboard(
    resizeKeyboard: Boolean? = null,
    oneTimeKeyboard: Boolean? = null,
    inputFieldPlaceholder: String? = null,
    selective: Boolean? = null,
    persistent: Boolean? = null,
    block: ReplyKeyboardRowBuilder.() -> Unit
) = replyKeyboard(resizeKeyboard, oneTimeKeyboard, inputFieldPlaceholder, selective, persistent) {
    row<KeyboardButton>(block)
}

/**
 * Creates an [ReplyKeyboardRowBuilder] and [apply] [block] with this builder
 *
 * @see simpleButton
 * @see requestContactButton
 * @see requestLocationButton
 * @see requestPollButton
 */
@Deprecated("Redundant", ReplaceWith("this.row(block)", "dev.inmo.tgbotapi.utils.row"))
inline fun ReplyKeyboardBuilder.row(
    block: ReplyKeyboardRowBuilder.() -> Unit
) = add(ReplyKeyboardRowBuilder().apply(block).row)

/**
 * Creates and put [SimpleKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.simpleButton(
    text: String
) = add(SimpleKeyboardButton(text))

/**
 * Creates and put [RequestContactKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestContactButton(
    text: String
) = add(RequestContactKeyboardButton(text))

/**
 * Creates and put [RequestLocationKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestLocationButton(
    text: String
) = add(RequestLocationKeyboardButton(text))

/**
 * Creates and put [RequestPollKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestPollButton(
    text: String,
    pollType: KeyboardButtonPollType
) = add(RequestPollKeyboardButton(text, pollType))

/**
 * Creates and put [WebAppKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.webAppButton(
    text: String,
    webApp: WebAppInfo
) = add(WebAppKeyboardButton(text, webApp))

/**
 * Creates and put [WebAppKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.webAppButton(
    text: String,
    url: String
) = webAppButton(text, WebAppInfo(url))


/**
 * Creates and put [RequestUserKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestUserButton(
    text: String,
    requestUser: KeyboardButtonRequestUser
) = add(
    requestUserReplyButton(
        text,
        requestUser
    )
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUser.Bot]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestBotButton(
    text: String,
    requestId: RequestId
) = requestUserButton(
    text,
    KeyboardButtonRequestUser.Bot(requestId)
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUser.Common]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestUserButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null
) = requestUserButton(
    text,
    KeyboardButtonRequestUser.Common(requestId, premiumUser)
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUser.Any]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestUserOrBotButton(
    text: String,
    requestId: RequestId
) = requestUserButton(
    text,
    KeyboardButtonRequestUser.Any(requestId)
)


/**
 * Creates and put [RequestChatKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestChatButton(
    text: String,
    requestChat: KeyboardButtonRequestChat
) = add(
    requestChatReplyButton(
        text,
        requestChat
    )
)

/**
 * Creates and put [RequestChatKeyboardButton] with [KeyboardButtonRequestChat]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestChatButton(
    text: String,
    requestId: RequestId,
    isChannel: Boolean? = null,
    isForum: Boolean? = null,
    withUsername: Boolean? = null,
    ownedBy: Boolean? = null,
    userRightsInChat: ChatAdministratorRights? = null,
    botRightsInChat: ChatAdministratorRights? = null,
    botIsMember: Boolean? = null
) = requestChatButton(
    text,
    KeyboardButtonRequestChat(
        requestId, isChannel, isForum, withUsername, ownedBy, userRightsInChat, botRightsInChat, botIsMember
    )
)
