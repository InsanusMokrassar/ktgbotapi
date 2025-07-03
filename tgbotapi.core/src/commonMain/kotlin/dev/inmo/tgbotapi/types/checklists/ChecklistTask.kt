package dev.inmo.tgbotapi.types.checklists

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.chat.PreviewUser
import dev.inmo.tgbotapi.types.completedByUserField
import dev.inmo.tgbotapi.types.completionDateField
import dev.inmo.tgbotapi.types.idField
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.RegularTextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.textEntitiesField
import dev.inmo.tgbotapi.types.textField
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
sealed interface ChecklistTask : TextedInput {
    val id: ChecklistTaskId
    override val text: String
    val completedByUser: PreviewUser?
        get() = null
    val completionDate: TelegramDate?
        get() = null

    @Serializable
    data class Undone(
        @SerialName(idField)
        override val id: ChecklistTaskId,
        @SerialName(textEntitiesField)
        override val textSources: List<TextSource> = emptyList(),
    ) : ChecklistTask {
        @OptIn(ExperimentalSerializationApi::class)
        @EncodeDefault
        @Serializable
        @SerialName(textField)
        override val text: String = textSources.makeSourceString()

        constructor(id: ChecklistTaskId, text: String): this(
            id,
            listOf(
                RegularTextSource(text)
            )
        )
    }

    @Serializable
    data class Done(
        @SerialName(idField)
        override val id: ChecklistTaskId,
        @SerialName(completedByUserField)
        override val completedByUser: PreviewUser,
        @SerialName(completionDateField)
        override val completionDate: TelegramDate,
        @SerialName(textEntitiesField)
        override val textSources: List<TextSource> = emptyList()
    ) : ChecklistTask {
        @OptIn(ExperimentalSerializationApi::class)
        @EncodeDefault
        @Serializable
        @SerialName(textField)
        override val text: String = textSources.makeSourceString()

        constructor(
            id: ChecklistTaskId,
            text: String,
            completedByUser: PreviewUser,
            completionDate: TelegramDate,
        ): this(
            id,
            completedByUser,
            completionDate,
            listOf(
                RegularTextSource(text)
            )
        )
    }
    
    
    @RiskFeature
    object Serializer : KSerializer<ChecklistTask> {
        @Serializable
        private data class RawChecklistTask(
            @SerialName(idField)
            val id: ChecklistTaskId,
            @SerialName(textField)
            val text: String,
            @SerialName(textEntitiesField)
            val textSources: List<RawMessageEntity> = emptyList(),
            @SerialName(completedByUserField)
            val completedByUser: PreviewUser? = null,
            @SerialName(completionDateField)
            val completionDate: TelegramDate = TelegramDate(0), // TelegramDate(0) is the default according to https://core.telegram.org/bots/api#checklisttask
        )
        override val descriptor: SerialDescriptor = RawChecklistTask.serializer().descriptor

        override fun deserialize(decoder: Decoder): ChecklistTask {
            val raw = RawChecklistTask.serializer().deserialize(
                decoder
            )

            return when {
                raw.completedByUser != null -> Done(
                    id = raw.id,
                    completedByUser = raw.completedByUser,
                    completionDate = raw.completionDate,
                    textSources = raw.textSources.asTextSources(raw.text),
                )
                else -> Undone(
                    id = raw.id,
                    textSources = raw.textSources.asTextSources(raw.text),
                )
            }
        }

        override fun serialize(encoder: Encoder, value: ChecklistTask) {
            RawChecklistTask.serializer().serialize(
                encoder,
                RawChecklistTask(
                    id = value.id,
                    text = value.text,
                    completedByUser = value.completedByUser,
                    completionDate = value.completionDate ?: TelegramDate(0),
                    textSources = value.textSources.toRawMessageEntities()
                )
            )
        }
    }
}