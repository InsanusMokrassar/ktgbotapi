package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.abstracts.types.UntilDate
import dev.inmo.tgbotapi.requests.chat.abstracts.ChatMemberRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
@Warning("This method is too common. Use it with caution")
data class PromoteChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(untilDateField)
    override val untilDate: TelegramDate?,
    @SerialName(isAnonymousField)
    private val isAnonymous: Boolean?,
    @SerialName(canChangeInfoField)
    private val canChangeInfo: Boolean?,
    @SerialName(canPostMessagesField)
    private val canPostMessages: Boolean?,
    @SerialName(canEditMessagesField)
    private val canEditMessages: Boolean?,
    @SerialName(canDeleteMessagesField)
    private val canDeleteMessages: Boolean?,
    @SerialName(canInviteUsersField)
    private val canInviteUsers: Boolean?,
    @SerialName(canRestrictMembersField)
    private val canRestrictMembers: Boolean?,
    @SerialName(canPinMessagesField)
    private val canPinMessages: Boolean?,
    @SerialName(canPromoteMembersField)
    private val canPromoteMembers: Boolean?,
    @SerialName(canManageVideoChatsField)
    private val canManageVideoChats: Boolean?,
    @SerialName(canManageChatField)
    private val canManageChat: Boolean?,
    @SerialName(canManageTopicsField)
    private val canManageTopics: Boolean?,
    @SerialName(canPostStoriesField)
    private val canPostStories: Boolean?,
    @SerialName(canEditStoriesField)
    private val canEditStories: Boolean?,
    @SerialName(canDeleteStoriesField)
    private val canDeleteStories: Boolean?
) : ChatMemberRequest<Boolean>, UntilDate {
    override fun method(): String = "promoteChatMember"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

fun PromoteChatMember(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
) = PromoteChatMember(
    chatId = chatId,
    userId = userId,
    untilDate = untilDate,
    isAnonymous = isAnonymous,
    canChangeInfo = canChangeInfo,
    canPostMessages = null,
    canEditMessages = null,
    canDeleteMessages = canDeleteMessages,
    canInviteUsers = canInviteUsers,
    canRestrictMembers = canRestrictMembers,
    canPinMessages = null,
    canPromoteMembers = canPromoteMembers,
    canManageVideoChats = canManageVideoChats,
    canManageChat = canManageChat,
    canManageTopics = null,
    canPostStories = null,
    canEditStories = null,
    canDeleteStories = null
)

fun PromoteChannelAdministrator(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canPostMessages: Boolean? = null,
    canEditMessages: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canPostStories: Boolean? = null,
    canEditStories: Boolean? = null,
    canDeleteStories: Boolean? = null
) = PromoteChatMember(
    chatId = chatId,
    userId = userId,
    untilDate = untilDate,
    isAnonymous = isAnonymous,
    canChangeInfo = canChangeInfo,
    canPostMessages = canPostMessages,
    canEditMessages = canEditMessages,
    canDeleteMessages = canDeleteMessages,
    canInviteUsers = canInviteUsers,
    canRestrictMembers = canRestrictMembers,
    canPinMessages = null,
    canPromoteMembers = canPromoteMembers,
    canManageVideoChats = canManageVideoChats,
    canManageChat = canManageChat,
    canManageTopics = null,
    canPostStories = canPostStories,
    canEditStories = canEditStories,
    canDeleteStories = canDeleteStories
)

fun PromoteSupergroupAdministrator(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTopics: Boolean? = null,
) = PromoteChatMember(
    chatId = chatId,
    userId = userId,
    untilDate = untilDate,
    isAnonymous = isAnonymous,
    canChangeInfo = canChangeInfo,
    canPostMessages = null,
    canEditMessages = null,
    canDeleteMessages = canDeleteMessages,
    canInviteUsers = canInviteUsers,
    canRestrictMembers = canRestrictMembers,
    canPinMessages = canPinMessages,
    canPromoteMembers = canPromoteMembers,
    canManageVideoChats = canManageVideoChats,
    canManageChat = canManageChat,
    canManageTopics = canManageTopics,
    canPostStories = null,
    canEditStories = null,
    canDeleteStories = null
)
