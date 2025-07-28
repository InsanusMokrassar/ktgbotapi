@file:Suppress("unused", "RemoveExplicitTypeArguments")

package dev.inmo.tgbotapi.extensions.utils.types.buttons

import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.buttons.reply.requestChatReplyButton
import dev.inmo.tgbotapi.types.buttons.reply.requestUserReplyButton
import dev.inmo.tgbotapi.types.buttons.reply.requestUsersReplyButton
import dev.inmo.tgbotapi.types.chat.member.ChatCommonAdministratorRights
import dev.inmo.tgbotapi.types.keyboardButtonRequestUserLimit
import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.types.webapps.WebAppInfo
import dev.inmo.tgbotapi.utils.*

/**
 * Core DSL part of Keyboard DSL. Can accept only [KeyboardButton] and returns ready to use
 * [ReplyKeyboardMarkup] via [build] method
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 * @see ReplyKeyboardRowBuilder
 */
typealias ReplyKeyboardBuilder = MatrixBuilder<KeyboardButton>

/**
 * Creates [InlineKeyboardMarkup] using internal [matrix]
 */
fun ReplyKeyboardBuilder.build(
    resizeKeyboard: Boolean? = null,
    oneTimeKeyboard: Boolean? = null,
    inputFieldPlaceholder: String? = null,
    selective: Boolean? = null,
    persistent: Boolean? = null,
) = ReplyKeyboardMarkup(matrix, resizeKeyboard, oneTimeKeyboard, inputFieldPlaceholder, selective, persistent)

/**
 * Row builder of [KeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
typealias ReplyKeyboardRowBuilder = RowBuilder<KeyboardButton>

/**
 * Factory-function for [ReplyKeyboardBuilder]. It will [apply] [block] to internally created [ReplyKeyboardMarkup]
 * and [ReplyKeyboardBuilder.build] [ReplyKeyboardMarkup] then
 *
 * ```kotlin
 *  replyKeyboard {
 *      row {
 *          simpleButton("simple button")
 *          simpleButton("simple button2")
 *      }
 *      row {
 *          simpleButton("simple button2")
 *      }
 *  }
 * ```
 *
 * @see ReplyKeyboardBuilder.row
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.simpleButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestContactButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestLocationButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestPollButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.webAppButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestUserButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestUserOrBotButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestChatButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestChannelButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestGroupButton
 */
inline fun replyKeyboard(
    resizeKeyboard: Boolean? = null,
    oneTimeKeyboard: Boolean? = null,
    inputFieldPlaceholder: String? = null,
    selective: Boolean? = null,
    persistent: Boolean? = null,
    block: ReplyKeyboardBuilder.() -> Unit
) = ReplyKeyboardBuilder().apply(block).build(resizeKeyboard, oneTimeKeyboard, inputFieldPlaceholder, selective, persistent)

/**
 * Factory-function for [ReplyKeyboardBuilder], but in difference with [replyKeyboard] this method will create single-row
 * keyboard
 *
 * ```kotlin
 * flatReplyKeyboard {
 *      simpleButton("simple button")
 *      simpleButton("simple button2")
 * }
 * ```
 *
 * @see ReplyKeyboardBuilder.row
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.simpleButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestContactButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestLocationButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestPollButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.webAppButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestUserButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestUserOrBotButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestChatButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestChannelButton
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.requestGroupButton
 */
inline fun flatReplyKeyboard(
    resizeKeyboard: Boolean? = null,
    oneTimeKeyboard: Boolean? = null,
    inputFieldPlaceholder: String? = null,
    selective: Boolean? = null,
    persistent: Boolean? = null,
    block: ReplyKeyboardRowBuilder.() -> Unit
) = replyKeyboard(resizeKeyboard, oneTimeKeyboard, inputFieldPlaceholder, selective, persistent) {
    row<KeyboardButton>(block)
}

/**
 * Creates and put [SimpleKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.simpleButton(
    text: String
) = add(SimpleKeyboardButton(text))

/**
 * Creates and put [RequestContactKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestContactButton(
    text: String
) = add(RequestContactKeyboardButton(text))

/**
 * Creates and put [RequestLocationKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestLocationButton(
    text: String
) = add(RequestLocationKeyboardButton(text))

/**
 * Creates and put [RequestPollKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestPollButton(
    text: String,
    pollType: KeyboardButtonPollType
) = add(RequestPollKeyboardButton(text, pollType))

/**
 * Creates and put [WebAppKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.webAppButton(
    text: String,
    webApp: WebAppInfo
) = add(WebAppKeyboardButton(text, webApp))

/**
 * Creates and put [WebAppKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.webAppButton(
    text: String,
    url: String
) = webAppButton(text, WebAppInfo(url))


/**
 * Creates and put [RequestUserKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestUsersButton(
    text: String,
    requestUser: KeyboardButtonRequestUsers
) = add(
    requestUsersReplyButton(
        text,
        requestUser
    )
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Bot]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestBotsButton(
    text: String,
    requestId: RequestId,
    maxCount: Int = keyboardButtonRequestUserLimit.first,
    requestName: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestUsersButton(
    text,
    KeyboardButtonRequestUsers.Bot(
        requestId = requestId,
        maxCount = maxCount,
        requestName = requestName,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto
    )
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Bot]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestBotButton(
    text: String,
    requestId: RequestId,
    requestName: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestBotsButton(
    text,
    requestId,
    maxCount = keyboardButtonRequestUserLimit.first,
    requestName = requestName,
    requestUsername = requestUsername,
    requestPhoto = requestPhoto
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Common]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestUsersButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null,
    maxCount: Int = keyboardButtonRequestUserLimit.first,
    requestName: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestUsersButton(
    text,
    KeyboardButtonRequestUsers.Common(
        requestId = requestId,
        isPremium = premiumUser,
        maxCount = maxCount,
        requestName = requestName,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto
    )
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Common]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestUserButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null,
    requestName: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestUsersButton(
    text = text,
    requestId = requestId,
    premiumUser = premiumUser,
    maxCount = keyboardButtonRequestUserLimit.first,
    requestName = requestName,
    requestUsername = requestUsername,
    requestPhoto = requestPhoto
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Any]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestUsersOrBotsButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null,
    maxCount: Int = keyboardButtonRequestUserLimit.first,
    requestName: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestUsersButton(
    text,
    KeyboardButtonRequestUsers.Any(
        requestId = requestId,
        isPremium = premiumUser,
        maxCount = maxCount,
        requestName = requestName,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto
    )
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Any]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestUserOrBotButton(
    text: String,
    requestId: RequestId,
    requestName: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestUsersOrBotsButton(
    text = text,
    requestId = requestId,
    maxCount = keyboardButtonRequestUserLimit.first,
    requestName = requestName,
    requestUsername = requestUsername,
    requestPhoto = requestPhoto,
)


/**
 * Creates and put [RequestChatKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestChatButton(
    text: String,
    requestChat: KeyboardButtonRequestChat
) = add(
    requestChatReplyButton(
        text,
        requestChat
    )
)

/**
 * Creates and put [RequestChatKeyboardButton] with [KeyboardButtonRequestChat]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestChatButton(
    text: String,
    requestId: RequestId,
    isChannel: Boolean? = null,
    isForum: Boolean? = null,
    isPublic: Boolean? = null,
    isOwnedBy: Boolean? = null,
    userRightsInChat: ChatCommonAdministratorRights? = null,
    botRightsInChat: ChatCommonAdministratorRights? = null,
    botIsMember: Boolean? = null,
    requestTitle: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestChatButton(
    text,
    KeyboardButtonRequestChat(
        requestId = requestId,
        isChannel = isChannel,
        isForum = isForum,
        isPublic = isPublic,
        isOwnedBy = isOwnedBy,
        userRightsInChat = userRightsInChat,
        botRightsInChat = botRightsInChat,
        botIsMember = botIsMember,
        requestTitle = requestTitle,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto,
    )
)

/**
 * Creates and put [RequestChatKeyboardButton] with [KeyboardButtonRequestChat.Channel]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestChannelButton(
    text: String,
    requestId: RequestId,
    isPublic: Boolean? = null,
    isOwnedBy: Boolean? = null,
    userRightsInChat: ChatCommonAdministratorRights? = null,
    botRightsInChat: ChatCommonAdministratorRights? = null,
    botIsMember: Boolean? = null,
    requestTitle: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestChatButton(
    text,
    KeyboardButtonRequestChat.Channel(
        requestId = requestId,
        isPublic = isPublic,
        isOwnedBy = isOwnedBy,
        userRightsInChat = userRightsInChat,
        botRightsInChat = botRightsInChat,
        botIsMember = botIsMember,
        requestTitle = requestTitle,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto,
    )
)

/**
 * Creates and put [RequestChatKeyboardButton] with [KeyboardButtonRequestChat.Group]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestGroupButton(
    text: String,
    requestId: RequestId,
    isForum: Boolean? = null,
    isPublic: Boolean? = null,
    isOwnedBy: Boolean? = null,
    userRightsInChat: ChatCommonAdministratorRights? = null,
    botRightsInChat: ChatCommonAdministratorRights? = null,
    botIsMember: Boolean? = null,
    requestTitle: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestChatButton(
    text,
    KeyboardButtonRequestChat.Group(
        requestId = requestId,
        isForum = isForum,
        isPublic = isPublic,
        isOwnedBy = isOwnedBy,
        userRightsInChat = userRightsInChat,
        botRightsInChat = botRightsInChat,
        botIsMember = botIsMember,
        requestTitle = requestTitle,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto,
    )
)
