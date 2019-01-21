package com.github.insanusmokrassar.TelegramBotAPI.types.update

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.ShippingQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update

data class ShippingQueryUpdate(
    override val updateId: UpdateIdentifier,
    override val data: ShippingQuery
) : Update
