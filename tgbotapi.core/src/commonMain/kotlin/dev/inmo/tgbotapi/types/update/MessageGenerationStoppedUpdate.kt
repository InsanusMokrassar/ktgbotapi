package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.MessageGenerationStopped
import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.Serializable

@Serializable
data class MessageGenerationStoppedUpdate(
    override val updateId: UpdateId,
    override val data: MessageGenerationStopped
) : Update
