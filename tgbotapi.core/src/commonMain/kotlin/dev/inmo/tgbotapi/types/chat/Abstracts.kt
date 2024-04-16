package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.Serializable

@Serializable(ChatSerializer::class)
sealed interface UsernameChat : Chat {
    val username: Username?
}

@Serializable(ChatSerializer::class)
sealed interface PrivateChat : Chat, UsernameChat {
    override val id: UserId
    val firstName: String
    val lastName: String
}

@Serializable(ChatSerializer::class)
sealed interface BusinessChat : Chat {
    override val id: BusinessChatId
    val original: PrivateChat
}

@Serializable(ChatSerializer::class)
sealed interface PublicChat : Chat {
    val title: String
}

@Serializable(ChatSerializer::class)
sealed interface SuperPublicChat : PublicChat, UsernameChat

@Serializable(ChatSerializer::class)
sealed interface ChannelChat : SuperPublicChat {
    override val id: ChatId
}

@Serializable(ChatSerializer::class)
sealed interface GroupChat : PublicChat

@Serializable(ChatSerializer::class)
sealed interface SupergroupChat : GroupChat, SuperPublicChat

@Serializable(ChatSerializer::class)
sealed interface ForumChat : SupergroupChat

@Serializable(ChatSerializer::class)
sealed interface PossiblyPremiumChat : Chat {
    val isPremium: Boolean
}

@Serializable(ChatSerializer::class)
sealed interface AbleToAddInAttachmentMenuChat : Chat {
    val addedToAttachmentMenu: Boolean
}

@Serializable(ChatSerializer::class)
@ClassCastsIncluded(excludeRegex = ".*Impl")
sealed interface Chat {
    val id: IdChatIdentifier
}
