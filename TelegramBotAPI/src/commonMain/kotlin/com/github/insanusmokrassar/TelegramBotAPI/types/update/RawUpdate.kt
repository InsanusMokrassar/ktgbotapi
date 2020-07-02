package com.github.insanusmokrassar.TelegramBotAPI.types.update

import com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery.RawCallbackQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.ChosenInlineResult.RawChosenInlineResult
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.query.RawInlineQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.PreCheckoutQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.ShippingQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.polls.Poll
import com.github.insanusmokrassar.TelegramBotAPI.types.polls.PollAnswer
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.UnknownUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.types.updateIdField
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElement

@Serializable
internal data class RawUpdate constructor(
    @SerialName(updateIdField)
    val updateId: UpdateIdentifier,
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    private val edited_message: CommonMessage<*>? = null,
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    private val message: Message? = null,
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    private val edited_channel_post: CommonMessage<*>? = null,
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    private val channel_post: Message? = null,
    private val inline_query: RawInlineQuery? = null,
    private val chosen_inline_result: RawChosenInlineResult? = null,
    private val callback_query: RawCallbackQuery? = null,
    private val shipping_query: ShippingQuery? = null,
    private val pre_checkout_query: PreCheckoutQuery? = null,
    private val poll: Poll? = null,
    private val poll_answer: PollAnswer? = null
) {
    private var initedUpdate: Update? = null
    /**
     * @return One of children of [Update] interface or null in case of unknown type of update
     */
    fun asUpdate(raw: JsonElement): Update {
        return initedUpdate ?: try {
            when {
                edited_message != null -> EditMessageUpdate(updateId, edited_message)
                message != null -> MessageUpdate(updateId, message)
                edited_channel_post != null -> EditChannelPostUpdate(updateId, edited_channel_post)
                channel_post != null -> ChannelPostUpdate(updateId, channel_post)

                chosen_inline_result != null -> ChosenInlineResultUpdate(updateId, chosen_inline_result.asChosenInlineResult)
                inline_query != null -> InlineQueryUpdate(updateId, inline_query.asInlineQuery)
                callback_query != null -> CallbackQueryUpdate(
                    updateId,
                    callback_query.asCallbackQuery(raw.jsonObject["callback_query"].toString())
                )
                shipping_query != null -> ShippingQueryUpdate(updateId, shipping_query)
                pre_checkout_query != null -> PreCheckoutQueryUpdate(updateId, pre_checkout_query)
                poll != null -> PollUpdate(updateId, poll)
                poll_answer != null -> PollAnswerUpdate(updateId, poll_answer)
                else -> UnknownUpdate(
                    updateId,
                    raw.toString(),
                    raw
                )
            }
        } catch (e: Error) {
            when (e) {
                is SerializationException,
                is NotImplementedError -> UnknownUpdate(
                    updateId,
                    raw.toString(),
                    raw
                )
                else -> throw e
            }
        }.also {
            initedUpdate = it
        }
    }
}
