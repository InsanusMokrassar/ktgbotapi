package dev.inmo.tgbotapi.extensions.utils.types.buttons

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardButtonStyle
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
fun InlineKeyboardRowBuilder.payButton(
    text: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(PayInlineKeyboardButton(text, iconCustomEmojiId, style))

/**
 * Creates and put [CallbackDataInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
fun InlineKeyboardRowBuilder.dataButton(
    text: String,
    data: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(CallbackDataInlineKeyboardButton(text, data, iconCustomEmojiId, style))

/**
 * Creates and put [CallbackGameInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
fun InlineKeyboardRowBuilder.gameButton(
    text: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(CallbackGameInlineKeyboardButton(text, iconCustomEmojiId, style))

/**
 * Creates and put [LoginURLInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
fun InlineKeyboardRowBuilder.loginButton(
    text: String,
    loginUrl: LoginURL,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(LoginURLInlineKeyboardButton(text, loginUrl, iconCustomEmojiId, style))

/**
 * Creates [CopyTextButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
fun InlineKeyboardRowBuilder.copyTextButton(
    text: String,
    data: CopyTextButtonData,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(CopyTextButton(text, data, iconCustomEmojiId, style))

/**
 * Creates [CopyTextButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
fun InlineKeyboardRowBuilder.copyTextButton(
    text: String,
    data: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = copyTextButton(text, CopyTextButtonData(data), iconCustomEmojiId, style)

/**
 * Creates and put [SwitchInlineQueryCurrentChatInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
fun InlineKeyboardRowBuilder.inlineQueryInCurrentChatButton(
    text: String,
    data: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(SwitchInlineQueryCurrentChatInlineKeyboardButton(text, data, iconCustomEmojiId, style))

/**
 * Creates and put [SwitchInlineQueryChosenChatInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
fun InlineKeyboardRowBuilder.inlineQueryInChosenChatButton(
    text: String,
    parameters: SwitchInlineQueryChosenChat,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(SwitchInlineQueryChosenChatInlineKeyboardButton(text, parameters, iconCustomEmojiId, style))

/**
 * Creates and put [SwitchInlineQueryChosenChatInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
fun InlineKeyboardRowBuilder.inlineQueryInChosenChatButton(
    text: String,
    query: String? = null,
    allowUsers: Boolean = false,
    allowBots: Boolean = false,
    allowGroups: Boolean = false,
    allowChannels: Boolean = false,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = inlineQueryInChosenChatButton(
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
fun InlineKeyboardRowBuilder.inlineQueryInAnyChosenChatButton(
    text: String,
    query: String? = null,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = inlineQueryInChosenChatButton(text, query, allowUsers = true, allowBots = true, allowGroups = true, allowChannels = true, iconCustomEmojiId = iconCustomEmojiId, style = style)

/**
 * Creates and put [SwitchInlineQueryInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
fun InlineKeyboardRowBuilder.inlineQueryButton(
    text: String,
    data: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(SwitchInlineQueryInlineKeyboardButton(text, data, iconCustomEmojiId, style))

/**
 * Creates and put [URLInlineKeyboardButton]
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
fun InlineKeyboardRowBuilder.urlButton(
    text: String,
    url: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(URLInlineKeyboardButton(text, url, iconCustomEmojiId, style))

/**
 * Creates and put [WebAppInlineKeyboardButton]. Please, remember that this button is available in private chats only
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
fun InlineKeyboardRowBuilder.webAppButton(
    text: String,
    webApp: WebAppInfo,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(WebAppInlineKeyboardButton(text, webApp, iconCustomEmojiId, style))

/**
 * Creates and put [WebAppInlineKeyboardButton]. Please, remember that this button is available in private chats only
 *
 * @see inlineKeyboard
 * @see InlineKeyboardBuilder.row
 */
fun InlineKeyboardRowBuilder.webAppButton(
    text: String,
    url: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = webAppButton(text, WebAppInfo(url), iconCustomEmojiId, style)
