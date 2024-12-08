package dev.inmo.tgbotapi.webapps.args

interface ArgErrorObject {
    val error: String
}

val ArgErrorObject.isUnsupported
    get() = error.uppercase() == "UNSUPPORTED"

val ArgErrorObject.isAlreadyFullscreen
    get() = error.uppercase() == "ALREADY_FULLSCREEN"

val ArgErrorObject.isAMessageExpired
    get() = error.uppercase() == "MESSAGE_EXPIRED"

val ArgErrorObject.isMessageSendFailed
    get() = error.uppercase() == "MESSAGE_SEND_FAILED"

val ArgErrorObject.isUserDeclined
    get() = error.uppercase() == "USER_DECLINED"

val ArgErrorObject.isSuggestedEmojiInvalid
    get() = error.uppercase() == "SUGGESTED_EMOJI_INVALID"

val ArgErrorObject.isDurationInvalid
    get() = error.uppercase() == "DURATION_INVALID"

val ArgErrorObject.isServerError
    get() = error.uppercase() == "SERVER_ERROR"

val ArgErrorObject.isUnknownError
    get() = error.uppercase() == "UNKNOWN_ERROR"
