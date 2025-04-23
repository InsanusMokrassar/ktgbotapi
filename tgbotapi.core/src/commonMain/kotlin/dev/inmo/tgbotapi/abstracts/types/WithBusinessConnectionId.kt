package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

interface WithBusinessConnectionId : WithOptionalBusinessConnectionId {
    override val businessConnectionId: BusinessConnectionId
}
