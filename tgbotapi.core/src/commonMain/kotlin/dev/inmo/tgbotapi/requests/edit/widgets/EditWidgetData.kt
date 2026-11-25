package dev.inmo.tgbotapi.requests.edit.widgets

import dev.inmo.tgbotapi.abstracts.types.MessageAction
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.edit.abstracts.EditInlineMessage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.widgets.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*

/** Edits a widget in a chat message. Empty data clears a field, and omitted data preserves the current value. */
@Serializable
data class EditChatMessageWidgetData(
    @SerialName(chatIdField) override val chatId: ChatIdentifier,
    @SerialName(messageIdField) override val messageId: MessageId,
    @SerialName(publicDataField) val publicData: String? = null,
    @SerialName(chatDataField) val chatData: String? = null
) : SimpleRequest<Unit>, MessageAction {
    override fun method(): String = "editMessageWidgetData"
    override val resultDeserializer: DeserializationStrategy<Unit> get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*> get() = serializer()
}

/** Edits a widget in an inline message. Empty data clears a field, and omitted data preserves the current value. */
@Serializable
data class EditInlineMessageWidgetData(
    @SerialName(inlineMessageIdField) override val inlineMessageId: InlineMessageId,
    @SerialName(publicDataField) val publicData: String? = null,
    @SerialName(chatDataField) val chatData: String? = null
) : EditInlineMessage {
    override fun method(): String = "editMessageWidgetData"
    override val requestSerializer: SerializationStrategy<*> get() = serializer()
}

/** Changes the public data shared by all copies of a widget created by the bot. */
@Serializable
data class EditSharedWidgetData(
    @SerialName(widgetIdField) val widgetId: WidgetId,
    @SerialName(publicDataField) val publicData: String,
    @SerialName(cacheTimeField) val cacheTime: Seconds? = null
) : SimpleRequest<Unit> {
    override fun method(): String = "editSharedWidgetData"
    override val resultDeserializer: DeserializationStrategy<Unit> get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*> get() = serializer()
}
