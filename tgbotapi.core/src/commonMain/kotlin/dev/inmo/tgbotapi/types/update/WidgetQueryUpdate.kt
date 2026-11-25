package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.types.widgets.WidgetQuery
import kotlinx.serialization.Serializable

@Serializable
data class WidgetQueryUpdate(
    override val updateId: UpdateId,
    override val data: WidgetQuery
) : Update
