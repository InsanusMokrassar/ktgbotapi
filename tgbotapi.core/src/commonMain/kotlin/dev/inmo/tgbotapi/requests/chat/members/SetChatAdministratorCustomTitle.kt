package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.tgbotapi.requests.chat.abstracts.ChatMemberRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetChatAdministratorCustomTitle(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(customTitleField)
    val customTitle: String
) : ChatMemberRequest<Unit> {
    override fun method(): String = "setChatAdministratorCustomTitle"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        if (customTitle.length !in customTitleLength) {
            throw IllegalArgumentException("Custom title length must be in range $customTitleLength, but was ${customTitle.length}")
        }
    }
}
