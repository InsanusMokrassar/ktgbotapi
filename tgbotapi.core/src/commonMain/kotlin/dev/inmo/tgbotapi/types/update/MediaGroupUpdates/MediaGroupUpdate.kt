package dev.inmo.tgbotapi.types.update.MediaGroupUpdates

import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.update.abstracts.*

/**
 * By default there is no instances of objects which could be deserialized from raw updates. If you want to get objects
 * with this type, you should use something like [dev.inmo.tgbotapi.extensions.api.SetWebhookKt.includeWebhookInRoute]
 *
 * @see dev.inmo.tgbotapi.extensions.api.SetWebhookKt.includeWebhookInRoute
 * @see dev.inmo.tgbotapi.extensions.api.updates.UpdatesPollingKt.startGettingOfUpdates
 */
interface MediaGroupUpdate : Update

interface SentMediaGroupUpdate: MediaGroupUpdate {
    override val data: List<MediaGroupMessage>
    val origins: List<BaseMessageUpdate>
}

interface EditMediaGroupUpdate : BaseEditMessageUpdate, MediaGroupUpdate {
    override val data: MediaGroupMessage
    val origin: BaseMessageUpdate
}
