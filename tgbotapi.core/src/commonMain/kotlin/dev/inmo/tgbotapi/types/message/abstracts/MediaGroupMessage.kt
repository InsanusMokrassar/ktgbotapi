package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.MediaGroupIdentifier
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaGroupContent

interface MediaGroupMessage<T : MediaGroupContent> : CommonMessage<T> {
    val mediaGroupId: MediaGroupIdentifier
}
