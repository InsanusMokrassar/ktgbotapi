package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.boosts.ChatBoostRemoved
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.Serializable

@Serializable
data class ChatBoostRemovedUpdate(
    override val updateId: UpdateId,
    override val data: ChatBoostRemoved
) : Update