package dev.inmo.tgbotapi.types.chat

import kotlinx.serialization.Serializable

@Serializable(PreviewChatSerializer::class)
sealed interface PreviewChat : Chat

@Serializable(PreviewChatSerializer::class)
sealed interface PreviewUsernameChat : PreviewChat, UsernameChat

@Serializable(PreviewChatSerializer::class)
sealed interface PreviewPrivateChat : PreviewUsernameChat, PrivateChat

@Serializable(PreviewChatSerializer::class)
sealed interface PreviewBusinessChat : BusinessChat, PreviewChat

@Serializable(PreviewChatSerializer::class)
sealed interface PreviewPublicChat : PreviewChat, PublicChat

@Serializable(PreviewChatSerializer::class)
sealed interface PreviewSuperPublicChat : PreviewPublicChat, PreviewUsernameChat, SuperPublicChat

@Serializable(PreviewChatSerializer::class)
sealed interface PreviewChannelChat : PreviewSuperPublicChat, ChannelChat

@Serializable(PreviewChatSerializer::class)
sealed interface PreviewGroupChat : PreviewPublicChat, GroupChat

@Serializable(PreviewChatSerializer::class)
sealed interface PreviewSupergroupChat : PreviewGroupChat, PreviewSuperPublicChat, SupergroupChat

@Serializable(PreviewChatSerializer::class)
sealed interface PreviewForumChat : PreviewSupergroupChat, ForumChat
