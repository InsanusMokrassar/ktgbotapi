package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

interface WithOptionalBusinessConnectionId {
    val businessConnectionId: BusinessConnectionId?
}