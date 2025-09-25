package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.SendChecklist
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.DirectMessageThreadId
import dev.inmo.tgbotapi.types.EffectId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.ReplyParameters
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.checklists.Checklist
import dev.inmo.tgbotapi.types.checklists.ChecklistTask
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import kotlinx.serialization.Serializable

@Serializable
data class ChecklistContent(
    val checklist: Checklist.Created
) : MessageContent {
    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        directMessageThreadId: DirectMessageThreadId?,
        businessConnectionId: BusinessConnectionId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        allowPaidBroadcast: Boolean,
        effectId: EffectId?,
        suggestedPostParameters: SuggestedPostParameters?,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<ChecklistContent>> {
        return SendChecklist(
            chatId = chatId,
            checklist = Checklist.Input(
                titleTextSources = checklist.titleTextSources,
                tasks = checklist.tasks.map {
                    ChecklistTask.Input(
                        id = it.id,
                        textSources = it.textSources,
                    )
                },
                othersCanAddTasks = checklist.othersCanAddTasks,
                othersCanCompleteTasks = checklist.othersCanCompleteTasks,
            ),
            businessConnectionId = businessConnectionId ?: error("Checklist can be sent only with business connection"),
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    }
}
