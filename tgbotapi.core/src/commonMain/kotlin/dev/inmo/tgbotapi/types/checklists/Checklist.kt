package dev.inmo.tgbotapi.types.checklists

import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(Checklist.Companion::class)
data class Checklist(
    val titleTextSources: List<TextSource>,
    val tasks: List<ChecklistTask>,
    val othersCanAddTasks: Boolean = false,
    val othersCanCompleteTasks: Boolean = false,
) {
    val title: String by lazy {
        titleTextSources.makeSourceString()
    }

    companion object : KSerializer<Checklist> {
        @Serializable
        private class RawChecklist(
            val title: String,
            val title_entities: List<RawMessageEntity> = emptyList(),
            val tasks: List<ChecklistTask>,
            val others_can_add_tasks: Boolean = false,
            val others_can_mark_tasks_as_done: Boolean = false,
        )
        override val descriptor: SerialDescriptor = RawChecklist.serializer().descriptor

        override fun serialize(
            encoder: Encoder,
            value: Checklist
        ) {
            RawChecklist.serializer().serialize(
                encoder,
                RawChecklist(
                    title = value.title,
                    title_entities = value.titleTextSources.toRawMessageEntities(),
                    tasks = value.tasks,
                    others_can_add_tasks = value.othersCanAddTasks,
                    others_can_mark_tasks_as_done = value.othersCanCompleteTasks,
                )
            )
        }

        override fun deserialize(decoder: Decoder): Checklist {
            val raw = RawChecklist.serializer().deserialize(decoder)
            return Checklist(
                titleTextSources = raw.title_entities.asTextSources(raw.title),
                tasks = raw.tasks,
                othersCanAddTasks = raw.others_can_add_tasks,
                othersCanCompleteTasks = raw.others_can_mark_tasks_as_done
            )
        }
    }
}
