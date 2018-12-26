package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types.UntilDate
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts.ChatMemberRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class PromoteChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(untilDateField)
    @Optional
    override val untilDate: TelegramDate? = null,
    @SerialName(canChangeInfoField)
    @Optional
    private val canChangeInfo: Boolean? = null,
    @SerialName(canPostMessagesField)
    @Optional
    private val canPostMessages: Boolean? = null,
    @SerialName(canEditMessagesField)
    @Optional
    private val canEditMessages: Boolean? = null,
    @SerialName(canDeleteMessagesField)
    @Optional
    private val canDeleteMessages: Boolean? = null,
    @SerialName(canInviteUsersField)
    @Optional
    private val canInviteUsers: Boolean? = null,
    @SerialName(canRestrictMembersField)
    @Optional
    private val canRestrictMembers: Boolean? = null,
    @SerialName(canPinMessagesField)
    @Optional
    private val canPinMessages: Boolean? = null,
    @SerialName(canPromoteMembersField)
    @Optional
    private val canPromoteMembers: Boolean? = null
) : ChatMemberRequest<Boolean>, UntilDate {
    override fun method(): String = "promoteChatMember"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}
