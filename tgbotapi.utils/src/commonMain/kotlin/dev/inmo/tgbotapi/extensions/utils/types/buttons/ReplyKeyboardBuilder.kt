@file:Suppress("unused", "RemoveExplicitTypeArguments")

package dev.inmo.tgbotapi.extensions.utils.types.buttons

import dev.inmo.tgbotapi.types.*
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
fun ReplyKeyboardRowBuilder.simpleButton(
    text: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(SimpleKeyboardButton(text, iconCustomEmojiId, style))

/**
 * Creates and put [RequestContactKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestContactButton(
    text: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(RequestContactKeyboardButton(text, iconCustomEmojiId, style))

/**
 * Creates and put [RequestLocationKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestLocationButton(
    text: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(RequestLocationKeyboardButton(text, iconCustomEmojiId, style))

/**
 * Creates and put [RequestPollKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestPollButton(
    text: String,
    pollType: KeyboardButtonPollType,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(RequestPollKeyboardButton(text, pollType, iconCustomEmojiId, style))

/**
 * Creates and put [WebAppKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.webAppButton(
    text: String,
    webApp: WebAppInfo,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(WebAppKeyboardButton(text, webApp, iconCustomEmojiId, style))

/**
 * Creates and put [WebAppKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.webAppButton(
    text: String,
    url: String,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = webAppButton(text, WebAppInfo(url), iconCustomEmojiId, style)


/**
 * Creates and put [RequestUserKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestUsersButton(
    text: String,
    requestUser: KeyboardButtonRequestUsers,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(
    requestUsersReplyButton(
        text,
        requestUser,
        iconCustomEmojiId,
        style
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
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = requestUsersButton(
    text,
    KeyboardButtonRequestUsers.Bot(
        requestId = requestId,
        maxCount = maxCount,
        requestName = requestName,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto
    ),
    iconCustomEmojiId,
    style
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
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = requestBotsButton(
    text,
    requestId,
    maxCount = keyboardButtonRequestUserLimit.first,
    requestName = requestName,
    requestUsername = requestUsername,
    requestPhoto = requestPhoto,
    iconCustomEmojiId = iconCustomEmojiId,
    style = style
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
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = requestUsersButton(
    text,
    KeyboardButtonRequestUsers.Common(
        requestId = requestId,
        isPremium = premiumUser,
        maxCount = maxCount,
        requestName = requestName,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto
    ),
    iconCustomEmojiId,
    style
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
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = requestUsersButton(
    text = text,
    requestId = requestId,
    premiumUser = premiumUser,
    maxCount = keyboardButtonRequestUserLimit.first,
    requestName = requestName,
    requestUsername = requestUsername,
    requestPhoto = requestPhoto,
    iconCustomEmojiId = iconCustomEmojiId,
    style = style
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
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = requestUsersButton(
    text,
    KeyboardButtonRequestUsers.Any(
        requestId = requestId,
        isPremium = premiumUser,
        maxCount = maxCount,
        requestName = requestName,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto
    ),
    iconCustomEmojiId,
    style
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
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = requestUsersOrBotsButton(
    text = text,
    requestId = requestId,
    maxCount = keyboardButtonRequestUserLimit.first,
    requestName = requestName,
    requestUsername = requestUsername,
    requestPhoto = requestPhoto,
    iconCustomEmojiId = iconCustomEmojiId,
    style = style
)


/**
 * Creates and put [RequestChatKeyboardButton]
 *
 * @see replyKeyboard
 * @see ReplyKeyboardBuilder.row
 */
fun ReplyKeyboardRowBuilder.requestChatButton(
    text: String,
    requestChat: KeyboardButtonRequestChat,
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
) = add(
    requestChatReplyButton(
        text,
        requestChat,
        iconCustomEmojiId,
        style
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
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
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
    ),
    iconCustomEmojiId,
    style
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
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
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
    ),
    iconCustomEmojiId,
    style
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
    iconCustomEmojiId: CustomEmojiId? = null,
    style: KeyboardButtonStyle? = null
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
    ),
    iconCustomEmojiId,
    style
)
