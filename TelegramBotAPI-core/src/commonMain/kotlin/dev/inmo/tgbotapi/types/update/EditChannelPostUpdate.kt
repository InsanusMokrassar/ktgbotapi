package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate

data class EditChannelPostUpdate(
    override val updateId: UpdateIdentifier,
    override val data: CommonMessage<*>
) : BaseEditMessageUpdate
