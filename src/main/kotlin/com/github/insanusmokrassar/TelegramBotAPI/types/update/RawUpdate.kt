package com.github.insanusmokrassar.TelegramBotAPI.types.update

import com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery.RawCallbackQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.ChosenInlineResult.RawChosenInlineResult
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.query.RawInlineQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.PreCheckoutQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.ShippingQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.polls.Poll
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.types.updateIdField
import kotlinx.serialization.*

// TODO:: add ShippingQuery type
// TODO:: add PreCheckoutQuery type
@Serializable
data class RawUpdate constructor(
    @SerialName(updateIdField)
    val updateId: UpdateIdentifier,
    private val message: RawMessage? = null,
    private val edited_message: RawMessage? = null,
    private val channel_post: RawMessage? = null,
    private val edited_channel_post: RawMessage? = null,
    private val inline_query: RawInlineQuery? = null,
    private val chosen_inline_result: RawChosenInlineResult? = null,
    private val callback_query: RawCallbackQuery? = null,
    private val shipping_query: ShippingQuery? = null,
    private val pre_checkout_query: PreCheckoutQuery? = null,
    private val poll: Poll? = null
) {
    @Transient
    val asUpdate: Update by lazy {
        when {
            message != null -> MessageUpdate(updateId, message.asMessage)
            edited_message != null -> EditMessageUpdate(updateId, edited_message.asMessage)
            channel_post != null -> ChannelPostUpdate(updateId, channel_post.asMessage)
            edited_channel_post != null -> EditChannelPostUpdate(updateId, edited_channel_post.asMessage)

            chosen_inline_result != null -> ChosenInlineResultUpdate(updateId, chosen_inline_result.asChosenInlineResult)
            inline_query != null -> InlineQueryUpdate(updateId, inline_query.asInlineQuery)
            callback_query != null -> CallbackQueryUpdate(updateId, callback_query.asCallbackQuery)
            shipping_query != null -> ShippingQueryUpdate(updateId, shipping_query)
            pre_checkout_query != null -> PreCheckoutQueryUpdate(updateId, pre_checkout_query)
            poll != null -> PollUpdate(updateId, poll)
            else -> throw IllegalArgumentException("Unsupported type of update")
        }
    }
}
