package dev.inmo.tgbotapi.types.chat.extended

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.ChatPermissions
import dev.inmo.tgbotapi.types.chat.abstracts.extended.ExtendedGroupChat
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExtendedGroupChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(titleField)
    override val title: String,
    @SerialName(photoField)
    override val chatPhoto: ChatPhoto? = null,
    @SerialName(permissionsField)
    override val permissions: ChatPermissions,
    @SerialName(descriptionField)
    override val description: String = "",
    @SerialName(inviteLinkField)
    override val inviteLink: String? = null,
    @SerialName(pinnedMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    override val pinnedMessage: Message? = null
) : ExtendedGroupChat
