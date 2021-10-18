package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.tgbotapi.CommonAbstracts.types.UntilDate
import dev.inmo.tgbotapi.requests.chat.abstracts.ChatMemberRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class BanChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(untilDateField)
    override val untilDate: TelegramDate? = null,
    @SerialName(revokeMessagesField)
    val revokeMessages: Boolean? = null
) : ChatMemberRequest<Boolean>, UntilDate {
    override fun method(): String = "banChatMember"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
