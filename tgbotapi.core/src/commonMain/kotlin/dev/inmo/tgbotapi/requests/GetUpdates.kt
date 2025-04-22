package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.types.update.abstracts.UpdateSerializerWithoutSerialization
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

private val updatesListSerializer =
    ListSerializer(
        UpdateSerializerWithoutSerialization,
    )

/**
 * Request updates from Telegram Bot API system. It is important, that the result updates WILL NOT include
 * [dev.inmo.tgbotapi.types.update.MediaGroupUpdates.MediaGroupUpdate] objects due to the fact,
 * that it is internal abstraction and in fact any [dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage]
 * is just a common [dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage]
 *
 * @see dev.inmo.tgbotapi.extensions.utils.updates.retrieving.updateHandlerWithMediaGroupsAdaptation
 * @see dev.inmo.tgbotapi.utils.convertWithMediaGroupUpdates
 */
@Serializable
data class GetUpdates(
    override val offset: UpdateId? = null, // set `last update id + 1` to receive next part of updates
    override val limit: Int = getUpdatesLimit.last,
    override val timeout: Seconds? = null,
    override val allowed_updates: List<String>? = ALL_UPDATES_LIST,
) : GetUpdatesRequest<List<Update>> {
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
