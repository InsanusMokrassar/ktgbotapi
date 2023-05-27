package dev.inmo.tgbotapi.types.message.abstracts

import korlibs.time.DateTime

interface PossiblyEditedMessage : Message {
    val editDate: DateTime?
}
