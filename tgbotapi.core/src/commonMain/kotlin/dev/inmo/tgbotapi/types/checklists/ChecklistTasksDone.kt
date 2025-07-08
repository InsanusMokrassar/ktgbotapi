package dev.inmo.tgbotapi.types.checklists

import dev.inmo.tgbotapi.types.checklistMessageField
import dev.inmo.tgbotapi.types.markedAsDoneTaskIdsField
import dev.inmo.tgbotapi.types.markedAsNotDoneTaskIdsField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializerClass
import dev.inmo.tgbotapi.types.message.content.ChecklistContent
import dev.inmo.tgbotapi.types.userField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChecklistTasksDone(
    @SerialName(checklistMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializerClass::class)
    val checklistMessage: CommonMessage<ChecklistContent>,
    @SerialName(markedAsDoneTaskIdsField)
    val markedAsDone: List<ChecklistTaskId>? = null,
    @SerialName(markedAsNotDoneTaskIdsField)
    val markedAsNotDone: List<ChecklistTaskId>? = null,
) : CommonEvent
