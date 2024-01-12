package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.reactions.Reaction
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetMessageReactions(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    val messageId: MessageId,
    @SerialName(reactionField)
    val reactions: List<Reaction>,
    @SerialName(isBigField)
    val big: Boolean = false
) : SimpleRequest<Boolean>, ChatRequest {
    override fun method(): String = "setMessageReaction"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}