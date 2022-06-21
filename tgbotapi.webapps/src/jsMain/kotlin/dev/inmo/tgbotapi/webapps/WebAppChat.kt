package dev.inmo.tgbotapi.webapps

import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.chat.PublicChat

external interface WebAppChat {
    val id: ChatIdentifier
    val type: String
    val title: String
    val username: Username?
    @JsName("photo_url")
    val photoUrl: String?
}
