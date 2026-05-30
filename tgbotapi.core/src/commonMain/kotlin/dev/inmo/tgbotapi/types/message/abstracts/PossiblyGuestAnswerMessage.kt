package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.PreviewUser
import dev.inmo.tgbotapi.types.chat.User

interface PossiblyGuestAnswerMessage : Message {
    val guestBotCallerUser: PreviewUser?
    val guestBotCallerChat: PreviewChat?
}