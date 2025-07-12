package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.tgbotapi.abstracts.types.UntilDate
import dev.inmo.tgbotapi.requests.chat.abstracts.ChatMemberRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.ChatPermissions
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class RestrictChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(untilDateField)
    override val untilDate: TelegramDate? = null,
    @SerialName(permissionsField)
    val permissions: ChatPermissions = ChatPermissions(),
    @SerialName(useIndependentChatPermissionsField)
    val useIndependentChatPermissions: Boolean? = permissions.isGranular.takeIf { it }
) : ChatMemberRequest<Boolean>, UntilDate {
    override fun method(): String = "restrictChatMember"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
