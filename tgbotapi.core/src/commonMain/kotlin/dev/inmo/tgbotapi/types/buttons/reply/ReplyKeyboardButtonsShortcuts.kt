package dev.inmo.tgbotapi.types.buttons.reply

import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.*
import dev.inmo.tgbotapi.types.chat.member.ChatAdministratorRights
import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.types.webapps.WebAppInfo


/**
 * Creates and put [SimpleKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun simpleReplyButton(
    text: String
) = SimpleKeyboardButton(text)

/**
 * Creates and put [RequestContactKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestContactReplyButton(
    text: String
) = RequestContactKeyboardButton(text)

/**
 * Creates and put [RequestLocationKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestLocationReplyButton(
    text: String
) = RequestLocationKeyboardButton(text)

/**
 * Creates and put [RequestPollKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestPollReplyButton(
    text: String,
    pollType: KeyboardButtonPollType
) = RequestPollKeyboardButton(text, pollType)

/**
 * Creates and put [WebAppKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun webAppReplyButton(
    text: String,
    webApp: WebAppInfo
) = WebAppKeyboardButton(text, webApp)

/**
 * Creates and put [WebAppKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun webAppReplyButton(
    text: String,
    url: String
) = webAppReplyButton(text, WebAppInfo(url))


/**
 * Creates and put [RequestUserKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestUserReplyButton(
    text: String,
    requestUser: KeyboardButtonRequestUser
) = RequestUserKeyboardButton(
    text,
    requestUser
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUser.Bot]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestBotReplyButton(
    text: String,
    requestId: RequestId
) = requestUserReplyButton(
    text,
    KeyboardButtonRequestUser.Bot(requestId)
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUser.Common]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestUserReplyButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null
) = requestUserReplyButton(
    text,
    KeyboardButtonRequestUser.Common(requestId, premiumUser)
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUser.Any]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestUserOrBotReplyButton(
    text: String,
    requestId: RequestId
) = requestUserReplyButton(
    text,
    KeyboardButtonRequestUser.Any(requestId)
)


/**
 * Creates and put [RequestChatKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestChatReplyButton(
    text: String,
    requestChat: KeyboardButtonRequestChat
) = RequestChatKeyboardButton(
    text,
    requestChat
)

/**
 * Creates and put [RequestChatKeyboardButton] with [KeyboardButtonRequestChat]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun requestChatReplyButton(
    text: String,
    requestId: RequestId,
    isChannel: Boolean? = null,
    isForum: Boolean? = null,
    withUsername: Boolean? = null,
    ownedBy: Boolean? = null,
    userRightsInChat: ChatAdministratorRights? = null,
    botRightsInChat: ChatAdministratorRights? = null,
    botIsMember: Boolean = false
) = requestChatReplyButton(
    text,
    KeyboardButtonRequestChat(
        requestId, isChannel, isForum, withUsername, ownedBy, userRightsInChat, botRightsInChat, botIsMember
    )
)
