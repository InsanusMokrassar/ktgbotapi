package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.tgbotapi.requests.chat.abstracts.ChatMemberRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.member.ChatMember
import dev.inmo.tgbotapi.types.chat.member.ChatMemberSerializer
import kotlinx.serialization.*

@Serializable
data class GetChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    override val userId: UserId
) : ChatMemberRequest<ChatMember> {
    override fun method(): String = "getChatMember"
    override val resultDeserializer: DeserializationStrategy<ChatMember>
        get() = ChatMemberSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
