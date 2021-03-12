package dev.inmo.tgbotapi.requests.chat.abstracts

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.DeserializationStrategy

interface ChatInviteLinkRequest : SimpleRequest<CommonInviteLink> {
    val chatId: ChatIdentifier

    override val resultDeserializer: DeserializationStrategy<CommonInviteLink>
        get() = CommonInviteLink.serializer()
}
interface KnownChatInviteLinkRequest : ChatInviteLinkRequest {
    val inviteLink: String
}
interface EditChatInviteLinkRequest : ChatInviteLinkRequest {
    val expireDate: DateTime?
    val membersLimit: MembersLimit?
}
