package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.ALL_UPDATES_LIST
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.UpdateSerializerWithoutDeserialization
import kotlinx.serialization.*
import kotlinx.serialization.internal.ArrayListSerializer

private val updatesListSerializer = ArrayListSerializer(
    UpdateSerializerWithoutDeserialization
)

@Serializable
data class GetUpdates(
    val offset: UpdateIdentifier? = null,// set `last update id + 1` to receive next part of updates
    val limit: Int? = null,
    val timeout: Int? = null,
    val allowed_updates: List<String>? = ALL_UPDATES_LIST
): SimpleRequest<List<Update>> {
    override fun method(): String = "getUpdates"

    override val resultDeserializer: DeserializationStrategy<List<Update>>
        get() = updatesListSerializer

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.getUpdates(
    offset: UpdateIdentifier? = null,
    limit: Int? = null,
    timeout: Int? = null,
    allowed_updates: List<String>? = ALL_UPDATES_LIST
) = execute(
    GetUpdates(
        offset, limit, timeout, allowed_updates
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.getUpdates(
    lastUpdate: Update,
    limit: Int? = null,
    timeout: Int? = null,
    allowed_updates: List<String>? = ALL_UPDATES_LIST
) = getUpdates(
    lastUpdate.updateId + 1, limit, timeout, allowed_updates
)
