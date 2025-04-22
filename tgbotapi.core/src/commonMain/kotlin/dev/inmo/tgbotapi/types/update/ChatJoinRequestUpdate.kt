package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.chat.ChatJoinRequest
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class ChatJoinRequestUpdate(
    override val updateId: UpdateId,
    override val data: ChatJoinRequest,
) : Update
