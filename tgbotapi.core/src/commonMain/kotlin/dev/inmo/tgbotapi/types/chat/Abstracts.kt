package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.Serializable

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatSerializer::class)
sealed interface UsernameChat : Chat {
    val username: Username?
}

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatSerializer::class)
sealed interface PrivateChat : Chat, UsernameChat {
    override val id: UserId
    val firstName: String
    val lastName: String
}

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatSerializer::class)
sealed interface BusinessChat : Chat {
    override val id: BusinessChatId
    val original: PrivateChat
}

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatSerializer::class)
sealed interface PublicChat : Chat {
    val title: String
}

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatSerializer::class)
sealed interface SuperPublicChat : PublicChat, UsernameChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatSerializer::class)
sealed interface ChannelChat : SuperPublicChat {
    override val id: ChatId
}

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatSerializer::class)
sealed interface GroupChat : PublicChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatSerializer::class)
sealed interface SupergroupChat : GroupChat, SuperPublicChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatSerializer::class)
sealed interface ForumChat : SupergroupChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatSerializer::class)
sealed interface PossiblyPremiumChat : Chat {
    val isPremium: Boolean
}

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatSerializer::class)
sealed interface AbleToAddInAttachmentMenuChat : Chat {
    val addedToAttachmentMenu: Boolean
}

@Serializable(ChatSerializer::class)
@ClassCastsIncluded(excludeRegex = ".*Impl")
sealed interface Chat {
    val id: IdChatIdentifier
}
