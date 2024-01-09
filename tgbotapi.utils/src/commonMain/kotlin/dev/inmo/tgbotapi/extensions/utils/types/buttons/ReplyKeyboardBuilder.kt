package dev.inmo.tgbotapi.extensions.utils.types.buttons

import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.buttons.reply.requestChatReplyButton
import dev.inmo.tgbotapi.types.buttons.reply.requestUserReplyButton
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
 * @see ReplyKeyboardBuilder.row
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
inline fun ReplyKeyboardRowBuilder.simpleButton(
    text: String
) = add(SimpleKeyboardButton(text))

/**
 * Creates and put [RequestContactKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestContactButton(
    text: String
) = add(RequestContactKeyboardButton(text))

/**
 * Creates and put [RequestLocationKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestLocationButton(
    text: String
) = add(RequestLocationKeyboardButton(text))

/**
 * Creates and put [RequestPollKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestPollButton(
    text: String,
    pollType: KeyboardButtonPollType
) = add(RequestPollKeyboardButton(text, pollType))

/**
 * Creates and put [WebAppKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.webAppButton(
    text: String,
    webApp: WebAppInfo
) = add(WebAppKeyboardButton(text, webApp))

/**
 * Creates and put [WebAppKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.webAppButton(
    text: String,
    url: String
) = webAppButton(text, WebAppInfo(url))


/**
 * Creates and put [RequestUserKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestUsersButton(
    text: String,
    requestUser: KeyboardButtonRequestUsers
) = add(
    requestUserReplyButton(
        text,
        requestUser
    )
)

/**
 * Creates and put [RequestUserKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
@Deprecated("Renamed", ReplaceWith("requestUsersButton(text, requestUser)", "dev.inmo.tgbotapi.extensions.utils.types.buttons"))
inline fun ReplyKeyboardRowBuilder.requestUserButton(
    text: String,
    requestUser: KeyboardButtonRequestUsers
) = requestUsersButton(text, requestUser)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Bot]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestBotsButton(
    text: String,
    requestId: RequestId,
    maxCount: Int
) = requestUsersButton(
    text,
    KeyboardButtonRequestUsers.Bot(requestId, maxCount)
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Bot]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestBotButton(
    text: String,
    requestId: RequestId
) = requestBotsButton(
    text,
    requestId,
    maxCount = keyboardButtonRequestUserLimit.first
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Common]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestUsersButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null,
    maxCount: Int
) = requestUsersButton(
    text,
    KeyboardButtonRequestUsers.Common(requestId, premiumUser, maxCount)
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Common]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestUserButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null
) = requestUsersButton(text, requestId, premiumUser, maxCount = keyboardButtonRequestUserLimit.first)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Any]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestUsersOrBotsButton(
    text: String,
    requestId: RequestId,
    maxCount: Int
) = requestUsersButton(
    text,
    KeyboardButtonRequestUsers.Any(requestId, maxCount)
)

/**
 * Creates and put [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Any]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestUserOrBotButton(
    text: String,
    requestId: RequestId
) = requestUsersOrBotsButton(
    text,
    requestId,
    maxCount = keyboardButtonRequestUserLimit.first
)


/**
 * Creates and put [RequestChatKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestChatButton(
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
inline fun ReplyKeyboardRowBuilder.requestChatButton(
    text: String,
    requestId: RequestId,
    isChannel: Boolean? = null,
    isForum: Boolean? = null,
    isPublic: Boolean? = null,
    isOwnedBy: Boolean? = null,
    userRightsInChat: ChatCommonAdministratorRights? = null,
    botRightsInChat: ChatCommonAdministratorRights? = null,
    botIsMember: Boolean? = null
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
        botIsMember = botIsMember
    )
)

/**
 * Creates and put [RequestChatKeyboardButton] with [KeyboardButtonRequestChat.Channel]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestChannelButton(
    text: String,
    requestId: RequestId,
    isPublic: Boolean? = null,
    isOwnedBy: Boolean? = null,
    userRightsInChat: ChatCommonAdministratorRights? = null,
    botRightsInChat: ChatCommonAdministratorRights? = null,
    botIsMember: Boolean? = null
) = requestChatButton(
    text,
    KeyboardButtonRequestChat.Channel(
        requestId = requestId,
        isPublic = isPublic,
        isOwnedBy = isOwnedBy,
        userRightsInChat = userRightsInChat,
        botRightsInChat = botRightsInChat,
        botIsMember = botIsMember
    )
)

/**
 * Creates and put [RequestChatKeyboardButton] with [KeyboardButtonRequestChat.Group]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
inline fun ReplyKeyboardRowBuilder.requestGroupButton(
    text: String,
    requestId: RequestId,
    isForum: Boolean? = null,
    isPublic: Boolean? = null,
    isOwnedBy: Boolean? = null,
    userRightsInChat: ChatCommonAdministratorRights? = null,
    botRightsInChat: ChatCommonAdministratorRights? = null,
    botIsMember: Boolean? = null
) = requestChatButton(
    text,
    KeyboardButtonRequestChat.Group(
        requestId = requestId,
        isForum = isForum,
        isPublic = isPublic,
        isOwnedBy = isOwnedBy,
        userRightsInChat = userRightsInChat,
        botRightsInChat = botRightsInChat,
        botIsMember = botIsMember
    )
)
