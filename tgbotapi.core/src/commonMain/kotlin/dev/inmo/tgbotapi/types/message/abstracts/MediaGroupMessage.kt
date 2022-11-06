package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.MediaGroupIdentifier
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent

interface MediaGroupMessage<T : MediaGroupPartContent> : CommonMessage<T> {
    val mediaGroupId: MediaGroupIdentifier
}
