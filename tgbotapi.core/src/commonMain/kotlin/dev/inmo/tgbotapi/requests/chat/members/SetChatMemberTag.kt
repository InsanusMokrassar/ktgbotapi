package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.tgbotapi.requests.chat.abstracts.ChatMemberRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*

@Serializable
data class SetChatMemberTag(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(tagField)
    val tag: UserTag? = null
) : ChatMemberRequest<Unit> {
    override fun method(): String = "setChatMemberTag"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
