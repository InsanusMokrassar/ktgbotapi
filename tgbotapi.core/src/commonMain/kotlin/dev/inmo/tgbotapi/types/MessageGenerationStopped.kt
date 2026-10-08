package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.abstracts.WithPreviewChat
import dev.inmo.tgbotapi.types.chat.PreviewChat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Information about a user stopping message generation.
 */
@Serializable
data class MessageGenerationStopped(
    @SerialName(chatField)
    override val chat: PreviewChat,
    @SerialName(messageThreadIdField)
    val messageThreadId: MessageThreadId? = null,
    @SerialName(draftIdField)
    val draftId: DraftId
) : WithPreviewChat
