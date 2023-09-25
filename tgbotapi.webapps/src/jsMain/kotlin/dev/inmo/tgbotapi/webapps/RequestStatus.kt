package dev.inmo.tgbotapi.webapps

external interface RequestStatus {
    val status: String
}

inline val RequestStatus.isCancelled: Boolean
    get() = status == "cancelled"

inline val RequestStatus.isAllowed: Boolean
    get() = status == "allowed"

inline val RequestStatus.isSent: Boolean
    get() = status == "sent"