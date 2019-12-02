package com.github.insanusmokrassar.TelegramBotAPI.requests

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
