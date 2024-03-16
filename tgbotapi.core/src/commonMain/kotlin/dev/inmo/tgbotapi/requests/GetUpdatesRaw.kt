package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.types.ALL_UPDATES_LIST
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.getUpdatesLimit
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.JsonArray

/**
 * Raw variant of [GetUpdates]. This type will be useful in case you wish to get some updates and handle them by
 * yourself or with [dev.inmo.tgbotapi.utils.convertWithMediaGroupUpdates]
 */
@Serializable
data class GetUpdatesRaw(
    override val offset: UpdateId? = null,// set `last update id + 1` to receive next part of updates
    override val limit: Int = getUpdatesLimit.last,
    override val timeout: Seconds? = null,
    override val allowed_updates: List<String>? = ALL_UPDATES_LIST
): GetUpdatesRequest<JsonArray> {
    override fun method(): String = "getUpdates"

    override val resultDeserializer: DeserializationStrategy<JsonArray>
        get() = JsonArray.serializer()

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        if (limit !in getUpdatesLimit) {
            error("GetUpdates request can be called only with limit in range $getUpdatesLimit (actual value is $limit)")
        }
    }
}