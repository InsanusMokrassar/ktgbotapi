package com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import kotlinx.serialization.*

@Serializable
data class RawCallbackQuery(
    @SerialName(idField)
    val id: CallbackQueryIdentifier,
    @SerialName(fromField)
    val from: User,
    @Optional
    val message: RawMessage? = null,
    @SerialName(inlineMessageIdField)
    @Optional
    val inlineMessageId: InlineMessageIdentifier? = null,
    @SerialName("chat_instance")
    val chatInstance: String,
    @Optional
    val data: String? = null,
    @SerialName("game_short_name")
    @Optional
    val gameShortName: String? = null
) {
    @Transient
    val asCallbackQuery: CallbackQuery by lazy {
        when {
            message != null && data != null -> MessageDataCallbackQuery(id, from, chatInstance, message.asMessage, data)
            message != null && gameShortName != null -> MessageGameShortNameCallbackQuery(id, from, chatInstance, message.asMessage, gameShortName)
            inlineMessageId != null && data != null -> InlineMessageIdDataCallbackQuery(id, from, chatInstance, inlineMessageId, data)
            inlineMessageId != null && gameShortName != null -> InlineMessageIdGameShortNameCallbackQuery(id, from, chatInstance, inlineMessageId, gameShortName)
            else -> throw IllegalStateException("Strange answer from server, can't create callback query")
        }
    }
}
