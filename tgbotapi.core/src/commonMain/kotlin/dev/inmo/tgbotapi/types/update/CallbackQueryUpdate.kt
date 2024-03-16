package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.queries.callback.CallbackQuery
import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class CallbackQueryUpdate(
    override val updateId: UpdateId,
    override val data: CallbackQuery
) : Update
