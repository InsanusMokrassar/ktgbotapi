package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.User

interface PossiblyGuestAnswerMessage : Message {
    val guestBotCallerUser: User?
    val guestBotCallerChat: PreviewChat?
}