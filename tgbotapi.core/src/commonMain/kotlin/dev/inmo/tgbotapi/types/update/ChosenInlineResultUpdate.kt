package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.ChosenInlineResult
import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class ChosenInlineResultUpdate(
    override val updateId: UpdateId,
    override val data: ChosenInlineResult
) : Update
