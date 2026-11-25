package dev.inmo.tgbotapi.types.widgets

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class WidgetId(val string: String)

@Serializable
@JvmInline
value class WidgetQueryId(val string: String)

/** An HTML widget received in a message. A shared widget has an [id] for resending and editing. */
@Serializable
data class Widget(
    @SerialName(htmlField) val html: String,
    @SerialName(idField) val id: WidgetId? = null,
    @SerialName(widthField) val width: Int? = null,
    @SerialName(heightField) val height: Int? = null,
    @SerialName(publicDataField) val publicData: String? = null,
    @SerialName(chatDataField) val chatData: String? = null
) : ReplyInfo.External.ContentVariant

internal const val publicDataField = "public_data"
internal const val chatDataField = "chat_data"
internal const val widgetIdField = "widget_id"
internal const val allowQueriesField = "allow_queries"
internal const val isSharedField = "is_shared"
