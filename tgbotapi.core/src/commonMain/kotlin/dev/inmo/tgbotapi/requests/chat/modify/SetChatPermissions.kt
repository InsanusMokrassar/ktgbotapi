package dev.inmo.tgbotapi.requests.chat.modify

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.ChatPermissions
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetChatPermissions (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(permissionsField)
    val permissions: ChatPermissions,
    @SerialName(useIndependentChatPermissionsField)
    val useIndependentChatPermissions: Boolean? = permissions.isGranular.takeIf { it }
): ChatRequest, SimpleRequest<Unit> {
    override fun method(): String = "setChatPermissions"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
