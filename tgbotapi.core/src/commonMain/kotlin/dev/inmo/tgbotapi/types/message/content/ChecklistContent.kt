package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.EffectId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.ReplyParameters
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.checklists.Checklist
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import kotlinx.serialization.Serializable

@Serializable
data class ChecklistContent(
    val checklist: Checklist
) : MessageContent {
    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        businessConnectionId: BusinessConnectionId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        allowPaidBroadcast: Boolean,
        effectId: EffectId?,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<CommonMessage<ChecklistContent>> {
        return SendChecklist
    }
}
