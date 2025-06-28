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
 * Example:
 *
 * ```kotlin
 * inlineKeyboard {
 *      row {
 *          dataButton("my button", "my data")
 *          dataButton("my button2", "my data2")
 *      }
 *      row {
 *          urlButton("example.com", "https://example.com")
 *      }
 * }
 * ```
 *
 * @see InlineKeyboardBuilder.row
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.payButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.dataButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.gameButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.loginButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.copyTextButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineQueryInCurrentChatButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineQueryInChosenChatButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineQueryInAnyChosenChatButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineQueryButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.urlButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.webAppButton
 */
inline fun inlineKeyboard(
    block: InlineKeyboardBuilder.() -> Unit
) = InlineKeyboardBuilder().apply(block).build()

/**
 * Factory-function for [InlineKeyboardBuilder], but in difference with [inlineKeyboard] this function will create single-row
 * inline keyboard
 *
 * Example:
 *
 * ```kotlin
 * flatInlineKeyboard {
 *      dataButton("some button", "some data")
 *      urlButton("example.com", "https://example.com")
 * }
 * ```
 *
 * @see InlineKeyboardBuilder.row
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.payButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.dataButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.gameButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.loginButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.copyTextButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineQueryInCurrentChatButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineQueryInChosenChatButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineQueryInAnyChosenChatButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineQueryButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.urlButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.webAppButton
 */
inline fun flatInlineKeyboard(
    block: InlineKeyboardRowBuilder.() -> Unit
) = inlineKeyboard { row<InlineKeyboardButton>(block) }

/**
 * Factory-function for [InlineKeyboardBuilder]. It will [apply] [block] to internally created [InlineKeyboardMarkup]
 * and [InlineKeyboardBuilder.build] [InlineKeyboardMarkup] then
 *
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardMarkup.modified(
    block: InlineKeyboardBuilder.() -> Unit
) = InlineKeyboardBuilder().apply {
    keyboard.forEach { add(it) }
    block()
}.build()


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
 * Creates [CopyTextButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.copyTextButton(
    text: String,
    data: CopyTextButtonData
) = add(CopyTextButton(text, data))

/**
 * Creates [CopyTextButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.copyTextButton(
    text: String,
    data: String
) = copyTextButton(text, CopyTextButtonData(data))

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
 * Creates and put [SwitchInlineQueryChosenChatInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.inlineQueryInChosenChatButton(
    text: String,
    parameters: SwitchInlineQueryChosenChat
) = add(SwitchInlineQueryChosenChatInlineKeyboardButton(text, parameters))

/**
 * Creates and put [SwitchInlineQueryChosenChatInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
inline fun InlineKeyboardRowBuilder.inlineQueryInChosenChatButton(
    text: String,
    query: String? = null,
    allowUsers: Boolean = false,
    allowBots: Boolean = false,
    allowGroups: Boolean = false,
    allowChannels: Boolean = false,
) = inlineQueryInChosenChatButton(
    text,
    SwitchInlineQueryChosenChat(
        query = query,
        allowUsers = allowUsers,
        allowBots = allowBots,
        allowGroups = allowGroups,
        allowChannels = allowChannels
    )
)
inline fun InlineKeyboardRowBuilder.inlineQueryInAnyChosenChatButton(
    text: String,
    query: String? = null,
) = inlineQueryInChosenChatButton(text, query, allowUsers = true, allowBots = true, allowGroups = true, allowChannels = true)

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
