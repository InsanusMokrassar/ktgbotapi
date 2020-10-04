package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery
import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class InlineQueryUpdate(
    override val updateId: UpdateIdentifier,
    override val data: InlineQuery
) : Update
