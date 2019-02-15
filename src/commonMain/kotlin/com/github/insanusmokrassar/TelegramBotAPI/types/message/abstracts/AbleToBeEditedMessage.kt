package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.soywiz.klock.DateTime

interface AbleToBeEditedMessage : Message {
    val editDate: DateTime?
}