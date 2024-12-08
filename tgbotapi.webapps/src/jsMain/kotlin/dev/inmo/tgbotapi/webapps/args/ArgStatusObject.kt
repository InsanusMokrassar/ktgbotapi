package dev.inmo.tgbotapi.webapps.args

external interface ArgStatusObject {
    val status: String
}

val ArgStatusObject.isUnsupported
    get() = status.lowercase() == "unsupported"

val ArgStatusObject.isUnknown
    get() = status.lowercase() == "unknown"

val ArgStatusObject.isAdded
    get() = status.lowercase() == "added"

val ArgStatusObject.isMissed
    get() = status.lowercase() == "missed"

val ArgStatusObject.isAllowed
    get() = status.lowercase() == "allowed"

val ArgStatusObject.isDownloading
    get() = status.lowercase() == "downloading"

val ArgStatusObject.isCancelled
    get() = status.lowercase() == "cancelled"
