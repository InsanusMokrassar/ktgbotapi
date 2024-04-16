package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

interface OptionallyBusinessConnectionRequest {
    val businessConnectionId: BusinessConnectionId?
}
