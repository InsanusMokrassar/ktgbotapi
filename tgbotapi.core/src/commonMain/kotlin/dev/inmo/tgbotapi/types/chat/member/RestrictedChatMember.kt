package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.abstracts.types.UntilDate
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.User
import kotlinx.serialization.Serializable

@Serializable(ChatMemberSerializer::class)
sealed interface RestrictedChatMember : ChatMember, UntilDate {
    companion object {
        // backward compatibility fun
        @Deprecated(
            "Renamed",
            ReplaceWith(
                "RestrictedChatMemberImpl(user, untilDate, isMember, canSendMessages, canSendAudios, canSendDocuments, canSendPhotos, canSendVideos, canSendVideoNotes, canSendVoiceNotes, canSendPolls, canSendOtherMessages, canAddWebPagePreviews, canChangeInfo, canInviteUsers, canPinMessages, canManageTopics)",
                "dev.inmo.tgbotapi.types.chat.members.RestrictedChatMemberImpl",
            )
        )
        operator fun invoke(
            user: User,
            untilDate: TelegramDate? = null,
            isMember: Boolean = false,
            canSendMessages: Boolean = false,
            canSendAudios: Boolean = false,
            canSendDocuments: Boolean = false,
            canSendPhotos: Boolean = false,
            canSendVideos: Boolean = false,
            canSendVideoNotes: Boolean = false,
            canSendVoiceNotes: Boolean = false,
            canSendPolls: Boolean = false,
            canSendOtherMessages: Boolean = false,
            canAddWebPagePreviews: Boolean = false,
            canChangeInfo: Boolean = false,
            canInviteUsers: Boolean = false,
            canPinMessages: Boolean = false,
            canManageTopics: Boolean = false
        ) = RestrictedMemberChatMember(
            user = user,
            untilDate = untilDate,
            isMember = isMember,
            canSendMessages = canSendMessages,
            canSendAudios = canSendAudios,
            canSendDocuments = canSendDocuments,
            canSendPhotos = canSendPhotos,
            canSendVideos = canSendVideos,
            canSendVideoNotes = canSendVideoNotes,
            canSendVoiceNotes = canSendVoiceNotes,
            canSendPolls = canSendPolls,
            canSendOtherMessages = canSendOtherMessages,
            canAddWebPagePreviews = canAddWebPagePreviews,
            canChangeInfo = canChangeInfo,
            canInviteUsers = canInviteUsers,
            canPinMessages = canPinMessages,
            canManageTopics = canManageTopics
        )
    }
}

@Deprecated("Renamed", ReplaceWith("RestrictedChatMember", "dev.inmo.tgbotapi.types.chat.member.RestrictedChatMember"))
typealias BannedChatMember = RestrictedChatMember
