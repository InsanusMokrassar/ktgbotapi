package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.chat.ChatMessageReactionsCountUpdated
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessageReactionsCountUpdatedUpdate(
    override val updateId: UpdateId,
    override val data: ChatMessageReactionsCountUpdated
) : Update