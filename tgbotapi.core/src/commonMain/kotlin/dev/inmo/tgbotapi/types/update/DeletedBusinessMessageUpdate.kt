package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.business_connection.BusinessMessagesDeleted
import dev.inmo.tgbotapi.types.message.abstracts.BusinessContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class DeletedBusinessMessageUpdate(
    override val updateId: UpdateId,
    override val data: BusinessMessagesDeleted
) : Update
