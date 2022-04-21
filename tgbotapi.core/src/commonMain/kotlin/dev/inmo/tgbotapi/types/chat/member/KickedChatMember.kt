package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
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
