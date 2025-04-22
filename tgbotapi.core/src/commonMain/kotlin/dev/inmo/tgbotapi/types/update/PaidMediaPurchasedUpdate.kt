package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.message.payments.PaidMediaPurchased
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.Serializable

@Serializable
data class PaidMediaPurchasedUpdate(
    override val updateId: UpdateId,
    override val data: PaidMediaPurchased,
) : Update
