package com.github.insanusmokrassar.TelegramBotAPI.types.update

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.ChosenInlineResult
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update

data class ChosenInlineResultUpdate(
    override val updateId: UpdateIdentifier,
    override val data: ChosenInlineResult
) : Update