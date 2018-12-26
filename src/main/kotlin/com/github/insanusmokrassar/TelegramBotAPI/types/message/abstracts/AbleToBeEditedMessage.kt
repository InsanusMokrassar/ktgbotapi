package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import org.joda.time.DateTime

interface AbleToBeEditedMessage : Message {
    val editDate: DateTime?
}