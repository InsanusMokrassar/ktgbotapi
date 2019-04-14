package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.ALL_UPDATES_LIST
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.RawUpdate
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.ArrayListSerializer

@Deprecated("Replaced to other package", ReplaceWith("UPDATE_MESSAGE", "com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_MESSAGE"))
const val UPDATE_MESSAGE = com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_MESSAGE
@Deprecated("Replaced to other package", ReplaceWith("UPDATE_EDITED_MESSAGE", "com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_EDITED_MESSAGE"))
const val UPDATE_EDITED_MESSAGE = com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_EDITED_MESSAGE
@Deprecated("Replaced to other package", ReplaceWith("UPDATE_CHANNEL_POST", "com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_CHANNEL_POST"))
const val UPDATE_CHANNEL_POST = com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_CHANNEL_POST
@Deprecated("Replaced to other package", ReplaceWith("UPDATE_EDITED_CHANNEL_POST", "com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_EDITED_CHANNEL_POST"))
const val UPDATE_EDITED_CHANNEL_POST = com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_EDITED_CHANNEL_POST
@Deprecated("Replaced to other package", ReplaceWith("UPDATE_CHOSEN_INLINE_RESULT", "com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_CHOSEN_INLINE_RESULT"))
const val UPDATE_CHOSEN_INLINE_RESULT = com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_CHOSEN_INLINE_RESULT
@Deprecated("Replaced to other package", ReplaceWith("UPDATE_INLINE_QUERY", "com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_INLINE_QUERY"))
const val UPDATE_INLINE_QUERY = com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_INLINE_QUERY
@Deprecated("Replaced to other package", ReplaceWith("UPDATE_CALLBACK_QUERY", "com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_CALLBACK_QUERY"))
const val UPDATE_CALLBACK_QUERY = com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_CALLBACK_QUERY
@Deprecated("Replaced to other package", ReplaceWith("UPDATE_SHIPPING_QUERY", "com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_SHIPPING_QUERY"))
const val UPDATE_SHIPPING_QUERY = com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_SHIPPING_QUERY
@Deprecated("Replaced to other package", ReplaceWith("UPDATE_PRE_CHECKOUT_QUERY", "com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_PRE_CHECKOUT_QUERY"))
const val UPDATE_PRE_CHECKOUT_QUERY = com.github.insanusmokrassar.TelegramBotAPI.types.UPDATE_PRE_CHECKOUT_QUERY

@Serializable
data class GetUpdates(
    val offset: UpdateIdentifier? = null,// set `last update id + 1` to receive next part of updates
    val limit: Int? = null,
    val timeout: Int? = null,
    val allowed_updates: List<String>? = ALL_UPDATES_LIST
): SimpleRequest<List<RawUpdate>> {
    override fun method(): String = "getUpdates"

    override fun resultSerializer(): KSerializer<List<RawUpdate>> = ArrayListSerializer(RawUpdate.serializer())
}
