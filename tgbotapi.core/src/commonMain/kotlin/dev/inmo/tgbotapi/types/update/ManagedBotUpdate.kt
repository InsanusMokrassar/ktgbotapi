package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.managed_bots.ManagedBotUpdated
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.Serializable

@Serializable
data class ManagedBotUpdate(
    override val updateId: UpdateId,
    override val data: ManagedBotUpdated
) : Update {
}