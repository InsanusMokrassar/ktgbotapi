package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.abstracts.types.WithOptionalGuestQueryId
import dev.inmo.tgbotapi.types.GuestQueryId

interface RequestGuestMessage : Message, WithOptionalGuestQueryId {
    override val guestQueryId: GuestQueryId
}