package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.tgbotapi.requests.chat.abstracts.ChatMemberRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class UnbanChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(onlyIfBannedField)
    val onlyIfBanned: Boolean? = null
) : ChatMemberRequest<Boolean> {
    override fun method(): String = "unbanChatMember"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
