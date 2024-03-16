package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.queries.callback.RawCallbackQuery
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.RawChosenInlineResult
import dev.inmo.tgbotapi.types.InlineQueries.query.RawInlineQuery
import dev.inmo.tgbotapi.types.boosts.ChatBoostRemoved
import dev.inmo.tgbotapi.types.boosts.ChatBoostUpdated
import dev.inmo.tgbotapi.types.chat.ChatJoinRequest
import dev.inmo.tgbotapi.types.chat.ChatMessageReactionUpdated
import dev.inmo.tgbotapi.types.chat.ChatMessageReactionsCountUpdated
import dev.inmo.tgbotapi.types.chat.member.ChatMemberUpdated
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.polls.PollAnswer
import dev.inmo.tgbotapi.types.update.abstracts.UnknownUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

@Serializable
internal data class RawUpdate constructor(
    @SerialName(updateIdField)
    val updateId: UpdateId,
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    private val edited_message: CommonMessage<*>? = null,
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    private val message: AccessibleMessage? = null,
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    private val edited_channel_post: CommonMessage<*>? = null,
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    private val channel_post: AccessibleMessage? = null,
    private val inline_query: RawInlineQuery? = null,
    private val chosen_inline_result: RawChosenInlineResult? = null,
    private val callback_query: RawCallbackQuery? = null,
    private val shipping_query: ShippingQuery? = null,
    private val pre_checkout_query: PreCheckoutQuery? = null,
    private val poll: Poll? = null,
    private val poll_answer: PollAnswer? = null,
    private val my_chat_member: ChatMemberUpdated? = null,
    private val chat_member: ChatMemberUpdated? = null,
    private val chat_join_request: ChatJoinRequest? = null,
    private val message_reaction: ChatMessageReactionUpdated? = null,
    private val message_reaction_count: ChatMessageReactionsCountUpdated? = null,
    private val chat_boost: ChatBoostUpdated? = null,
    private val removed_chat_boost: ChatBoostRemoved? = null
) {
    @Transient
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
                my_chat_member != null -> MyChatMemberUpdatedUpdate(updateId, my_chat_member)
                chat_member != null -> CommonChatMemberUpdatedUpdate(updateId, chat_member)
                chat_join_request != null -> ChatJoinRequestUpdate(updateId, chat_join_request)
                message_reaction != null -> ChatMessageReactionUpdatedUpdate(updateId, message_reaction)
                message_reaction_count != null -> ChatMessageReactionsCountUpdatedUpdate(updateId, message_reaction_count)
                chat_boost != null -> ChatBoostUpdatedUpdate(updateId, chat_boost)
                removed_chat_boost != null -> ChatBoostRemovedUpdate(updateId, removed_chat_boost)
                else -> UnknownUpdate(
                    updateId,
                    raw
                )
            }
        } catch (e: NotImplementedError) {
            UnknownUpdate(
                updateId,
                raw
            )
        } catch (e: SerializationException) {
            UnknownUpdate(
                updateId,
                raw
            )
        }.also {
            initedUpdate = it
        }
    }
}
