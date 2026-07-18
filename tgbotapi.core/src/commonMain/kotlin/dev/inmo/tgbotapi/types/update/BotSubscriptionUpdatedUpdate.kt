package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.payments.BotSubscriptionUpdated
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.Serializable

@Serializable
data class BotSubscriptionUpdatedUpdate(
    override val updateId: UpdateId,
    override val data: BotSubscriptionUpdated
) : Update
