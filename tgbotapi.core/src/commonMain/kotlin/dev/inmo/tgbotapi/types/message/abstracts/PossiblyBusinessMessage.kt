package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.abstracts.types.WithOptionalBusinessConnectionId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

interface PossiblyBusinessMessage : WithOptionalBusinessConnectionId {
    override val businessConnectionId: BusinessConnectionId?
        get() = null
}
