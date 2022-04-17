package dev.inmo.tgbotapi.requests.chat.modify

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetDefaultChatMenuButton(
    @Serializable(MenuButtonSerializer::class)
    @SerialName(menuButtonField)
    val menuButton: MenuButton
) : SimpleRequest<Boolean> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = Companion.method()

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()

    companion object {
        fun method() = "setChatMenuButton"
    }
}
