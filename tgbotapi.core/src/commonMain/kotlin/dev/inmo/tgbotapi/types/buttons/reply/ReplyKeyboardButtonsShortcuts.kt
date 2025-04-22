package dev.inmo.tgbotapi.types.buttons.reply

import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.chat.member.ChatCommonAdministratorRights
import dev.inmo.tgbotapi.types.keyboardButtonRequestUserLimit
import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.types.webapps.WebAppInfo

/**
 * Creates [SimpleKeyboardButton]
 */
inline fun simpleReplyButton(text: String) = SimpleKeyboardButton(text)

/**
 * Creates [RequestContactKeyboardButton]
 */
inline fun requestContactReplyButton(text: String) = RequestContactKeyboardButton(text)

/**
 * Creates [RequestLocationKeyboardButton]
 */
inline fun requestLocationReplyButton(text: String) = RequestLocationKeyboardButton(text)

/**
 * Creates [RequestPollKeyboardButton]
 */
inline fun requestPollReplyButton(
    text: String,
    pollType: KeyboardButtonPollType,
) = RequestPollKeyboardButton(text, pollType)

/**
 * Creates [WebAppKeyboardButton]
 */
inline fun webAppReplyButton(
    text: String,
    webApp: WebAppInfo,
) = WebAppKeyboardButton(text, webApp)

/**
 * Creates [WebAppKeyboardButton]
 */
inline fun webAppReplyButton(
    text: String,
    url: String,
) = webAppReplyButton(text, WebAppInfo(url))

/**
 * Creates [RequestUserKeyboardButton]
 */
inline fun requestUsersReplyButton(
    text: String,
    requestUser: KeyboardButtonRequestUsers,
) = RequestUserKeyboardButton(
    text,
    requestUser,
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Bot]
 */
inline fun requestBotsReplyButton(
    text: String,
    requestId: RequestId,
    maxCount: Int = keyboardButtonRequestUserLimit.first,
    requestName: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestUsersReplyButton(
    text,
    KeyboardButtonRequestUsers.Bot(
        requestId = requestId,
        maxCount = maxCount,
        requestName = requestName,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto,
    ),
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Common]
 */
inline fun requestUsersReplyButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null,
    maxCount: Int = keyboardButtonRequestUserLimit.first,
    requestName: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestUsersReplyButton(
    text,
    KeyboardButtonRequestUsers.Common(
        requestId = requestId,
        isPremium = premiumUser,
        maxCount = maxCount,
        requestName = requestName,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto,
    ),
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Common]
 */
inline fun requestUserReplyButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null,
    maxCount: Int = keyboardButtonRequestUserLimit.first,
    requestName: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestUsersReplyButton(
    text,
    requestId,
    premiumUser,
    maxCount,
    requestName = requestName,
    requestUsername = requestUsername,
    requestPhoto = requestPhoto,
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Any]
 */
inline fun requestUsersOrBotsReplyButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null,
    maxCount: Int = keyboardButtonRequestUserLimit.first,
    requestName: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestUsersReplyButton(
    text,
    KeyboardButtonRequestUsers.Any(
        requestId = requestId,
        isPremium = premiumUser,
        maxCount = maxCount,
        requestName = requestName,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto,
    ),
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Any]
 */
inline fun requestUserOrBotReplyButton(
    text: String,
    requestId: RequestId,
    requestName: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestUsersReplyButton(
    text,
    KeyboardButtonRequestUsers.Any(
        requestId = requestId,
        requestName = requestName,
        requestUsername = requestUsername,
        requestPhoto = requestPhoto,
    ),
)

/**
 * Creates [RequestChatKeyboardButton]
 */
inline fun requestChatReplyButton(
    text: String,
    requestChat: KeyboardButtonRequestChat,
) = RequestChatKeyboardButton(
    text,
    requestChat,
)

/**
 * Creates [RequestChatKeyboardButton] with [KeyboardButtonRequestChat]
 */
inline fun requestChatReplyButton(
    text: String,
    requestId: RequestId,
    isChannel: Boolean? = null,
    isForum: Boolean? = null,
    isPublic: Boolean? = null,
    isOwnedBy: Boolean? = null,
    userRightsInChat: ChatCommonAdministratorRights? = null,
    botRightsInChat: ChatCommonAdministratorRights? = null,
    botIsMember: Boolean = false,
    requestTitle: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestChatReplyButton(
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
)

/**
 * Creates [RequestChatKeyboardButton] with [KeyboardButtonRequestChat.Channel]
 */
inline fun requestChannelReplyButton(
    text: String,
    requestId: RequestId,
    isPublic: Boolean? = null,
    isOwnedBy: Boolean? = null,
    userRightsInChat: ChatCommonAdministratorRights? = null,
    botRightsInChat: ChatCommonAdministratorRights? = null,
    botIsMember: Boolean = false,
    requestTitle: Boolean? = null,
    requestUsername: Boolean? = null,
    requestPhoto: Boolean? = null,
) = requestChatReplyButton(
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
)

/**
 * Creates [RequestChatKeyboardButton] with [KeyboardButtonRequestChat.Group]
 */
inline fun requestChannelReplyButton(
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
) = requestChatReplyButton(
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
)
