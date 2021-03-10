package dev.inmo.tgbotapi.requests.chat.abstracts

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.DeserializationStrategy

interface ChatInviteLinkRequest : SimpleRequest<BotInviteLink> {
    val chatId: ChatId

    override val resultDeserializer: DeserializationStrategy<BotInviteLink>
        get() = BotInviteLink.serializer()
}
interface KnownChatInviteLinkRequest : ChatInviteLinkRequest {
    val inviteLink: String
}
interface EditChatInviteLinkRequest : ChatInviteLinkRequest {
    val expireDate: DateTime?
    val membersLimit: MembersLimit?
}
