package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.CallbackQuery.CallbackQuery
import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class CallbackQueryUpdate(
    override val updateId: UpdateIdentifier,
    override val data: CallbackQuery
) : Update
