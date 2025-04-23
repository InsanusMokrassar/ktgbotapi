package dev.inmo.tgbotapi.types.message.abstracts

interface PossiblyOfflineMessage : Message {
    val fromOffline: Boolean
}
