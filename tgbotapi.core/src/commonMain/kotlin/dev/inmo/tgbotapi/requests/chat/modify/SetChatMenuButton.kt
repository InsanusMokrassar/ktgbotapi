package dev.inmo.tgbotapi.requests.chat.modify

import dev.inmo.tgbotapi.CommonAbstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetChatMenuButton(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @Serializable(MenuButtonSerializer::class)
    @SerialName(menuButtonField)
    val menuButton: MenuButton
) : ChatRequest, SimpleRequest<Boolean> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = SetDefaultChatMenuButton.method()

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}
