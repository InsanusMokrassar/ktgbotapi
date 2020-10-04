package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.InlineQueries.abstracts.ChosenInlineResult
import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class ChosenInlineResultUpdate(
    override val updateId: UpdateIdentifier,
    override val data: ChosenInlineResult
) : Update