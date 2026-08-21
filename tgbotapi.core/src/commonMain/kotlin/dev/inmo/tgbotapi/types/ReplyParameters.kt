package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.abstracts.WithMessageId
import dev.inmo.tgbotapi.types.chat.Bot
import dev.inmo.tgbotapi.types.chat.PreviewBot
import dev.inmo.tgbotapi.types.checklists.ChecklistTaskId
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.abstracts.OptionallyFromUserMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyEphemeralMessage
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.polls.PollOptionPersistentId
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(ReplyParameters.Serializer::class)
sealed interface ReplyParameters {
    val messageId: MessageId?
    val allowSendingWithoutReply: Boolean?
    val checklistTaskId: ChecklistTaskId?
    val pollOptionId: PollOptionPersistentId?

    /**
     * Reply parameters targeting a regular message identified by [messageId]. A `null` [chatIdentifier] targets a
     * message in the current chat; a non-null [chatIdentifier] targets a message in a different chat. Public
     * construction uses the `ReplyParameters(...)` companion factories.
     */
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @ConsistentCopyVisibility
    @Serializable(ReplyParameters.Serializer::class)
    data class Chat internal constructor(
        val chatIdentifier: ChatIdentifier?,
        override val messageId: MessageId,
        override val allowSendingWithoutReply: Boolean? = null,
        val quote: String? = null,
        val quoteParseMode: ParseMode? = null,
        internal val quoteEntities: List<RawMessageEntity>? = null,
        val quotePosition: Int? = null,
        override val checklistTaskId: ChecklistTaskId? = null,
        override val pollOptionId: PollOptionPersistentId? = null,
    ) : WithMessageId, ReplyParameters, TextedInput {
        override val text: String?
            get() = quote
        override val textSources: List<TextSource> by lazy {
            quoteEntities ?.asTextSources(quote ?: return@lazy emptyList()) ?: emptyList()
        }
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(ReplyParameters.Serializer::class)
    /**
     * Reply parameters targeting an incoming ephemeral message identified by [ephemeralMessageId]. The outgoing
     * reply must also be ephemeral; chat and quote fields are unsupported for ephemeral replies. Type-neutral
     * construction is available through the `ReplyParameters(...)` companion factory.
     */
    data class Ephemeral(
        val ephemeralMessageId: EphemeralMessageId,
        override val allowSendingWithoutReply: Boolean? = null,
        override val checklistTaskId: ChecklistTaskId? = null,
        override val pollOptionId: PollOptionPersistentId? = null,
    ) : ReplyParameters {
        override val messageId: MessageId? = null
    }

    companion object {
        operator fun invoke(
            ephemeralMessageId: EphemeralMessageId,
            allowSendingWithoutReply: Boolean? = null,
            checklistTaskId: ChecklistTaskId? = null,
            pollOptionId: PollOptionPersistentId? = null,
        ): Ephemeral = Ephemeral(
            ephemeralMessageId,
            allowSendingWithoutReply,
            checklistTaskId,
            pollOptionId
        )

        operator fun invoke(
            chatIdentifier: ChatIdentifier,
            messageId: MessageId,
            entities: TextSourcesList,
            allowSendingWithoutReply: Boolean? = null,
            quotePosition: Int? = null,
            checklistTaskId: ChecklistTaskId? = null,
            pollOptionId: PollOptionPersistentId? = null,
        ): Chat = Chat(
            chatIdentifier,
            messageId,
            allowSendingWithoutReply,
            entities.makeSourceString(),
            null,
            entities.toRawMessageEntities(),
            quotePosition,
            checklistTaskId,
            pollOptionId
        )

        operator fun invoke(
            metaInfo: Message.MetaInfo,
            entities: TextSourcesList,
            allowSendingWithoutReply: Boolean? = null,
            quotePosition: Int? = null,
            checklistTaskId: ChecklistTaskId? = null,
            pollOptionId: PollOptionPersistentId? = null,
        ): Chat = invoke(
            metaInfo.chatId,
            metaInfo.messageId,
            entities,
            allowSendingWithoutReply,
            quotePosition,
            checklistTaskId,
            pollOptionId
        )

        operator fun invoke(
            message: Message,
            entities: TextSourcesList,
            allowSendingWithoutReply: Boolean? = null,
            quotePosition: Int? = null,
            checklistTaskId: ChecklistTaskId? = null,
            pollOptionId: PollOptionPersistentId? = null,
        ): Chat = invoke(
            message.metaInfo,
            entities,
            allowSendingWithoutReply,
            quotePosition,
            checklistTaskId,
            pollOptionId
        )

        operator fun invoke(
            chatIdentifier: ChatIdentifier,
            messageId: MessageId,
            quote: String,
            quoteParseMode: ParseMode,
            allowSendingWithoutReply: Boolean? = null,
            quotePosition: Int? = null,
            checklistTaskId: ChecklistTaskId? = null,
            pollOptionId: PollOptionPersistentId? = null,
        ): Chat = Chat(
            chatIdentifier,
            messageId,
            allowSendingWithoutReply,
            quote,
            quoteParseMode,
            null,
            quotePosition,
            checklistTaskId,
            pollOptionId
        )

        operator fun invoke(
            metaInfo: Message.MetaInfo,
            quote: String,
            quoteParseMode: ParseMode,
            allowSendingWithoutReply: Boolean? = null,
            quotePosition: Int? = null,
            checklistTaskId: ChecklistTaskId? = null,
            pollOptionId: PollOptionPersistentId? = null,
        ): Chat = invoke(
            metaInfo.chatId,
            metaInfo.messageId,
            quote,
            quoteParseMode,
            allowSendingWithoutReply,
            quotePosition,
            checklistTaskId,
            pollOptionId
        )

        operator fun invoke(
            message: Message,
            quote: String,
            quoteParseMode: ParseMode,
            allowSendingWithoutReply: Boolean? = null,
            quotePosition: Int? = null,
            checklistTaskId: ChecklistTaskId? = null,
            pollOptionId: PollOptionPersistentId? = null,
        ): Chat = invoke(
            message.metaInfo,
            quote,
            quoteParseMode,
            allowSendingWithoutReply,
            quotePosition,
            checklistTaskId,
            pollOptionId
        )

        operator fun invoke(
            chatIdentifier: ChatIdentifier,
            messageId: MessageId,
            allowSendingWithoutReply: Boolean? = null,
            quotePosition: Int? = null,
            checklistTaskId: ChecklistTaskId? = null,
            pollOptionId: PollOptionPersistentId? = null,
        ): Chat = Chat(
            chatIdentifier,
            messageId,
            allowSendingWithoutReply,
            null,
            null,
            null,
            quotePosition,
            checklistTaskId,
            pollOptionId
        )

        operator fun invoke(
            metaInfo: Message.MetaInfo,
            allowSendingWithoutReply: Boolean? = null,
            quotePosition: Int? = null,
            checklistTaskId: ChecklistTaskId? = null,
            pollOptionId: PollOptionPersistentId? = null,
        ): Chat = invoke(
            metaInfo.chatId,
            metaInfo.messageId,
            allowSendingWithoutReply,
            quotePosition,
            checklistTaskId,
            pollOptionId
        )

        operator fun invoke(
            message: Message,
            allowSendingWithoutReply: Boolean? = null,
            quotePosition: Int? = null,
            checklistTaskId: ChecklistTaskId? = null,
            pollOptionId: PollOptionPersistentId? = null,
        ): Chat = invoke(
            message.metaInfo,
            allowSendingWithoutReply,
            quotePosition,
            checklistTaskId,
            pollOptionId
        )
    }

    object Serializer : KSerializer<ReplyParameters> {
        @Serializable
        private data class Surrogate(
            @SerialName(chatIdField) val chatIdentifier: ChatIdentifier? = null,
            @SerialName(messageIdField) val messageId: MessageId? = null,
            @SerialName(allowSendingWithoutReplyField) val allowSendingWithoutReply: Boolean? = null,
            @SerialName(quoteField) val quote: String? = null,
            @SerialName(quoteParseModeField) val quoteParseMode: ParseMode? = null,
            @SerialName(quoteEntitiesField) val quoteEntities: List<RawMessageEntity>? = null,
            @SerialName(quotePositionField) val quotePosition: Int? = null,
            @SerialName(checklistTaskIdField) val checklistTaskId: ChecklistTaskId? = null,
            @SerialName(pollOptionIdField) val pollOptionId: PollOptionPersistentId? = null,
            @SerialName(ephemeralMessageIdField) val ephemeralMessageId: EphemeralMessageId? = null,
        )

        override val descriptor: SerialDescriptor = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): ReplyParameters {
            val surrogate = decoder.decodeSerializableValue(Surrogate.serializer())
            if (surrogate.messageId != null && surrogate.ephemeralMessageId != null) {
                throw SerializationException("ReplyParameters must contain either message_id or ephemeral_message_id, not both")
            }
            return when {
                surrogate.ephemeralMessageId != null -> Ephemeral(
                    ephemeralMessageId = surrogate.ephemeralMessageId,
                    allowSendingWithoutReply = surrogate.allowSendingWithoutReply,
                    checklistTaskId = surrogate.checklistTaskId,
                    pollOptionId = surrogate.pollOptionId
                )
                surrogate.messageId != null -> Chat(
                    chatIdentifier = surrogate.chatIdentifier,
                    messageId = surrogate.messageId,
                    allowSendingWithoutReply = surrogate.allowSendingWithoutReply,
                    quote = surrogate.quote,
                    quoteParseMode = surrogate.quoteParseMode,
                    quoteEntities = surrogate.quoteEntities,
                    quotePosition = surrogate.quotePosition,
                    checklistTaskId = surrogate.checklistTaskId,
                    pollOptionId = surrogate.pollOptionId
                )
                else -> throw SerializationException("ReplyParameters must contain message_id or ephemeral_message_id")
            }
        }

        override fun serialize(encoder: Encoder, value: ReplyParameters) {
            val surrogate = when (value) {
                is Chat -> Surrogate(
                    chatIdentifier = value.chatIdentifier,
                    messageId = value.messageId,
                    allowSendingWithoutReply = value.allowSendingWithoutReply,
                    quote = value.quote,
                    quoteParseMode = value.quoteParseMode,
                    quoteEntities = value.quoteEntities,
                    quotePosition = value.quotePosition,
                    checklistTaskId = value.checklistTaskId,
                    pollOptionId = value.pollOptionId
                )
                is Ephemeral -> Surrogate(
                    allowSendingWithoutReply = value.allowSendingWithoutReply,
                    ephemeralMessageId = value.ephemeralMessageId,
                    checklistTaskId = value.checklistTaskId,
                    pollOptionId = value.pollOptionId
                )
            }
            encoder.encodeSerializableValue(Surrogate.serializer(), surrogate)
        }
    }
}

/**
 * Builds [ReplyParameters.Ephemeral] with [allowSendingWithoutReply], [checklistTaskId], and [pollOptionId], targeting
 * [ephemeralMessageId]. The target defaults to [PossiblyEphemeralMessage.ephemeralMessageId]; a missing target produces
 * `null`.
 */
fun Message.ephemeralReplyParametersOrNull(
    allowSendingWithoutReply: Boolean? = null,
    ephemeralMessageId: EphemeralMessageId? = (this as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
): ReplyParameters? =
    ephemeralMessageId ?.let {
        ReplyParameters(
            ephemeralMessageId = it,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
            pollOptionId = pollOptionId,
        )
    }

/**
 * Returns the receiver [UserId] for an ephemeral reply: [PossiblyEphemeralMessage.receiverUser] when available,
 * otherwise [OptionallyFromUserMessage.from]; returns `null` for a non-ephemeral message.
 */
val Message.ephemeralReplyReceiverUserIdOrNull: UserId?
    get() = (this as? PossiblyEphemeralMessage) ?.takeIf { it.ephemeralMessageId != null } ?.let {
        val receiverUser = it.receiverUser
        val thisAsOptionallyFromUserMessage = this as? OptionallyFromUserMessage
        val from = thisAsOptionallyFromUserMessage ?.from
        when {
            receiverUser != null && receiverUser !is PreviewBot && from !is Bot -> receiverUser.id
            from != null && from !is PreviewBot && from !is Bot -> from.id
            else -> null
        }
    }
