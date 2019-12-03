package com.github.insanusmokrassar.TelegramBotAPI.types.update

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.PreCheckoutQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update

data class PreCheckoutQueryUpdate(
    override val updateId: UpdateIdentifier,
    override val data: PreCheckoutQuery
) : Update
