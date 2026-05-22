package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.abstracts.types.WithOptionalGuestQueryId
import dev.inmo.tgbotapi.types.GuestQueryId

interface RequestGuestMessage : GuestMessage, WithOptionalGuestQueryId {
    override val guestQueryId: GuestQueryId
}