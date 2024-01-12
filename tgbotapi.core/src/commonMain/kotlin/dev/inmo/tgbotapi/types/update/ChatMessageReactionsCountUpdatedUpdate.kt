package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.chat.ChatMessageReactionUpdated
import dev.inmo.tgbotapi.types.chat.ChatMessageReactionsCountUpdated
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessageReactionsCountUpdatedUpdate(
    override val updateId: UpdateIdentifier,
    override val data: ChatMessageReactionsCountUpdated
) : Update