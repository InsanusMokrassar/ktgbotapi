package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate

data class EditChannelPostUpdate(
    override val updateId: UpdateId,
    override val data: ChatContentMessage<*>
) : BaseEditMessageUpdate
