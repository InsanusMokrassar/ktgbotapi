package dev.inmo.tgbotapi.requests.chat.abstracts

import korlibs.time.DateTime
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.DeserializationStrategy

interface ChatInviteLinkRequest<R : SecondaryChatInviteLink> : SimpleRequest<R> {
    val chatId: ChatIdentifier
}

interface KnownChatInviteLinkRequest<R : SecondaryChatInviteLink> : ChatInviteLinkRequest<R> {
    val inviteLink: String
}

interface LimitedMembersChatInviteLinkRequest : ChatInviteLinkRequest<ChatInviteLinkWithLimitedMembers> {
    val membersLimit: MembersLimit

    override val resultDeserializer: DeserializationStrategy<ChatInviteLinkWithLimitedMembers>
        get() = ChatInviteLinkWithLimitedMembers.serializer()
}

interface WithJoinRequestChatInviteLinkRequest : ChatInviteLinkRequest<ChatInviteLinkWithJoinRequest> {
    override val resultDeserializer: DeserializationStrategy<ChatInviteLinkWithJoinRequest>
        get() = ChatInviteLinkWithJoinRequest.serializer()
}

interface EditChatInviteLinkRequest<R : SecondaryChatInviteLink> : ChatInviteLinkRequest<R> {
    val expireDate: DateTime?
    val name: String?
}
