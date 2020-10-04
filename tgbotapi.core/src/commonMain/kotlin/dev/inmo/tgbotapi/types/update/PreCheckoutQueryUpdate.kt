package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class PreCheckoutQueryUpdate(
    override val updateId: UpdateIdentifier,
    override val data: PreCheckoutQuery
) : Update
