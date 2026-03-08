package dev.inmo.tgbotapi.requests.chat.modify

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetDefaultChatMenuButton(
    @Serializable(MenuButtonSerializer::class)
    @SerialName(menuButtonField)
    val menuButton: MenuButton
) : SimpleRequest<Unit> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = Companion.method()

    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer

    companion object {
        fun method() = "setChatMenuButton"
    }
}
