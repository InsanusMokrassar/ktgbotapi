package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.ksp.lib.ClassCastsIncluded
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.Serializable

@Serializable(PreviewChatSerializer::class)
sealed interface ChannelChat : SuperPublicChat

@Serializable(PreviewChatSerializer::class)
sealed interface GroupChat : PublicChat

@Serializable(PreviewChatSerializer::class)
sealed interface PrivateChat : Chat, UsernameChat {
    override val id: UserId
    val firstName: String
    val lastName: String
}

@Serializable(PreviewChatSerializer::class)
sealed interface PublicChat : Chat {
    val title: String
}

@Serializable(PreviewChatSerializer::class)
sealed interface SupergroupChat : GroupChat, SuperPublicChat

@Serializable(PreviewChatSerializer::class)
sealed interface SuperPublicChat : PublicChat, UsernameChat

@Serializable(PreviewChatSerializer::class)
sealed interface UsernameChat : Chat {
    val username: Username?
}

@Serializable(PreviewChatSerializer::class)
sealed interface PossiblyPremiumChat : Chat {
    val isPremium: Boolean
}

@Serializable(PreviewChatSerializer::class)
sealed interface AbleToAddInAttachmentMenuChat : Chat {
    val addedToAttachmentMenu: Boolean
}

@Serializable(PreviewChatSerializer::class)
@ClassCastsIncluded
sealed interface Chat {
    val id: ChatId
}
