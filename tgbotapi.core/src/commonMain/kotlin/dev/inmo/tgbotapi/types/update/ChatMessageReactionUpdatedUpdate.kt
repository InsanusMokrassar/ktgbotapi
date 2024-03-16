package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.chat.ChatMessageReactionUpdated
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessageReactionUpdatedUpdate(
    override val updateId: UpdateId,
    override val data: ChatMessageReactionUpdated
) : Update