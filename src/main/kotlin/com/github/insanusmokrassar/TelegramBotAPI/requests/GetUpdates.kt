package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.update.RawUpdate
import kotlinx.serialization.*
import kotlinx.serialization.internal.ArrayListSerializer

const val UPDATE_MESSAGE = "message"
const val UPDATE_EDITED_MESSAGE = "edited_message"
const val UPDATE_CHANNEL_POST = "channel_post"
const val UPDATE_EDITED_CHANNEL_POST = "edited_channel_post"
const val UPDATE_CHOSEN_INLINE_RESULT = "chosen_inline_result"
const val UPDATE_INLINE_QUERY = "inline_query"
const val UPDATE_CALLBACK_QUERY = "callback_query"
const val UPDATE_SHIPPING_QUERY = "shipping_query"
const val UPDATE_PRE_CHECKOUT_QUERY = "pre_checkout_query"
/*

    @Optional private val inline_query: RawInlineQuery? = null,
    @Optional private val chosen_inline_result: Unit? = null,
    @Optional private val callback_query: RawCallbackQuery? = null,
    @Optional private val shipping_query: Unit? = null,
    @Optional private val pre_checkout_query: Unit? = null
 */
@Serializable
data class GetUpdates(
    @Optional
    val offset: UpdateIdentifier? = null,// set `last update id + 1` to receive next part of updates
    @Optional
    val limit: Int? = null,
    @Optional
    val timeout: Int? = null,
    @Optional
    val allowed_updates: List<String>? = listOf(
        UPDATE_MESSAGE,
        UPDATE_EDITED_MESSAGE,
        UPDATE_CHANNEL_POST,
        UPDATE_EDITED_CHANNEL_POST,
        UPDATE_CHOSEN_INLINE_RESULT,
        UPDATE_INLINE_QUERY,
        UPDATE_CALLBACK_QUERY,
        UPDATE_SHIPPING_QUERY,
        UPDATE_PRE_CHECKOUT_QUERY
    )
): SimpleRequest<List<RawUpdate>> {
    override fun method(): String = "getUpdates"

    override fun resultSerializer(): KSerializer<List<RawUpdate>> = ArrayListSerializer(RawUpdate.serializer())
}
