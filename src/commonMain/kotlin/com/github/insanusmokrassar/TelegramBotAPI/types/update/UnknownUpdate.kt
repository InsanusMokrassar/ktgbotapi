package com.github.insanusmokrassar.TelegramBotAPI.types.update

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update

data class UnknownUpdate(
    override val updateId: UpdateIdentifier,
    override val data: String
) : Update
