package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage

interface BaseSentMessageUpdate : BaseMessageUpdate {
    /**
     * This method copies current [BaseSentMessageUpdate] with new data from [newData]
     *
     * **THERE IS NO ANY GUARANTEE THAT THIS METHOD WILL NOT THROW ANY EXCEPTION**
     */
    fun copy(newData: ContentMessage<*>): BaseSentMessageUpdate
}
