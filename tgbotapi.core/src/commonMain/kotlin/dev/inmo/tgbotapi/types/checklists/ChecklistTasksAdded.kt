package dev.inmo.tgbotapi.types.checklists

import dev.inmo.tgbotapi.types.checklistMessageField
import dev.inmo.tgbotapi.types.markedAsDoneTaskIdsField
import dev.inmo.tgbotapi.types.markedAsNotDoneTaskIdsField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.ChecklistContent
import dev.inmo.tgbotapi.types.tasksField
import dev.inmo.tgbotapi.types.userField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChecklistTasksAdded(
    @SerialName(checklistMessageField)
    val checklistMessage: CommonMessage<ChecklistContent>,
    @SerialName(tasksField)
    val tasks: List<ChecklistTask.Created>,
) : CommonEvent
