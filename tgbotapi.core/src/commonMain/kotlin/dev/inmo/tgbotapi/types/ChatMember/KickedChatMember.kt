package dev.inmo.tgbotapi.types.ChatMember

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.ChatMember.abstracts.BannedChatMember
import kotlinx.serialization.*

@Serializable
data class KickedChatMember(
    @SerialName(userField)
    override val user: User,
    @SerialName(untilDateField)
    override val untilDate: TelegramDate? = null
) : BannedChatMember {
    @SerialName(statusField)
    @Required
    private val type: String = "kicked"
}