package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializer
import dev.inmo.tgbotapi.types.message.content.MessageContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RawCallbackQuery(
    @SerialName(idField)
    val id: CallbackQueryIdentifier,
    @SerialName(fromField)
    val from: CommonUser,
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
    private var inited: CallbackQuery? = null
    fun asCallbackQuery(raw: String): CallbackQuery {
        return inited ?: when {
            message != null && data != null -> when {
                message is ContentMessage<*> -> MessageDataCallbackQuery(id, from, chatInstance, message, data)
                message is InaccessibleMessage -> InaccessibleMessageDataCallbackQuery(id, from, chatInstance, message, data)
                else -> UnknownCallbackQueryType(
                    id,
                    from,
                    chatInstance,
                    raw
                )
            }
            message != null && gameShortName != null -> when {
                message is ContentMessage<*> -> MessageGameShortNameCallbackQuery(id, from, chatInstance, message, gameShortName)
                message is InaccessibleMessage -> InaccessibleMessageGameShortNameCallbackQuery(id, from, chatInstance, message, gameShortName)
                else -> UnknownCallbackQueryType(
                    id,
                    from,
                    chatInstance,
                    raw
                )
            }
            inlineMessageId != null && data != null -> InlineMessageIdDataCallbackQuery(id, from, chatInstance, inlineMessageId, data)
            inlineMessageId != null && gameShortName != null -> InlineMessageIdGameShortNameCallbackQuery(id, from, chatInstance, inlineMessageId, gameShortName)
            else -> UnknownCallbackQueryType(
                id,
                from,
                chatInstance,
                raw
            )
        }.also {
            inited = it
        }
    }
}
