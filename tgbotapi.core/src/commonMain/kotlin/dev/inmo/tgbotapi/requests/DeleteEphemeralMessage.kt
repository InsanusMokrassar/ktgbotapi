package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.abstracts.types.EphemeralMessageAction
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class DeleteEphemeralMessage(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(receiverUserIdField)
    override val receiverUserId: UserId,
    @SerialName(ephemeralMessageIdField)
    override val ephemeralMessageId: EphemeralMessageId
) : SimpleRequest<Unit>, EphemeralMessageAction {
    override fun method(): String = "deleteEphemeralMessage"

    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
