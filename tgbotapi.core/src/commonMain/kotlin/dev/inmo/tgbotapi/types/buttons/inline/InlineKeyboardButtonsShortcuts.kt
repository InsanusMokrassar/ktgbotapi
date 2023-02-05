package dev.inmo.tgbotapi.types.buttons.inline

import dev.inmo.tgbotapi.types.LoginURL
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.*
import dev.inmo.tgbotapi.types.buttons.KeyboardButtonRequestChat
import dev.inmo.tgbotapi.types.buttons.KeyboardButtonRequestUser
import dev.inmo.tgbotapi.types.chat.member.ChatAdministratorRights
import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.types.webapps.WebAppInfo

/**
 * Creates and put [PayInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun payInlineButton(
    text: String
) = PayInlineKeyboardButton(text)

/**
 * Creates and put [CallbackDataInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun dataInlineButton(
    text: String,
    data: String
) = CallbackDataInlineKeyboardButton(text, data)

/**
 * Creates and put [CallbackGameInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun gameInlineButton(
    text: String
) = CallbackGameInlineKeyboardButton(text)

/**
 * Creates and put [LoginURLInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun loginInlineButton(
    text: String,
    loginUrl: LoginURL
) = LoginURLInlineKeyboardButton(text, loginUrl)

/**
 * Creates and put [SwitchInlineQueryCurrentChatInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun inlineQueryInCurrentChatInlineButton(
    text: String,
    data: String
) = SwitchInlineQueryCurrentChatInlineKeyboardButton(text, data)

/**
 * Creates and put [SwitchInlineQueryInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun inlineQueryInlineButton(
    text: String,
    data: String
) = SwitchInlineQueryInlineKeyboardButton(text, data)

/**
 * Creates and put [URLInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun urlInlineButton(
    text: String,
    url: String
) = URLInlineKeyboardButton(text, url)

/**
 * Creates and put [WebAppInlineKeyboardButton]. Please, remember that this button is available in private chats only
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun webAppInlineButton(
    text: String,
    webApp: WebAppInfo
) = WebAppInlineKeyboardButton(text, webApp)

/**
 * Creates and put [WebAppInlineKeyboardButton]. Please, remember that this button is available in private chats only
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun webAppInlineButton(
    text: String,
    url: String
) = webAppInlineButton(text, WebAppInfo(url))


/**
 * Creates and put [RequestUserKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestUserInlineButton(
    text: String,
    requestUser: KeyboardButtonRequestUser
) = RequestUserInlineKeyboardButton(
    text,
    requestUser
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUser.Bot]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestBotInlineButton(
    text: String,
    requestId: RequestId
) = requestUserInlineButton(
    text,
    KeyboardButtonRequestUser.Bot(requestId)
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUser.Common]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestUserInlineButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null
) = requestUserInlineButton(
    text,
    KeyboardButtonRequestUser.Common(requestId, premiumUser)
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUser.Any]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestUserOrBotInlineButton(
    text: String,
    requestId: RequestId
) = requestUserInlineButton(
    text,
    KeyboardButtonRequestUser.Any(requestId)
)


/**
 * Creates and put [RequestChatKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestChatInlineButton(
    text: String,
    requestChat: KeyboardButtonRequestChat
) = RequestChatInlineKeyboardButton(
    text,
    requestChat
)

/**
 * Creates and put [RequestChatKeyboardButton] with [KeyboardButtonRequestChat]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestChatInlineButton(
    text: String,
    requestId: RequestId,
    isChannel: Boolean? = null,
    isForum: Boolean? = null,
    withUsername: Boolean? = null,
    ownedBy: Boolean? = null,
    userRightsInChat: ChatAdministratorRights? = null,
    botRightsInChat: ChatAdministratorRights? = null,
    botIsMember: Boolean = false
) = requestChatInlineButton(
    text,
    KeyboardButtonRequestChat(
        requestId, isChannel, isForum, withUsername, ownedBy, userRightsInChat, botRightsInChat, botIsMember
    )
)

