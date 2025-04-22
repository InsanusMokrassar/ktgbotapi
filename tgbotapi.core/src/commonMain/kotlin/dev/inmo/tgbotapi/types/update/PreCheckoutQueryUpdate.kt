package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class PreCheckoutQueryUpdate(
    override val updateId: UpdateId,
    override val data: PreCheckoutQuery,
) : Update
