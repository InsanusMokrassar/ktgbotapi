package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.UntilDate
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts.ChatMemberRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class RestrictChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(untilDateField)
    override val untilDate: TelegramDate? = null,
    @SerialName(canSendMessagesField)
    private val canSendMessages: Boolean? = null,
    @SerialName(canSendMediaMessagesField)
    private val canSendMediaMessages: Boolean? = null,
    @SerialName(canSendOtherMessagesField)
    private val canSendOtherMessages: Boolean? = null,
    @SerialName(canAddWebPagePreviewsField)
    private val canAddWebPagePreviews: Boolean? = null
) : ChatMemberRequest<Boolean>, UntilDate {
    override fun method(): String = "restrictChatMember"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}
