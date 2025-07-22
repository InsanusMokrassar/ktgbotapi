package dev.inmo.tgbotapi.types.checklists

import dev.inmo.tgbotapi.types.checklistMessageField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializerClass
import dev.inmo.tgbotapi.types.message.content.ChecklistContent
import dev.inmo.tgbotapi.types.tasksField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChecklistTasksAdded(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(checklistMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializerClass::class)
    val checklistMessage: CommonMessage<ChecklistContent>,
    @SerialName(tasksField)
    val tasks: List<ChecklistTask.Created>,
) : CommonEvent
