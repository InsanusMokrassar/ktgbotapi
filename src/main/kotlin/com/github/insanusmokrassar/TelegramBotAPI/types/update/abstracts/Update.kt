package com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier

interface Update<T: Any> {
    val updateId: UpdateIdentifier
    val data: T
}
