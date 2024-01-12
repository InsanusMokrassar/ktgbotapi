package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.boosts.ChatBoostRemoved
import dev.inmo.tgbotapi.types.boosts.ChatBoostUpdated
import dev.inmo.tgbotapi.types.chat.ChatMessageReactionUpdated
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.Serializable

@Serializable
data class ChatBoostRemovedUpdate(
    override val updateId: UpdateIdentifier,
    override val data: ChatBoostRemoved
) : Update