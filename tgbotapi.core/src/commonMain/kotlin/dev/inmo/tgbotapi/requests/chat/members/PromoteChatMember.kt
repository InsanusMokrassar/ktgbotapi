package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.tgbotapi.abstracts.types.UntilDate
import dev.inmo.tgbotapi.requests.chat.abstracts.ChatMemberRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class PromoteChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(untilDateField)
    override val untilDate: TelegramDate? = null,
    @SerialName(isAnonymousField)
    private val isAnonymous: Boolean? = null,
    @SerialName(canChangeInfoField)
    private val canChangeInfo: Boolean? = null,
    @SerialName(canPostMessagesField)
    private val canPostMessages: Boolean? = null,
    @SerialName(canEditMessagesField)
    private val canEditMessages: Boolean? = null,
    @SerialName(canDeleteMessagesField)
    private val canDeleteMessages: Boolean? = null,
    @SerialName(canInviteUsersField)
    private val canInviteUsers: Boolean? = null,
    @SerialName(canRestrictMembersField)
    private val canRestrictMembers: Boolean? = null,
    @SerialName(canPinMessagesField)
    private val canPinMessages: Boolean? = null,
    @SerialName(canPromoteMembersField)
    private val canPromoteMembers: Boolean? = null,
    @SerialName(canManageVideoChatsField)
    private val canManageVideoChats: Boolean? = null,
    @SerialName(canManageChatField)
    private val canManageChat: Boolean? = null,
    @SerialName(canManageTopicsField)
    private val canManageTopics: Boolean? = null
) : ChatMemberRequest<Boolean>, UntilDate {
    override fun method(): String = "promoteChatMember"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
