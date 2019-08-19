package com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializer
import kotlinx.serialization.*

@Serializable
internal data class RawCallbackQuery(
    @SerialName(idField)
    val id: CallbackQueryIdentifier,
    @SerialName(fromField)
    val from: User,
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    val message: Message? = null,
    @SerialName(inlineMessageIdField)
    val inlineMessageId: InlineMessageIdentifier? = null,
    @SerialName("chat_instance")
    val chatInstance: String,
    val data: String? = null,
    @SerialName("game_short_name")
    val gameShortName: String? = null
) {
    @Transient
    val asCallbackQuery: CallbackQuery by lazy {
        when {
            message != null && data != null -> MessageDataCallbackQuery(id, from, chatInstance, message, data)
            message != null && gameShortName != null -> MessageGameShortNameCallbackQuery(id, from, chatInstance, message, gameShortName)
            inlineMessageId != null && data != null -> InlineMessageIdDataCallbackQuery(id, from, chatInstance, inlineMessageId, data)
            inlineMessageId != null && gameShortName != null -> InlineMessageIdGameShortNameCallbackQuery(id, from, chatInstance, inlineMessageId, gameShortName)
            else -> throw IllegalStateException("Strange answer from server, can't create callback query")
        }
    }
}
