package dev.inmo.tgbotapi.types.chat

import kotlinx.serialization.Serializable

@Serializable(PreviewChatSerializer::class)
sealed interface PreviewChat : Chat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(PreviewChatSerializer::class)
sealed interface PreviewUsernameChat : PreviewChat, UsernameChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(PreviewChatSerializer::class)
sealed interface PreviewPrivateChat : PreviewUsernameChat, PrivateChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(PreviewChatSerializer::class)
sealed interface PreviewBusinessChat : BusinessChat, PreviewChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(PreviewChatSerializer::class)
sealed interface PreviewPublicChat : PreviewChat, PublicChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(PreviewChatSerializer::class)
sealed interface PreviewSuperPublicChat : PreviewPublicChat, PreviewUsernameChat, SuperPublicChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(PreviewChatSerializer::class)
sealed interface PreviewChannelChat : PreviewSuperPublicChat, ChannelChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(PreviewChatSerializer::class)
sealed interface PreviewGroupChat : PreviewPublicChat, GroupChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(PreviewChatSerializer::class)
sealed interface PreviewSupergroupChat : PreviewGroupChat, PreviewSuperPublicChat, SupergroupChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(PreviewChatSerializer::class)
sealed interface PreviewForumChat : PreviewSupergroupChat, ForumChat

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(PreviewChatSerializer::class)
sealed interface PreviewChannelDirectMessagesChat : PreviewSupergroupChat, ChannelDirectMessagesChat
