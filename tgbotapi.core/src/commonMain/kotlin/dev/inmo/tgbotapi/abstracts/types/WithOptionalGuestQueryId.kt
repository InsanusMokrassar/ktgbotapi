package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.GuestQueryId

interface WithOptionalGuestQueryId {
    val guestQueryId: GuestQueryId?
}
