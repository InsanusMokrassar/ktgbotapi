package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class ShippingQueryUpdate(
    override val updateId: UpdateIdentifier,
    override val data: ShippingQuery
) : Update
