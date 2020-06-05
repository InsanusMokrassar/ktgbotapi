package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.CommonBot

interface PossiblySentViaBot {
    val senderBot: CommonBot?
}
