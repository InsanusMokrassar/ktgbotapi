package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent
import kotlinx.serialization.Serializable

/** The sender used a link granting free messages to the recipient. */
@Serializable
data object PaidMessagesWaived : PrivateEvent
