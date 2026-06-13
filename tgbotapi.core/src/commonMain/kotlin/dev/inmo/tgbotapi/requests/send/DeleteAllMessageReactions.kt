package dev.inmo.tgbotapi.requests.send

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class DeleteAllMessageReactions @Warning(
    "Pass either userId or actorChatId, but not both. Prefer DeleteAllUserMessageReactions or DeleteAllActorChatMessageReactions factory functions"
) constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    val userId: UserId? = null,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(actorChatIdField)
    val actorChatId: ChatId? = null
) : SimpleRequest<Unit>, ChatRequest {
    override fun method(): String = "deleteAllMessageReactions"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
}

@OptIn(Warning::class)
fun DeleteAllUserMessageReactions(
    chatId: ChatIdentifier,
    userId: UserId
): DeleteAllMessageReactions = DeleteAllMessageReactions(
    chatId = chatId,
    userId = userId,
    actorChatId = null
)

@OptIn(Warning::class)
fun DeleteAllActorChatMessageReactions(
    chatId: ChatIdentifier,
    actorChatId: ChatId
): DeleteAllMessageReactions = DeleteAllMessageReactions(
    chatId = chatId,
    userId = null,
    actorChatId = actorChatId
)
