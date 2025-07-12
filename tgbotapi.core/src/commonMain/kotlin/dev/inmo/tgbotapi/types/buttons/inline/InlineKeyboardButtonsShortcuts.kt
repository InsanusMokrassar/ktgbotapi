@file:Suppress("unused")

package dev.inmo.tgbotapi.types.buttons.inline

import dev.inmo.tgbotapi.types.LoginURL
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.*
import dev.inmo.tgbotapi.types.webapps.WebAppInfo

/**
 * Creates [PayInlineKeyboardButton]
 */
fun payInlineButton(
    text: String
) = PayInlineKeyboardButton(text)

/**
 * Creates [CallbackDataInlineKeyboardButton]
 */
fun dataInlineButton(
    text: String,
    data: String
) = CallbackDataInlineKeyboardButton(text, data)

/**
 * Creates [CallbackGameInlineKeyboardButton]
 */
fun gameInlineButton(
    text: String
) = CallbackGameInlineKeyboardButton(text)

/**
 * Creates [LoginURLInlineKeyboardButton]
 */
fun loginInlineButton(
    text: String,
    loginUrl: LoginURL
) = LoginURLInlineKeyboardButton(text, loginUrl)

/**
 * Creates [CopyTextButton]
 */
fun copyTextButton(
    text: String,
    data: CopyTextButtonData
) = CopyTextButton(text, data)

/**
 * Creates [CopyTextButton]
 */
fun copyTextButton(
    text: String,
    data: String
) = copyTextButton(text, CopyTextButtonData(data))

/**
 * Creates [SwitchInlineQueryCurrentChatInlineKeyboardButton]
 */
fun inlineQueryInCurrentChatInlineButton(
    text: String,
    data: String
) = SwitchInlineQueryCurrentChatInlineKeyboardButton(text, data)

/**
 * Creates [SwitchInlineQueryChosenChatInlineKeyboardButton]
 */
fun inlineQueryInCurrentChatInlineButton(
    text: String,
    parameters: SwitchInlineQueryChosenChat
) = SwitchInlineQueryChosenChatInlineKeyboardButton(text, parameters)

/**
 * Creates [SwitchInlineQueryChosenChatInlineKeyboardButton]
 */
fun inlineQueryInCurrentChatInlineButton(
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
fun inlineQueryInAnyCurrentChatInlineButton(
    text: String,
    query: String? = null,
) = inlineQueryInCurrentChatInlineButton(text, query, allowUsers = true, allowBots = true, allowGroups = true, allowChannels = true)

/**
 * Creates [SwitchInlineQueryInlineKeyboardButton]
 */
fun inlineQueryInlineButton(
    text: String,
    data: String
) = SwitchInlineQueryInlineKeyboardButton(text, data)

/**
 * Creates [URLInlineKeyboardButton]
 */
fun urlInlineButton(
    text: String,
    url: String
) = URLInlineKeyboardButton(text, url)

/**
 * Creates [WebAppInlineKeyboardButton]. Please, remember that this button is available in private chats only
 */
fun webAppInlineButton(
    text: String,
    webApp: WebAppInfo
) = WebAppInlineKeyboardButton(text, webApp)

/**
 * Creates [WebAppInlineKeyboardButton]. Please, remember that this button is available in private chats only
 */
fun webAppInlineButton(
    text: String,
    url: String
) = webAppInlineButton(text, WebAppInfo(url))
