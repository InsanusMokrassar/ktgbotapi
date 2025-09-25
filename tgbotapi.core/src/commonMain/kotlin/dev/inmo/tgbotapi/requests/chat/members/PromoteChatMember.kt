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
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
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
    private val canManageTopics: Boolean? = null,
    @SerialName(canPostStoriesField)
    private val canPostStories: Boolean? = null,
    @SerialName(canEditStoriesField)
    private val canEditStories: Boolean? = null,
    @SerialName(canDeleteStoriesField)
    private val canDeleteStories: Boolean? = null,
    @SerialName(canManageDirectMessagesField)
    private val canManageDirectMessages: Boolean? = null,
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
    canDeleteStories: Boolean? = null,
    canManageDirectMessages: Boolean? = null,
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
    canDeleteStories = canDeleteStories,
    canManageDirectMessages = canManageDirectMessages
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
