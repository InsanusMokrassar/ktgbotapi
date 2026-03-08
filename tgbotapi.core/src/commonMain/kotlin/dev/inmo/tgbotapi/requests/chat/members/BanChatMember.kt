package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.tgbotapi.abstracts.types.UntilDate
import dev.inmo.tgbotapi.requests.chat.abstracts.ChatMemberRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class BanChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(untilDateField)
    override val untilDate: TelegramDate? = null,
    @SerialName(revokeMessagesField)
    val revokeMessages: Boolean? = null
) : ChatMemberRequest<Unit>, UntilDate {
    override fun method(): String = "banChatMember"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
