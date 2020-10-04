package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.CommonBot

interface PossiblySentViaBot {
    val senderBot: CommonBot?
}
