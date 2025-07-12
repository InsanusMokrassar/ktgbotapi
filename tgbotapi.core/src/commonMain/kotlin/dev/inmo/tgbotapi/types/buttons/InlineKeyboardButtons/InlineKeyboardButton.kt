@file:OptIn(ExperimentalSerializationApi::class)
@file:Suppress("KDocUnresolvedReference", "unused")

package dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.games.CallbackGame
import dev.inmo.tgbotapi.types.webapps.WebAppInfo
import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * Some button of [dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup]. See inheritors and visit
 * https://core.telegram.org/bots/api#inlinekeyboardbutton for more info
 */
@Serializable(InlineKeyboardButtonSerializer::class)
@ClassCastsIncluded
sealed interface InlineKeyboardButton {
    val text: String
}

@Serializable
data class UnknownInlineKeyboardButton (
    val rawData: JsonElement
) : InlineKeyboardButton {
    override val text: String
        get() = runCatching {
            rawData.jsonObject[textField] ?.jsonPrimitive ?.content
        }.getOrNull() ?: ""
}

/**
 * This type of button must always be the first button in the first row. Visit
 * https://core.telegram.org/bots/api#payments for mor info
 */
@Serializable
data class PayInlineKeyboardButton(
    override val text: String
) : InlineKeyboardButton {
    @ExperimentalSerializationApi
    @EncodeDefault
    @SerialName(payField)
    private val toPay = true
}

/**
 * Simple button with [callbackData] which you are able to catch this type of updates and data using
 * [dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onDataCallbackQuery] in
 * case you are using Behaviour Builder OR [dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter.callbackQueriesFlow]
 * with [kotlinx.coroutines.flow.filterIsInstance] and filtering by type [dev.inmo.tgbotapi.types.queries.callback.DataCallbackQuery]
 */
@Serializable
data class CallbackDataInlineKeyboardButton(
    @SerialName(textField)
    override val text: String,
    /**
     * You will receive this data in [dev.inmo.tgbotapi.types.queries.callback.DataCallbackQuery.data] field
     */
    @SerialName(callbackDataField)
    val callbackData: String
) : InlineKeyboardButton

/**
 * Button with [callbackGame]
 */
@Serializable
data class CallbackGameInlineKeyboardButton(
    @SerialName(textField)
    override val text: String
) : InlineKeyboardButton {
    @SerialName(callbackGameField)
    @EncodeDefault
    private val callbackGame = CallbackGame
}

/**
 * You may use this button to automatically authorize your user on [loginUrl]
 */
@Serializable
data class LoginURLInlineKeyboardButton(
    override val text: String,
    @SerialName(loginUrlField)
    val loginUrl: LoginURL
) : InlineKeyboardButton

/**
 * Complex button with [switchInlineQueryCurrentChat] which will be sent to you in an [dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery]
 * which you may catch in [dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onBaseInlineQuery] and get
 * from [dev.inmo.tgbotapi.types.InlineQueries.query.BaseInlineQuery.query] (or changed by user query in case he will be
 * the fastest hand in the wild west). Can be forwarded in any chat with message in case if it is the only one button in
 * message, but will be converted to a [SwitchInlineQueryInlineKeyboardButton].
 * Remember that clicking on this button will automatically insert username of this bot in current chat, paste
 * [switchInlineQueryCurrentChat] as a query and create and inline request to your bot
 * Visit https://core.telegram.org/bots/api#inlinekeyboardbutton for more info
 */
@Serializable
data class SwitchInlineQueryCurrentChatInlineKeyboardButton(
    override val text: String,
    @SerialName(switchInlineQueryCurrentChatField)
    val switchInlineQueryCurrentChat: String
) : InlineKeyboardButton

/**
 * Complex button with [switchInlineQueryCurrentChat] which will be sent to you in an [dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery]
 * which you may catch in [dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onBaseInlineQuery] and get
 * from [dev.inmo.tgbotapi.types.InlineQueries.query.BaseInlineQuery.query] (or changed by user query in case he will be
 * the fastest hand in the wild west). Can be forwarded in any chat with message in case if it is the only one button in
 * message, but will be converted to a [SwitchInlineQueryInlineKeyboardButton].
 * Remember that clicking on this button will automatically insert username of this bot in current chat, paste
 * [switchInlineQueryCurrentChat] as a query and create and inline request to your bot
 * Visit https://core.telegram.org/bots/api#inlinekeyboardbutton for more info
 */
@Serializable
data class SwitchInlineQueryChosenChatInlineKeyboardButton(
    override val text: String,
    @SerialName(switchInlineQueryChosenChatField)
    val parameters: SwitchInlineQueryChosenChat
) : InlineKeyboardButton

/**
 * Complex button with [switchInlineQuery] which will be sent to you in an [dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery]
 * which you may catch in [dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onBaseInlineQuery] and get
 * from [dev.inmo.tgbotapi.types.InlineQueries.query.BaseInlineQuery.query] (or changed by user query in case he will be
 * the fastest hand in the wild west). Can be forwarded in any chat with message in case if it is the only one button in message.
 * Remember that clicking on this button will automatically insert username of this bot in the chosen by user chat, paste
 * [switchInlineQuery] as a query and create and inline request to your bot.
 * Visit https://core.telegram.org/bots/api#inlinekeyboardbutton for more info
 */
@Serializable
data class SwitchInlineQueryInlineKeyboardButton(
    override val text: String,
    @SerialName(switchInlineQueryField)
    val switchInlineQuery: String
) : InlineKeyboardButton

/**
 * Simple [url] button. Can be forwarded in any chat with message in case if it is the only one button in message
 */
@Serializable
data class URLInlineKeyboardButton(
    override val text: String,
    @SerialName(urlField)
    val url: String
) : InlineKeyboardButton

/**
 * `copy_text` button
 */
@Serializable
data class CopyTextButton(
    override val text: String,
    @SerialName(copyTextField)
    val data: CopyTextButtonData
) : InlineKeyboardButton

/**
 * Button with [WebAppInfo]. Web App will be launched when the button is pressed. The Web App will be able to send a
 * `web_app_data` service message. **Available in private chats only**.
 */
@Serializable
data class WebAppInlineKeyboardButton(
    override val text: String,
    @SerialName(webAppField)
    val webApp: WebAppInfo
) : InlineKeyboardButton
