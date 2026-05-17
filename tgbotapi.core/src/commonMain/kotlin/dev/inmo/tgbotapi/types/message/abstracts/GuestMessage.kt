package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.abstracts.types.WithOptionalGuestQueryId
import dev.inmo.tgbotapi.types.GuestQueryId
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.User

interface GuestMessage : WithOptionalGuestQueryId {
    override val guestQueryId: GuestQueryId
    val guestBotCallerUser: User
    val guestBotCallerChat: PreviewChat
}
