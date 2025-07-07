package dev.inmo.tgbotapi.types.checklists

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.abstracts.TitledInput
import dev.inmo.tgbotapi.types.checklists.ChecklistTask.Input
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.othersCanAddTasksField
import dev.inmo.tgbotapi.types.othersCanMarkTasksAsDoneField
import dev.inmo.tgbotapi.types.tasksField
import dev.inmo.tgbotapi.types.titleEntitiesField
import dev.inmo.tgbotapi.types.titleField
import dev.inmo.tgbotapi.utils.EntitiesBuilder
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
sealed interface Checklist : TitledInput {
    val tasks: List<ChecklistTask>
    val othersCanAddTasks: Boolean
    val othersCanCompleteTasks: Boolean
    @Serializable
    data class Input @Warning("It is low level API. Do not use it without need") constructor(
        @SerialName(titleField)
        override val title: String,
        @SerialName(tasksField)
        override val tasks: List<ChecklistTask.Input>,
        @SerialName(parseModeField)
        val parseMode: ParseMode? = null,
        @SerialName(titleEntitiesField)
        override val titleTextSources: List<TextSource> = emptyList(),
        @SerialName(othersCanAddTasksField)
        override val othersCanAddTasks: Boolean = false,
        @SerialName(othersCanMarkTasksAsDoneField)
        override val othersCanCompleteTasks: Boolean = false,
    ) : Checklist {
        constructor(
            text: String,
            tasks: List<ChecklistTask.Input>,
            parseMode: ParseMode? = null,
            othersCanAddTasks: Boolean = false,
            othersCanCompleteTasks: Boolean = false,
        ) : this(
            title = text,
            parseMode = parseMode,
            titleTextSources = emptyList(),
            tasks = tasks,
            othersCanAddTasks = othersCanAddTasks,
            othersCanCompleteTasks = othersCanCompleteTasks
        )
        constructor(
            titleTextSources: List<TextSource>,
            tasks: List<ChecklistTask.Input>,
            othersCanAddTasks: Boolean = false,
            othersCanCompleteTasks: Boolean = false,
        ) : this(
            title = titleTextSources.makeSourceString(),
            parseMode = null,
            titleTextSources = titleTextSources,
            tasks = tasks,
            othersCanAddTasks = othersCanAddTasks,
            othersCanCompleteTasks = othersCanCompleteTasks
        )
        constructor(
            tasks: List<ChecklistTask.Input>,
            othersCanAddTasks: Boolean = false,
            othersCanCompleteTasks: Boolean = false,
            builderBody: EntitiesBuilderBody
        ) : this(
            titleTextSources = EntitiesBuilder().apply(builderBody).build(),
            tasks = tasks,
            othersCanAddTasks = othersCanAddTasks,
            othersCanCompleteTasks = othersCanCompleteTasks
        )

        companion object : KSerializer<Input> {
            @Serializable
            private class RawChecklist(
                val title: String,
                val parseMode: ParseMode? = null,
                val title_entities: List<RawMessageEntity> = emptyList(),
                val tasks: List<ChecklistTask.Input>,
                val others_can_add_tasks: Boolean = false,
                val others_can_mark_tasks_as_done: Boolean = false,
            )
            override val descriptor: SerialDescriptor = RawChecklist.serializer().descriptor

            override fun serialize(
                encoder: Encoder,
                value: Input
            ) {
                RawChecklist.serializer().serialize(
                    encoder,
                    RawChecklist(
                        title = value.title,
                        title_entities = value.titleTextSources.toRawMessageEntities(),
                        tasks = value.tasks,
                        parseMode = value.parseMode,
                        others_can_add_tasks = value.othersCanAddTasks,
                        others_can_mark_tasks_as_done = value.othersCanCompleteTasks,
                    )
                )
            }

            override fun deserialize(decoder: Decoder): Input {
                val raw = RawChecklist.serializer().deserialize(decoder)
                return Input(
                    title = raw.title,
                    titleTextSources = raw.title_entities.asTextSources(raw.title),
                    tasks = raw.tasks,
                    parseMode = raw.parseMode,
                    othersCanAddTasks = raw.others_can_add_tasks,
                    othersCanCompleteTasks = raw.others_can_mark_tasks_as_done
                )
            }
        }
    }

    @Serializable(Created.Companion::class)
    data class Created(
        override val titleTextSources: List<TextSource>,
        @SerialName(tasksField)
        override val tasks: List<ChecklistTask.Created>,
        @SerialName(othersCanAddTasksField)
        override val othersCanAddTasks: Boolean = false,
        @SerialName(othersCanMarkTasksAsDoneField)
        override val othersCanCompleteTasks: Boolean = false,
    ): Checklist {
        override val title: String by lazy {
            titleTextSources.makeSourceString()
        }

        companion object : KSerializer<Created> {
            @Serializable
            private class RawChecklist(
                val title: String,
                val title_entities: List<RawMessageEntity> = emptyList(),
                val tasks: List<ChecklistTask.Created>,
                val others_can_add_tasks: Boolean = false,
                val others_can_mark_tasks_as_done: Boolean = false,
            )
            override val descriptor: SerialDescriptor = RawChecklist.serializer().descriptor

            override fun serialize(
                encoder: Encoder,
                value: Created
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

            override fun deserialize(decoder: Decoder): Created {
                val raw = RawChecklist.serializer().deserialize(decoder)
                return Created(
                    titleTextSources = raw.title_entities.asTextSources(raw.title),
                    tasks = raw.tasks,
                    othersCanAddTasks = raw.others_can_add_tasks,
                    othersCanCompleteTasks = raw.others_can_mark_tasks_as_done
                )
            }
        }
    }
}
