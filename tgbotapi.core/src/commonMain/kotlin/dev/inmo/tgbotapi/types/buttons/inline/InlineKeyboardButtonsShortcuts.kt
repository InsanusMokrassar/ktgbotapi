@file:Suppress("unused")

package dev.inmo.tgbotapi.types.buttons.inline

import dev.inmo.tgbotapi.types.LoginURL
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.*
import dev.inmo.tgbotapi.types.webapps.WebAppInfo

/**
 * Creates [PayInlineKeyboardButton]
 */
inline fun payInlineButton(
    text: String
) = PayInlineKeyboardButton(text)

/**
 * Creates [CallbackDataInlineKeyboardButton]
 */
inline fun dataInlineButton(
    text: String,
    data: String
) = CallbackDataInlineKeyboardButton(text, data)

/**
 * Creates [CallbackGameInlineKeyboardButton]
 */
inline fun gameInlineButton(
    text: String
) = CallbackGameInlineKeyboardButton(text)

/**
 * Creates [LoginURLInlineKeyboardButton]
 */
inline fun loginInlineButton(
    text: String,
    loginUrl: LoginURL
) = LoginURLInlineKeyboardButton(text, loginUrl)

/**
 * Creates [CopyTextButton]
 */
inline fun copyTextButton(
    text: String,
    data: CopyTextButtonData
) = CopyTextButton(text, data)

/**
 * Creates [CopyTextButton]
 */
inline fun copyTextButton(
    text: String,
    data: String
) = copyTextButton(text, CopyTextButtonData(data))

/**
 * Creates [SwitchInlineQueryCurrentChatInlineKeyboardButton]
 */
inline fun inlineQueryInCurrentChatInlineButton(
    text: String,
    data: String
) = SwitchInlineQueryCurrentChatInlineKeyboardButton(text, data)

/**
 * Creates [SwitchInlineQueryChosenChatInlineKeyboardButton]
 */
inline fun inlineQueryInCurrentChatInlineButton(
    text: String,
    parameters: SwitchInlineQueryChosenChat
) = SwitchInlineQueryChosenChatInlineKeyboardButton(text, parameters)

/**
 * Creates [SwitchInlineQueryChosenChatInlineKeyboardButton]
 */
inline fun inlineQueryInCurrentChatInlineButton(
    text: String,
    query: String? = null,
    allowUsers: Boolean = false,
    allowBots: Boolean = false,
    allowGroups: Boolean = false,
    allowChannels: Boolean = false,
) = inlineQueryInCurrentChatInlineButton(
    text,
    SwitchInlineQueryChosenChat(
        query = query,
        allowUsers = allowUsers,
        allowBots = allowBots,
        allowGroups = allowGroups,
        allowChannels = allowChannels
    )
)

/**
 * Creates [SwitchInlineQueryChosenChatInlineKeyboardButton]
 */
inline fun inlineQueryInAnyCurrentChatInlineButton(
    text: String,
    query: String? = null,
) = inlineQueryInCurrentChatInlineButton(text, query, allowUsers = true, allowBots = true, allowGroups = true, allowChannels = true)

/**
 * Creates [SwitchInlineQueryInlineKeyboardButton]
 */
inline fun inlineQueryInlineButton(
    text: String,
    data: String
) = SwitchInlineQueryInlineKeyboardButton(text, data)

/**
 * Creates [URLInlineKeyboardButton]
 */
inline fun urlInlineButton(
    text: String,
    url: String
) = URLInlineKeyboardButton(text, url)

/**
 * Creates [WebAppInlineKeyboardButton]. Please, remember that this button is available in private chats only
 */
inline fun webAppInlineButton(
    text: String,
    webApp: WebAppInfo
) = WebAppInlineKeyboardButton(text, webApp)

/**
 * Creates [WebAppInlineKeyboardButton]. Please, remember that this button is available in private chats only
 */
inline fun webAppInlineButton(
    text: String,
    url: String
) = webAppInlineButton(text, WebAppInfo(url))
