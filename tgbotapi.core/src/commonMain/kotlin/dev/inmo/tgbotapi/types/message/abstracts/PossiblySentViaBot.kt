package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.CommonBot

interface PossiblySentViaBot {
    val senderBot: CommonBot?
}
