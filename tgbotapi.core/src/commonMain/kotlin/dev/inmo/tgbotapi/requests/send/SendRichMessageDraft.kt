package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyMessageThreadRequest
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.canStopField
import dev.inmo.tgbotapi.types.chatIdField
import dev.inmo.tgbotapi.types.draftIdField
import dev.inmo.tgbotapi.types.keepOnStopField
import dev.inmo.tgbotapi.types.messageThreadIdField
import dev.inmo.tgbotapi.types.threadIdOrDirectMessageThreadIdAsThreadId
import dev.inmo.tgbotapi.types.richMessageField
import dev.inmo.tgbotapi.types.rich.InputRichMessage
import dev.inmo.tgbotapi.types.rich.multipartFiles
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

/**
 * Use this method to stream a partial rich message to a user while the message is being generated. The streamed draft is
 * ephemeral and acts as a temporary 30-second preview - once the output is finalized, [SendRichMessage] must be called
 * with the complete message to persist it in the user's chat.
 *
 * @param chatId Numeric target chat identifier.
 * @param threadId Message thread identifier. Defaults from [chatId].
 * @see <a href="https://core.telegram.org/bots/api#sendrichmessagedraft">sendRichMessageDraft</a>
 */
@Serializable
data class SendRichMessageDraft(
    @SerialName(chatIdField)
    val chatId: IdChatIdentifier,
    /**
     * Unique identifier of the message draft; must be non-zero. Changes to drafts with the same identifier are animated.
     */
    @SerialName(draftIdField)
    val draftId: Long,
    @SerialName(richMessageField)
    val richMessage: InputRichMessage,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = chatId.threadIdOrDirectMessageThreadIdAsThreadId,
    @SerialName(canStopField)
    val canStop: Boolean? = null,
    @SerialName(keepOnStopField)
    val keepOnStop: Boolean? = null
) : SimpleRequest<Unit>, OptionallyMessageThreadRequest {
    init {
        require(draftId != 0L) {
            "draftId of SendRichMessageDraft must be non-zero"
        }
        require(richMessage.multipartFiles.isEmpty()) {
            "sendRichMessageDraft does not support direct upload of new files"
        }
    }

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer

    override fun method(): String = "sendRichMessageDraft"
}
