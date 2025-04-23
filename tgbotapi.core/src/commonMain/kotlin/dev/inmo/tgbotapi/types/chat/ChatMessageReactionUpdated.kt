package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.abstracts.WithPreviewChatAndMessageId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.reactions.Reaction
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement

@Serializable(ChatMessageReactionUpdated.Companion::class)
@ClassCastsIncluded
sealed interface ChatMessageReactionUpdated : WithPreviewChatAndMessageId {
    val reactedUser: PreviewUser?
    val reactedChat: PreviewChat?
    val date: TelegramDate
    val old: List<Reaction>
    val new: List<Reaction>

    @Serializable(Companion::class)
    data class ByUser(
        @SerialName(chatField)
        override val chat: PreviewChat,
        @SerialName(messageIdField)
        override val messageId: MessageId,
        @SerialName(userField)
        override val reactedUser: PreviewUser,
        @Serializable(TelegramDateSerializer::class)
        @SerialName(dateField)
        override val date: TelegramDate,
        @SerialName(oldReactionField)
        override val old: List<Reaction>,
        @SerialName(newReactionField)
        override val new: List<Reaction>,
    ) : ChatMessageReactionUpdated {
        override val reactedChat: PreviewChat?
            get() = null
    }

    @Serializable(Companion::class)
    data class ByChat(
        @SerialName(chatField)
        override val chat: PreviewChat,
        @SerialName(messageIdField)
        override val messageId: MessageId,
        @SerialName(actorChatField)
        override val reactedChat: PreviewChat,
        @Serializable(TelegramDateSerializer::class)
        @SerialName(dateField)
        override val date: TelegramDate,
        @SerialName(oldReactionField)
        override val old: List<Reaction>,
        @SerialName(newReactionField)
        override val new: List<Reaction>,
    ) : ChatMessageReactionUpdated {
        override val reactedUser: PreviewUser?
            get() = null
    }

    @Serializable(Companion::class)
    data class Unknown(
        @SerialName(chatField)
        override val chat: PreviewChat,
        @SerialName(messageIdField)
        override val messageId: MessageId,
        @SerialName(actorChatField)
        override val reactedChat: PreviewChat?,
        @SerialName(userField)
        override val reactedUser: PreviewUser?,
        @Serializable(TelegramDateSerializer::class)
        @SerialName(dateField)
        override val date: TelegramDate,
        @SerialName(oldReactionField)
        override val old: List<Reaction>,
        @SerialName(newReactionField)
        override val new: List<Reaction>,
        val source: JsonElement?,
    ) : ChatMessageReactionUpdated

    @Serializable
    data class Surrogate internal constructor(
        @SerialName(chatField)
        val chat: PreviewChat,
        @SerialName(messageIdField)
        val messageId: MessageId,
        @SerialName(userField)
        val reactedUser: PreviewUser? = null,
        @SerialName(actorChatField)
        val reactedChat: PreviewChat? = null,
        @Serializable(TelegramDateSerializer::class)
        @SerialName(dateField)
        val date: TelegramDate,
        @SerialName(oldReactionField)
        val old: List<Reaction>,
        @SerialName(newReactionField)
        val new: List<Reaction>,
    )

    companion object : KSerializer<ChatMessageReactionUpdated> {
        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): ChatMessageReactionUpdated {
            val (surrogate, jsonElement) = if (decoder is JsonDecoder) {
                val jsonElement = decoder.decodeJsonElement()
                decoder.json.decodeFromJsonElement(Surrogate.serializer(), jsonElement) to jsonElement
            } else {
                Surrogate.serializer().deserialize(decoder) to null
            }
            return when {
                surrogate.reactedUser != null ->
                    ByUser(
                        surrogate.chat,
                        surrogate.messageId,
                        surrogate.reactedUser,
                        surrogate.date,
                        surrogate.old,
                        surrogate.new,
                    )
                surrogate.reactedChat != null ->
                    ByChat(
                        surrogate.chat,
                        surrogate.messageId,
                        surrogate.reactedChat,
                        surrogate.date,
                        surrogate.old,
                        surrogate.new,
                    )
                else ->
                    Unknown(
                        surrogate.chat,
                        surrogate.messageId,
                        surrogate.reactedUser,
                        surrogate.reactedChat,
                        surrogate.date,
                        surrogate.old,
                        surrogate.new,
                        jsonElement,
                    )
            }
        }

        override fun serialize(
            encoder: Encoder,
            value: ChatMessageReactionUpdated,
        ) {
            if (value is Unknown && value.source != null) {
                JsonElement.serializer().serialize(encoder, value.source)
            } else {
                Surrogate(
                    value.chat,
                    value.messageId,
                    value.reactedUser,
                    value.reactedChat,
                    value.date,
                    value.old,
                    value.new,
                )
            }
        }
    }
}
