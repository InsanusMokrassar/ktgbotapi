package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnection
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.Serializable

@Serializable
data class BusinessConnectionUpdate(
    override val updateId: UpdateId,
    override val data: BusinessConnection
) : Update
