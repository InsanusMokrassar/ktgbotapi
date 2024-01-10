package dev.inmo.tgbotapi.types.buttons.reply

import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.chat.member.ChatCommonAdministratorRights
import dev.inmo.tgbotapi.types.keyboardButtonRequestUserLimit
import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.types.webapps.WebAppInfo
import kotlin.math.max


/**
 * Creates [SimpleKeyboardButton]
 */
inline fun simpleReplyButton(
    text: String
) = SimpleKeyboardButton(text)

/**
 * Creates [RequestContactKeyboardButton]
 */
inline fun requestContactReplyButton(
    text: String
) = RequestContactKeyboardButton(text)

/**
 * Creates [RequestLocationKeyboardButton]
 */
inline fun requestLocationReplyButton(
    text: String
) = RequestLocationKeyboardButton(text)

/**
 * Creates [RequestPollKeyboardButton]
 */
inline fun requestPollReplyButton(
    text: String,
    pollType: KeyboardButtonPollType
) = RequestPollKeyboardButton(text, pollType)

/**
 * Creates [WebAppKeyboardButton]
 */
inline fun webAppReplyButton(
    text: String,
    webApp: WebAppInfo
) = WebAppKeyboardButton(text, webApp)

/**
 * Creates [WebAppKeyboardButton]
 */
inline fun webAppReplyButton(
    text: String,
    url: String
) = webAppReplyButton(text, WebAppInfo(url))


/**
 * Creates [RequestUserKeyboardButton]
 */
inline fun requestUsersReplyButton(
    text: String,
    requestUser: KeyboardButtonRequestUsers
) = RequestUserKeyboardButton(
    text,
    requestUser
)


/**
 * Creates [RequestUserKeyboardButton]
 */
@Deprecated("Renamed", ReplaceWith("requestUsersReplyButton(text, requestUser)", "dev.inmo.tgbotapi.types.buttons.reply.requestUsersReplyButton"))
inline fun requestUserReplyButton(
    text: String,
    requestUser: KeyboardButtonRequestUsers
) = requestUsersReplyButton(text, requestUser)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Bot]
 */
inline fun requestBotsReplyButton(
    text: String,
    requestId: RequestId,
    maxCount: Int = keyboardButtonRequestUserLimit.first
) = requestUsersReplyButton(
    text,
    KeyboardButtonRequestUsers.Bot(requestId, maxCount)
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Bot]
 */
@Deprecated("Renamed", ReplaceWith("requestBotsReplyButton(text, requestId)", "dev.inmo.tgbotapi.types.buttons.reply.requestBotsReplyButton"))
inline fun requestBotReplyButton(
    text: String,
    requestId: RequestId,
) = requestBotsReplyButton(
    text,
    requestId,
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Common]
 */
inline fun requestUsersReplyButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null,
    maxCount: Int = keyboardButtonRequestUserLimit.first
) = requestUsersReplyButton(
    text,
    KeyboardButtonRequestUsers.Common(requestId, premiumUser, maxCount)
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Common]
 */
inline fun requestUserReplyButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null,
    maxCount: Int = keyboardButtonRequestUserLimit.first
) = requestUsersReplyButton(
    text,
    requestId,
    premiumUser,
    maxCount
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Any]
 */
inline fun requestUsersOrBotsReplyButton(
    text: String,
    requestId: RequestId,
    premiumUser: Boolean? = null,
    maxCount: Int = keyboardButtonRequestUserLimit.first
) = requestUsersReplyButton(
    text,
    KeyboardButtonRequestUsers.Any(requestId, premiumUser, maxCount)
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Any]
 */
inline fun requestUserOrBotReplyButton(
    text: String,
    requestId: RequestId
) = requestUserReplyButton(
    text,
    KeyboardButtonRequestUsers.Any(requestId)
)


/**
 * Creates [RequestChatKeyboardButton]
 */
inline fun requestChatReplyButton(
    text: String,
    requestChat: KeyboardButtonRequestChat
) = RequestChatKeyboardButton(
    text,
    requestChat
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
    botIsMember: Boolean = false
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
        botIsMember = botIsMember
    )
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
    botIsMember: Boolean = false
) = requestChatReplyButton(
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
    botIsMember: Boolean? = null
) = requestChatReplyButton(
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
