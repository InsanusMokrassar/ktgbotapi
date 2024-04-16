package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

interface PossiblyBusinessMessage : Message {
    val businessConnectionId: BusinessConnectionId?
        get() = null
}