package dev.inmo.tgbotapi.requests.verifications

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chatIdField
import dev.inmo.tgbotapi.types.customDescriptionField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class VerifyChat(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(customDescriptionField)
    val description: String? = null,
) : ChatRequest, SimpleRequest<Boolean> {
    override fun method(): String = "verifyChat"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
