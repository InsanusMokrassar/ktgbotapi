package dev.inmo.tgbotapi.types.buttons.reply

import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.*
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
