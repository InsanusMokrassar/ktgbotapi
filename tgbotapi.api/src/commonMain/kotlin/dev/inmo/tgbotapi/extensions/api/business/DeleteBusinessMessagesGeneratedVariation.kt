// THIS CODE HAVE BEEN GENERATED AUTOMATICALLY
// TO REGENERATE IT JUST DELETE FILE
// ORIGINAL FILE: DeleteBusinessMessages.kt
package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import kotlin.Boolean
import kotlin.collections.List

public suspend fun TelegramBot.deleteBusinessMessages(
    businessConnectionId: BusinessConnectionId,
    messages: List<AccessibleMessage>,
): Boolean =
    deleteBusinessMessages(
        businessConnectionId = businessConnectionId,
        messageIds =
            with(messages) {
                messages.map {
                    it.messageId
                } 
            },
    )
