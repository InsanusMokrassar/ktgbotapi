package dev.inmo.tgbotapi.requests.chat.get

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.chat.ChatShareThemeBackdrops
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

/** Retrieves the available backdrops for chat share themes. */
@Serializable
data object GetChatShareThemeBackdrops : SimpleRequest<ChatShareThemeBackdrops> {
    override fun method(): String = "getChatShareThemeBackdrops"
    override val resultDeserializer: DeserializationStrategy<ChatShareThemeBackdrops>
        get() = ChatShareThemeBackdrops.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
