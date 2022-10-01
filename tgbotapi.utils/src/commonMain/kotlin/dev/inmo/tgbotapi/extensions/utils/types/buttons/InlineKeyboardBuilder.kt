package dev.inmo.tgbotapi.extensions.utils.types.buttons

import dev.inmo.tgbotapi.types.LoginURL
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.webapps.WebAppInfo
import dev.inmo.tgbotapi.utils.*

/**
 * Core DSL part of Inline Keyboard DSL. Can accept only [InlineKeyboardButton] and returns ready to use
 * [InlineKeyboardMarkup] via [build] method
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 * @see InlineKeyboardRowBuilder
 */
typealias InlineKeyboardBuilder = MatrixBuilder<InlineKeyboardButton>

/**
 * Creates [InlineKeyboardMarkup] using internal [matrix]
 */
fun InlineKeyboardBuilder.build() = InlineKeyboardMarkup(matrix)

/**
 * Row builder of [InlineKeyboardBuilder]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
typealias InlineKeyboardRowBuilder = RowBuilder<InlineKeyboardButton>

/**
 * Factory-function for [InlineKeyboardBuilder]. It will [apply] [block] to internally created [InlineKeyboardMarkup]
 * and [InlineKeyboardBuilder.build] [InlineKeyboardMarkup] then
 *
 * @see InlineKeyboardBuilder.row
 */
inline fun inlineKeyboard(
    block: InlineKeyboardBuilder.() -> Unit
) = InlineKeyboardBuilder().apply(block).build()

/**
 * Factory-function for [InlineKeyboardBuilder], but in difference with [inlineKeyboard] this function will create single-row
 * inline keyboard
 *
 * @see InlineKeyboardBuilder.row
 */
inline fun flatInlineKeyboard(
    block: InlineKeyboardRowBuilder.() -> Unit
) = inlineKeyboard { row<InlineKeyboardButton>(block) }

/**
 * Creates an [InlineKeyboardRowBuilder] and [apply] [block] with this builder
 *
 * @see payButton
 * @see dataButton
 * @see gameButton
 * @see loginButton
 * @see inlineQueryInCurrentChatButton
 * @see inlineQueryButton
 * @see urlButton
 */
@Deprecated("Redundant", ReplaceWith("this.row(block)", "dev.inmo.tgbotapi.utils.row"))
inline fun InlineKeyboardBuilder.row(
    block: InlineKeyboardRowBuilder.() -> Unit
) = add(InlineKeyboardRowBuilder().apply(block).row)

/**
 * Creates and put [PayInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.payButton(
    text: String
) = add(PayInlineKeyboardButton(text))

/**
 * Creates and put [CallbackDataInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.dataButton(
    text: String,
    data: String
) = add(CallbackDataInlineKeyboardButton(text, data))

/**
 * Creates and put [CallbackGameInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.gameButton(
    text: String
) = add(CallbackGameInlineKeyboardButton(text))

/**
 * Creates and put [LoginURLInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.loginButton(
    text: String,
    loginUrl: LoginURL
) = add(LoginURLInlineKeyboardButton(text, loginUrl))

/**
 * Creates and put [SwitchInlineQueryCurrentChatInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.inlineQueryInCurrentChatButton(
    text: String,
    data: String
) = add(SwitchInlineQueryCurrentChatInlineKeyboardButton(text, data))

/**
 * Creates and put [SwitchInlineQueryInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.inlineQueryButton(
    text: String,
    data: String
) = add(SwitchInlineQueryInlineKeyboardButton(text, data))

/**
 * Creates and put [URLInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.urlButton(
    text: String,
    url: String
) = add(URLInlineKeyboardButton(text, url))

/**
 * Creates and put [WebAppInlineKeyboardButton]. Please, remember that this button is available in private chats only
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.webAppButton(
    text: String,
    webApp: WebAppInfo
) = add(WebAppInlineKeyboardButton(text, webApp))

/**
 * Creates and put [WebAppInlineKeyboardButton]. Please, remember that this button is available in private chats only
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.webAppButton(
    text: String,
    url: String
) = webAppButton(text, WebAppInfo(url))
