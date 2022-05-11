package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.chat.ChatJoinRequest
import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class ChatJoinRequestUpdate(
    override val updateId: UpdateIdentifier,
    override val data: ChatJoinRequest
) : Update
