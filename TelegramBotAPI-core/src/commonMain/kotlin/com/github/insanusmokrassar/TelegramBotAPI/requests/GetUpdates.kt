package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.UpdateSerializerWithoutSerialization
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

private val updatesListSerializer = ListSerializer(
    UpdateSerializerWithoutSerialization
)

/**
 * Request updates from Telegram Bot API system. It is important, that the result updates WILL NOT include
 * [com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.MediaGroupUpdate] objects due to the fact,
 * that it is internal abstraction and in fact any [com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage]
 * is just a common [com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message]
 *
 * @see com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates.retrieving.updateHandlerWithMediaGroupsAdaptation
 * @see com.github.insanusmokrassar.TelegramBotAPI.utils.convertWithMediaGroupUpdates
 */
@Serializable
data class GetUpdates(
    val offset: UpdateIdentifier? = null,// set `last update id + 1` to receive next part of updates
    val limit: Int = getUpdatesLimit.last,
    val timeout: Seconds? = null,
    val allowed_updates: List<String>? = ALL_UPDATES_LIST
): SimpleRequest<List<Update>> {
    override fun method(): String = "getUpdates"

    override val resultDeserializer: DeserializationStrategy<List<Update>>
        get() = updatesListSerializer

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        if (limit !in getUpdatesLimit) {
            error("GetUpdates request can be called only with limit in range $getUpdatesLimit (actual value is $limit)")
        }
    }
}
