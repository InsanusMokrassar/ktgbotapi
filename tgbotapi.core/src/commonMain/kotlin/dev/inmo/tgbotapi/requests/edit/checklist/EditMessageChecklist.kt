package dev.inmo.tgbotapi.requests.edit.checklist

import dev.inmo.tgbotapi.requests.edit.abstracts.EditChatMessage
import dev.inmo.tgbotapi.requests.edit.abstracts.EditReplyMessage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.checklists.Checklist
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.ChecklistContent
import dev.inmo.tgbotapi.types.message.content.MessageContent
import kotlinx.serialization.*

const val editMessageChecklistMethod = "editMessageChecklist"

private val commonResultDeserializer = TelegramBotAPIMessageDeserializationStrategyClass<ContentMessage<ChecklistContent>>()

@Serializable
data class EditMessageChecklist(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
    @SerialName(checklistField)
    val checklist: Checklist.Input,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditChatMessage<ChecklistContent>, EditReplyMessage {
    override fun method(): String = editMessageChecklistMethod
    override val resultDeserializer: DeserializationStrategy<ContentMessage<ChecklistContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
