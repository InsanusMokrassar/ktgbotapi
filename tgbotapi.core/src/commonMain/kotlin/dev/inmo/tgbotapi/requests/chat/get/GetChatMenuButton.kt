package dev.inmo.tgbotapi.requests.chat.get

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class GetChatMenuButton(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
) : ChatRequest, SimpleRequest<MenuButton> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = GetDefaultChatMenuButton.method()

    override val resultDeserializer: DeserializationStrategy<MenuButton>
        get() = MenuButtonSerializer
}
