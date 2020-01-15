package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.soywiz.klock.DateTime

interface PossiblyEditedMessage : Message {
    val editDate: DateTime?
}