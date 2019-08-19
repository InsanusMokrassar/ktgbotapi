package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.ALL_UPDATES_LIST
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.UpdateSerializerWithoutDeserialization
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.ArrayListSerializer

@Serializable
data class GetUpdates(
    val offset: UpdateIdentifier? = null,// set `last update id + 1` to receive next part of updates
    val limit: Int? = null,
    val timeout: Int? = null,
    val allowed_updates: List<String>? = ALL_UPDATES_LIST
): SimpleRequest<List<Update>> {
    override fun method(): String = "getUpdates"

    override fun resultDeserializer(): KSerializer<List<Update>> = ArrayListSerializer(
        UpdateSerializerWithoutDeserialization
    )
}
