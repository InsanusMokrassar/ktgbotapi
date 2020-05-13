package com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.*

/**
 * By default there is no instances of objects which could be deserialized from raw updates. If you want to get objects
 * with this type, you should use something like [com.github.insanusmokrassar.TelegramBotAPI.extensions.api.SetWebhookKt.includeWebhookInRoute]
 *
 * @see com.github.insanusmokrassar.TelegramBotAPI.extensions.api.SetWebhookKt.includeWebhookInRoute
 * @see com.github.insanusmokrassar.TelegramBotAPI.extensions.api.updates.UpdatesPollingKt.startGettingOfUpdates
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
