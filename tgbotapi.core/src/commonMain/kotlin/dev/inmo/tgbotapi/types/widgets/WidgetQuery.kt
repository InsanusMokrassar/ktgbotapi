package dev.inmo.tgbotapi.types.widgets

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.ChatType
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Common fields available when answering a query from an HTML widget. */
sealed interface WidgetQuerySource : FromUser {
    val id: WidgetQueryId
    val type: WidgetQueryType
    val data: String
    val widgetId: WidgetId?
}

/** Query from a widget in a message sent by the bot or via the bot. */
@Serializable
data class WidgetQuery(
    @SerialName(idField) override val id: WidgetQueryId,
    @SerialName(fromField) override val from: User,
    @SerialName(typeField) override val type: WidgetQueryType,
    @SerialName(dataField) override val data: String,
    @SerialName("chat_instance") val chatInstance: String,
    @SerialName(chatField) val chat: PreviewChat? = null,
    @SerialName(messageIdField) val messageId: MessageId? = null,
    @SerialName(inlineMessageIdField) val inlineMessageId: InlineMessageId? = null,
    @SerialName(chatTypeField) val chatType: ChatType? = null,
    @SerialName(widgetIdField) override val widgetId: WidgetId? = null
) : WidgetQuerySource

/** Query from a forwarded message containing a shared widget created by the bot. */
@Serializable
data class SharedWidgetQuery(
    @SerialName(idField) override val id: WidgetQueryId,
    @SerialName(fromField) override val from: User,
    @SerialName(typeField) override val type: WidgetQueryType,
    @SerialName(dataField) override val data: String,
    @SerialName(widgetIdField) override val widgetId: WidgetId
) : WidgetQuerySource
