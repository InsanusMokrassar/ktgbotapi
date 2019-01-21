package com.github.insanusmokrassar.TelegramBotAPI.types.update

import com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery.CallbackQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update

data class CallbackQueryUpdate(
    override val updateId: UpdateIdentifier,
    override val data: CallbackQuery
) : Update
