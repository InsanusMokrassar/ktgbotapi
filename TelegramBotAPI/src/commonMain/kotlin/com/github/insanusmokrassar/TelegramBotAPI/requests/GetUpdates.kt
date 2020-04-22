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
