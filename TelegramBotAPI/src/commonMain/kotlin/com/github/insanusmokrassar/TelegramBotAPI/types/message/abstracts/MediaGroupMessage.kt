package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.MediaGroupIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaGroupContent

interface MediaGroupMessage : CommonMessage<MediaGroupContent> {
    val mediaGroupId: MediaGroupIdentifier
}
