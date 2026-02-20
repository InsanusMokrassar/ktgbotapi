package dev.inmo.tgbotapi.types.checklists

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.PreviewUser
import dev.inmo.tgbotapi.types.completedByUserField
import dev.inmo.tgbotapi.types.completedByChatField
import dev.inmo.tgbotapi.types.completionDateField
import dev.inmo.tgbotapi.types.idField
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.RegularTextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.textEntitiesField
import dev.inmo.tgbotapi.types.textField
import dev.inmo.tgbotapi.types.textParseModeField
import dev.inmo.tgbotapi.utils.EntitiesBuilder
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
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

    @Serializable(Input.Companion::class)
    data class Input @Warning("It is low level API. Do not use it without need") constructor(
        @SerialName(idField)
        override val id: ChecklistTaskId,
        @SerialName(textField)
        override val text: String,
        @SerialName(textParseModeField)
        val parseMode: ParseMode? = null,
        @SerialName(textEntitiesField)
        override val textSources: List<TextSource> = emptyList(),
    ) : ChecklistTask {
        constructor(id: ChecklistTaskId, text: String, parseMode: ParseMode? = null) : this(
            id = id,
            text = text,
            parseMode = parseMode,
            textSources = emptyList()
        )
        constructor(id: ChecklistTaskId, textSources: List<TextSource>) : this(
            id = id,
            text = textSources.makeSourceString(),
            parseMode = null,
            textSources = textSources
        )
        constructor(id: ChecklistTaskId, builderBody: EntitiesBuilderBody) : this(
            id = id,
            textSources = EntitiesBuilder().apply(builderBody).build()
        )

        companion object : KSerializer<Input> {
            @Serializable
            private data class RawInput(
                @SerialName(idField)
                val id: ChecklistTaskId,
                @SerialName(textField)
                val text: String,
                @SerialName(textParseModeField)
                val parseMode: ParseMode? = null,
                @SerialName(textEntitiesField)
                val textSources: List<RawMessageEntity> = emptyList(),
            )
            override val descriptor: SerialDescriptor
                get() = RawInput.serializer().descriptor

            override fun deserialize(decoder: Decoder): Input {
                val raw = RawInput.serializer().deserialize(decoder)
                return Input(
                    raw.id,
                    raw.text,
                    raw.parseMode,
                    raw.textSources.asTextSources(raw.text)
                )
            }

            override fun serialize(encoder: Encoder, value: Input) {
                RawInput.serializer().serialize(
                    encoder,
                    RawInput(
                        value.id,
                        value.text,
                        value.parseMode,
                        value.textSources.toRawMessageEntities()
                    )
                )
            }
        }
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data class Uncompleted(
        @SerialName(idField)
        override val id: ChecklistTaskId,
        @SerialName(textEntitiesField)
        override val textSources: List<TextSource> = emptyList(),
    ) : ChecklistTask.Created {
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

    @Serializable(Serializer::class)
    sealed interface Created : ChecklistTask {
        val completedByUser: PreviewUser?
            get() = null
        val completedByChat: PreviewChat?
            get() = null
        val completionDate: TelegramDate?
            get() = null
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    sealed interface Completed : Created {
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @Serializable(Serializer::class)
        data class ByUser(
            @SerialName(idField)
            override val id: ChecklistTaskId,
            @SerialName(completedByUserField)
            override val completedByUser: PreviewUser,
            @SerialName(completionDateField)
            override val completionDate: TelegramDate,
            @SerialName(textEntitiesField)
            override val textSources: List<TextSource> = emptyList()
        ) : Completed {
            @OptIn(ExperimentalSerializationApi::class)
            @EncodeDefault
            @Serializable
            @SerialName(textField)
            override val text: String = textSources.makeSourceString()
        }
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @Serializable(Serializer::class)
        data class ByChat(
            @SerialName(idField)
            override val id: ChecklistTaskId,
            @SerialName(completedByChatField)
            override val completedByChat: PreviewChat,
            @SerialName(completionDateField)
            override val completionDate: TelegramDate,
            @SerialName(textEntitiesField)
            override val textSources: List<TextSource> = emptyList()
        ) : Completed {
            @OptIn(ExperimentalSerializationApi::class)
            @EncodeDefault
            @Serializable
            @SerialName(textField)
            override val text: String = textSources.makeSourceString()
        }
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @Serializable(Serializer::class)
        data class Common(
            @SerialName(idField)
            override val id: ChecklistTaskId,
            @SerialName(completionDateField)
            override val completionDate: TelegramDate,
            @SerialName(textEntitiesField)
            override val textSources: List<TextSource> = emptyList()
        ) : Completed {
            @OptIn(ExperimentalSerializationApi::class)
            @EncodeDefault
            @Serializable
            @SerialName(textField)
            override val text: String = textSources.makeSourceString()
        }
    }

    @RiskFeature
    object Serializer : KSerializer<Created> {
        @Serializable
        private data class RawCreatedChecklistTask(
            @SerialName(idField)
            val id: ChecklistTaskId,
            @SerialName(textField)
            val text: String,
            @SerialName(textEntitiesField)
            val textSources: List<RawMessageEntity> = emptyList(),
            @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
            @SerialName(completedByUserField)
            val completedByUser: PreviewUser? = null,
            @SerialName(completedByChatField)
            val completedByChat: PreviewChat? = null,
            @SerialName(completionDateField)
            val completionDate: TelegramDate = TelegramDate.Start, // TelegramDate(0) is the default according to https://core.telegram.org/bots/api#checklisttask
        )
        override val descriptor: SerialDescriptor = RawCreatedChecklistTask.serializer().descriptor

        override fun deserialize(decoder: Decoder): Created {
            val raw = RawCreatedChecklistTask.serializer().deserialize(
                decoder
            )

            return when {
                raw.completionDate == TelegramDate.Start -> Uncompleted(
                    id = raw.id,
                    textSources = raw.textSources.asTextSources(raw.text),
                )
                raw.completedByChat != null -> Completed.ByChat(
                    id = raw.id,
                    completedByChat = raw.completedByChat,
                    completionDate = raw.completionDate,
                    textSources = raw.textSources.asTextSources(raw.text),
                )
                raw.completedByUser != null -> Completed.ByUser(
                    id = raw.id,
                    completedByUser = raw.completedByUser,
                    completionDate = raw.completionDate,
                    textSources = raw.textSources.asTextSources(raw.text),
                )
                else -> Completed.Common(
                    id = raw.id,
                    completionDate = raw.completionDate,
                    textSources = raw.textSources.asTextSources(raw.text),
                )
            }
        }

        override fun serialize(encoder: Encoder, value: Created) {
            RawCreatedChecklistTask.serializer().serialize(
                encoder,
                RawCreatedChecklistTask(
                    id = value.id,
                    text = value.text,
                    completedByUser = value.completedByUser,
                    completedByChat = value.completedByChat,
                    completionDate = value.completionDate ?: TelegramDate.Start,
                    textSources = value.textSources.toRawMessageEntities()
                )
            )
        }
    }
}