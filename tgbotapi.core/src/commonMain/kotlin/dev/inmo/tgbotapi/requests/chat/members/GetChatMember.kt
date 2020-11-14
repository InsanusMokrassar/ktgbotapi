package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.tgbotapi.requests.chat.abstracts.ChatMemberRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.ChatMember.abstracts.ChatMember
import dev.inmo.tgbotapi.types.ChatMember.abstracts.ChatMemberSerializer
import kotlinx.serialization.*

@Serializable
data class GetChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId
) : ChatMemberRequest<ChatMember> {
    override fun method(): String = "getChatMember"
    override val resultDeserializer: DeserializationStrategy<ChatMember>
        get() = ChatMemberSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
