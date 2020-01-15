package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.ForwardInfo

interface PossiblyForwardedMessage : Message {
    val forwardInfo: ForwardInfo?
    @Deprecated(
        "Renamed",
        ReplaceWith("forwardInfo")
    )
    val forwarded: ForwardInfo?
        get() = forwardInfo
}