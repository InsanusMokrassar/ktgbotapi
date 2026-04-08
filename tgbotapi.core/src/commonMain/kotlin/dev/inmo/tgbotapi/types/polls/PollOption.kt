package dev.inmo.tgbotapi.types.polls

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.EntitiesBuilder
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
private data class PollOptionSurrogate(
    @SerialName(persistentIdField)
    val id: PollOptionPersistentId,
    @SerialName(textField)
    val text: String,
    @SerialName(textEntitiesField)
    val textEntities: List<RawMessageEntity> = emptyList(),
    @SerialName(votesCountField)
    val votes: Int = 0,
    @SerialName(addedByUserField)
    val addedByUser: User? = null,
    @SerialName(addedByChatField)
    val addedByChat: Chat? = null,
    @Serializable(TelegramDateSerializer::class)
    @SerialName(additionDateField)
    val additionDate: TelegramDate? = null
)

@Serializable(PollOption.Companion::class)
sealed interface PollOption : TextedInput {
    val id: PollOptionPersistentId
    val votes: Int

    fun asInput(): InputPollOption

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(PollOption.Companion::class)
    data class Simple(
        @SerialName(persistentIdField)
        override val id: PollOptionPersistentId,
        @SerialName(textField)
        override val text: String,
        @SerialName(textEntitiesField)
        override val textSources: List<TextSource> = emptyList(),
        @SerialName(votesCountField)
        override val votes: Int = 0
    ) : PollOption {
        override fun asInput(): InputPollOption = InputPollOption(text, null, textSources)
    }

    @Serializable(PollOption.LatelyAdded.Companion::class)
    sealed interface LatelyAdded : PollOption {
        val additionDate: TelegramDate

        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @Serializable(PollOption.LatelyAdded.Companion::class)
        data class AddedByUser(
            @SerialName(persistentIdField)
            override val id: PollOptionPersistentId,
            @SerialName(textField)
            override val text: String,
            @SerialName(textEntitiesField)
            override val textSources: List<TextSource> = emptyList(),
            @SerialName(votesCountField)
            override val votes: Int = 0,
            @SerialName(addedByUserField)
            val addedByUser: User,
            @Serializable(TelegramDateSerializer::class)
            @SerialName(additionDateField)
            override val additionDate: TelegramDate
        ) : LatelyAdded {
            override fun asInput(): InputPollOption = InputPollOption(text, null, textSources)
        }

        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @Serializable(PollOption.LatelyAdded.Companion::class)
        data class AddedByChat(
            @SerialName(persistentIdField)
            override val id: PollOptionPersistentId,
            @SerialName(textField)
            override val text: String,
            @SerialName(textEntitiesField)
            override val textSources: List<TextSource> = emptyList(),
            @SerialName(votesCountField)
            override val votes: Int = 0,
            @SerialName(addedByChatField)
            val addedByChat: Chat,
            @Serializable(TelegramDateSerializer::class)
            @SerialName(additionDateField)
            override val additionDate: TelegramDate
        ) : LatelyAdded {
            override fun asInput(): InputPollOption = InputPollOption(text, null, textSources)
        }

        @RiskFeature
        companion object : KSerializer<LatelyAdded> {
            override val descriptor: SerialDescriptor = PollOptionSurrogate.serializer().descriptor

            override fun deserialize(decoder: Decoder): LatelyAdded {
                val surrogate = PollOptionSurrogate.serializer().deserialize(decoder)
                val textSources = surrogate.textEntities.asTextSources(surrogate.text)
                return when {
                    surrogate.addedByUser != null && surrogate.additionDate != null -> AddedByUser(
                        id = surrogate.id,
                        text = surrogate.text,
                        textSources = textSources,
                        votes = surrogate.votes,
                        addedByUser = surrogate.addedByUser,
                        additionDate = surrogate.additionDate
                    )
                    surrogate.addedByChat != null && surrogate.additionDate != null -> AddedByChat(
                        id = surrogate.id,
                        text = surrogate.text,
                        textSources = textSources,
                        votes = surrogate.votes,
                        addedByChat = surrogate.addedByChat,
                        additionDate = surrogate.additionDate
                    )
                    else -> error("LatelyAdded poll option must have either added_by_user or added_by_chat")
                }
            }

            override fun serialize(encoder: Encoder, value: LatelyAdded) {
                PollOptionSurrogate.serializer().serialize(
                    encoder,
                    when (value) {
                        is AddedByUser -> PollOptionSurrogate(
                            id = value.id,
                            text = value.text,
                            textEntities = value.textSources.toRawMessageEntities(),
                            votes = value.votes,
                            addedByUser = value.addedByUser,
                            additionDate = value.additionDate
                        )
                        is AddedByChat -> PollOptionSurrogate(
                            id = value.id,
                            text = value.text,
                            textEntities = value.textSources.toRawMessageEntities(),
                            votes = value.votes,
                            addedByChat = value.addedByChat,
                            additionDate = value.additionDate
                        )
                    }
                )
            }
        }
    }

    @RiskFeature
    companion object : KSerializer<PollOption> {
        override val descriptor: SerialDescriptor = PollOptionSurrogate.serializer().descriptor

        fun simple(
            id: PollOptionPersistentId,
            text: String,
            textSources: List<TextSource>,
            votes: Int = 0
        ) = Simple(id, text, textSources, votes)
        fun simple(
            id: PollOptionPersistentId,
            textSources: List<TextSource>,
            votes: Int = 0
        ) = Simple(id, textSources.makeSourceString(), textSources, votes)
        fun simple(
            id: PollOptionPersistentId,
            votes: Int = 0,
            builder: EntitiesBuilderBody
        ) = simple(
            id,
            EntitiesBuilder().apply(builder).build(),
            votes
        )

        override fun deserialize(decoder: Decoder): PollOption {
            val surrogate = PollOptionSurrogate.serializer().deserialize(decoder)
            val textSources = surrogate.textEntities.asTextSources(surrogate.text)
            return when {
                surrogate.addedByUser != null && surrogate.additionDate != null -> LatelyAdded.AddedByUser(
                    id = surrogate.id,
                    text = surrogate.text,
                    textSources = textSources,
                    votes = surrogate.votes,
                    addedByUser = surrogate.addedByUser,
                    additionDate = surrogate.additionDate
                )
                surrogate.addedByChat != null && surrogate.additionDate != null -> LatelyAdded.AddedByChat(
                    id = surrogate.id,
                    text = surrogate.text,
                    textSources = textSources,
                    votes = surrogate.votes,
                    addedByChat = surrogate.addedByChat,
                    additionDate = surrogate.additionDate
                )
                else -> Simple(
                    id = surrogate.id,
                    text = surrogate.text,
                    textSources = textSources,
                    votes = surrogate.votes
                )
            }
        }

        override fun serialize(encoder: Encoder, value: PollOption) {
            PollOptionSurrogate.serializer().serialize(
                encoder,
                when (value) {
                    is Simple -> PollOptionSurrogate(
                        id = value.id,
                        text = value.text,
                        textEntities = value.textSources.toRawMessageEntities(),
                        votes = value.votes
                    )
                    is LatelyAdded -> when (value) {
                        is LatelyAdded.AddedByUser -> PollOptionSurrogate(
                            id = value.id,
                            text = value.text,
                            textEntities = value.textSources.toRawMessageEntities(),
                            votes = value.votes,
                            addedByUser = value.addedByUser,
                            additionDate = value.additionDate
                        )
                        is LatelyAdded.AddedByChat -> PollOptionSurrogate(
                            id = value.id,
                            text = value.text,
                            textEntities = value.textSources.toRawMessageEntities(),
                            votes = value.votes,
                            addedByChat = value.addedByChat,
                            additionDate = value.additionDate
                        )
                    }
                }
            )
        }
    }
}
