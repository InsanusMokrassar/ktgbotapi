@file:Suppress("unused")

package dev.inmo.tgbotapi.types.buttons.reply

import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.chat.member.ChatCommonAdministratorRights
import dev.inmo.tgbotapi.types.keyboardButtonRequestUserLimit
import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.types.webapps.WebAppInfo


/**
 * Creates [SimpleKeyboardButton]
 */
fun simpleReplyButton(
    text: String
) = SimpleKeyboardButton(text)

/**
 * Creates [RequestContactKeyboardButton]
 */
fun requestContactReplyButton(
    text: String
) = RequestContactKeyboardButton(text)

/**
 * Creates [RequestLocationKeyboardButton]
 */
fun requestLocationReplyButton(
    text: String
) = RequestLocationKeyboardButton(text)

/**
 * Creates [RequestPollKeyboardButton]
 */
fun requestPollReplyButton(
    text: String,
    pollType: KeyboardButtonPollType
) = RequestPollKeyboardButton(text, pollType)

/**
 * Creates [WebAppKeyboardButton]
 */
fun webAppReplyButton(
    text: String,
    webApp: WebAppInfo
) = WebAppKeyboardButton(text, webApp)

/**
 * Creates [WebAppKeyboardButton]
 */
fun webAppReplyButton(
    text: String,
    url: String
) = webAppReplyButton(text, WebAppInfo(url))


/**
 * Creates [RequestUserKeyboardButton]
 */
fun requestUsersReplyButton(
    text: String,
    requestUser: KeyboardButtonRequestUsers
) = RequestUserKeyboardButton(
    text,
    requestUser
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Bot]
 */
fun requestBotsReplyButton(
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
        requestPhoto = requestPhoto
    )
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Common]
 */
fun requestUsersReplyButton(
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
        requestPhoto = requestPhoto
    )
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Common]
 */
fun requestUserReplyButton(
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
    requestPhoto = requestPhoto
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Any]
 */
fun requestUsersOrBotsReplyButton(
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
        requestPhoto = requestPhoto
    )
)

/**
 * Creates [RequestUserKeyboardButton] with [KeyboardButtonRequestUsers.Any]
 */
fun requestUserOrBotReplyButton(
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
        requestPhoto = requestPhoto
    )
)


/**
 * Creates [RequestChatKeyboardButton]
 */
fun requestChatReplyButton(
    text: String,
    requestChat: KeyboardButtonRequestChat
) = RequestChatKeyboardButton(
    text,
    requestChat
)

/**
 * Creates [RequestChatKeyboardButton] with [KeyboardButtonRequestChat]
 */
fun requestChatReplyButton(
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
    )
)

/**
 * Creates [RequestChatKeyboardButton] with [KeyboardButtonRequestChat.Channel]
 */
fun requestChannelReplyButton(
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
    )
)


/**
 * Creates [RequestChatKeyboardButton] with [KeyboardButtonRequestChat.Group]
 */
fun requestChannelReplyButton(
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
    )
)
