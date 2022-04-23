package dev.inmo.tgbotapi.requests.chat.get

import dev.inmo.tgbotapi.CommonAbstracts.types.ChatRequest
import dev.inmo.tgbotapi.CommonAbstracts.types.OptionalChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
object GetDefaultChatMenuButton : OptionalChatRequest, SimpleRequest<MenuButton> {
    override val chatId: ChatIdentifier?
        get() = null
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "getChatMenuButton"

    override val resultDeserializer: DeserializationStrategy<MenuButton>
        get() = MenuButtonSerializer
}
