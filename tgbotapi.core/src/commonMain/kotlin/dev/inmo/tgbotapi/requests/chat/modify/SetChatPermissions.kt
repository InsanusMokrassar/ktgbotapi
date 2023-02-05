package dev.inmo.tgbotapi.requests.chat.modify

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.ChatPermissions
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
): ChatRequest, SimpleRequest<Boolean> {
    override fun method(): String = "setChatPermissions"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
