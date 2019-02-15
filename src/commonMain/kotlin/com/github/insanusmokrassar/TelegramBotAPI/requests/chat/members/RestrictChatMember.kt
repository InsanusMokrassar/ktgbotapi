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
    @Optional
    override val untilDate: TelegramDate? = null,
    @SerialName(canSendMessagesField)
    @Optional
    private val canSendMessages: Boolean? = null,
    @SerialName(canSendMediaMessagesField)
    @Optional
    private val canSendMediaMessages: Boolean? = null,
    @SerialName(canSendOtherMessagesField)
    @Optional
    private val canSendOtherMessages: Boolean? = null,
    @SerialName(canAddWebPagePreviewsField)
    @Optional
    private val canAddWebPagePreviews: Boolean? = null
) : ChatMemberRequest<Boolean>, UntilDate {
    override fun method(): String = "restrictChatMember"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}
