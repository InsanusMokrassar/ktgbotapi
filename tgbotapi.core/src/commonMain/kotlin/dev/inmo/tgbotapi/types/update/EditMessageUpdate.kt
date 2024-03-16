package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate

data class EditMessageUpdate(
    override val updateId: UpdateId,
    override val data: CommonMessage<*>
) : BaseEditMessageUpdate
