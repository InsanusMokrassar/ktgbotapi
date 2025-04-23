package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery
import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class InlineQueryUpdate(
    override val updateId: UpdateId,
    override val data: InlineQuery,
) : Update
