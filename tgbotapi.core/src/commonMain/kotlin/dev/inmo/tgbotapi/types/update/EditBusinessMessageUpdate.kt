package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.message.abstracts.BusinessContentMessage
import dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate

data class EditBusinessMessageUpdate(
    override val updateId: UpdateId,
    override val data: BusinessContentMessage<*>,
) : BaseEditMessageUpdate
