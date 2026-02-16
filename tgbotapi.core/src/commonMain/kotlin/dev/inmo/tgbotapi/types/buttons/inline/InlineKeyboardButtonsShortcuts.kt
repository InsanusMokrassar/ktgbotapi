@file:Suppress("unused")

package dev.inmo.tgbotapi.types.buttons.inline

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardButtonStyle
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.*
import dev.inmo.tgbotapi.types.webapps.WebAppInfo

/**
 * Creates [PayInlineKeyboardButton]
 */
fun payInlineButton(
    text: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = PayInlineKeyboardButton(text, iconCustomEmojiId, style)

/**
 * Creates [CallbackDataInlineKeyboardButton]
 */
fun dataInlineButton(
    text: String,
    data: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = CallbackDataInlineKeyboardButton(text, data, iconCustomEmojiId, style)

/**
 * Creates [CallbackGameInlineKeyboardButton]
 */
fun gameInlineButton(
    text: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = CallbackGameInlineKeyboardButton(text, iconCustomEmojiId, style)

/**
 * Creates [LoginURLInlineKeyboardButton]
 */
fun loginInlineButton(
    text: String,
    loginUrl: LoginURL,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = LoginURLInlineKeyboardButton(text, loginUrl, iconCustomEmojiId, style)

/**
 * Creates [CopyTextButton]
 */
fun copyTextButton(
    text: String,
    data: CopyTextButtonData,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = CopyTextButton(text, data, iconCustomEmojiId, style)

/**
 * Creates [CopyTextButton]
 */
fun copyTextButton(
    text: String,
    data: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = copyTextButton(text, CopyTextButtonData(data), iconCustomEmojiId, style)

/**
 * Creates [SwitchInlineQueryCurrentChatInlineKeyboardButton]
 */
fun inlineQueryInCurrentChatInlineButton(
    text: String,
    data: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = SwitchInlineQueryCurrentChatInlineKeyboardButton(text, data, iconCustomEmojiId, style)

/**
 * Creates [SwitchInlineQueryChosenChatInlineKeyboardButton]
 */
fun inlineQueryInCurrentChatInlineButton(
    text: String,
    parameters: SwitchInlineQueryChosenChat,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = SwitchInlineQueryChosenChatInlineKeyboardButton(text, parameters, iconCustomEmojiId, style)

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
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = inlineQueryInCurrentChatInlineButton(
    text,
    SwitchInlineQueryChosenChat(
        query = query,
        allowUsers = allowUsers,
        allowBots = allowBots,
        allowGroups = allowGroups,
        allowChannels = allowChannels
    ),
    iconCustomEmojiId,
    style
)

/**
 * Creates [SwitchInlineQueryChosenChatInlineKeyboardButton]
 */
fun inlineQueryInAnyCurrentChatInlineButton(
    text: String,
    query: String? = null,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = inlineQueryInCurrentChatInlineButton(text, query, allowUsers = true, allowBots = true, allowGroups = true, allowChannels = true, iconCustomEmojiId = iconCustomEmojiId, style = style)

/**
 * Creates [SwitchInlineQueryInlineKeyboardButton]
 */
fun inlineQueryInlineButton(
    text: String,
    data: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = SwitchInlineQueryInlineKeyboardButton(text, data, iconCustomEmojiId, style)

/**
 * Creates [URLInlineKeyboardButton]
 */
fun urlInlineButton(
    text: String,
    url: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = URLInlineKeyboardButton(text, url, iconCustomEmojiId, style)

/**
 * Creates [WebAppInlineKeyboardButton]. Please, remember that this button is available in private chats only
 */
fun webAppInlineButton(
    text: String,
    webApp: WebAppInfo,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = WebAppInlineKeyboardButton(text, webApp, iconCustomEmojiId, style)

/**
 * Creates [WebAppInlineKeyboardButton]. Please, remember that this button is available in private chats only
 */
fun webAppInlineButton(
    text: String,
    url: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = webAppInlineButton(text, WebAppInfo(url), iconCustomEmojiId, style)
