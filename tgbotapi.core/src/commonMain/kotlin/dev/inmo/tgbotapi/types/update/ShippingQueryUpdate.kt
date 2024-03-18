package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class ShippingQueryUpdate(
    override val updateId: UpdateId,
    override val data: ShippingQuery
) : Update
